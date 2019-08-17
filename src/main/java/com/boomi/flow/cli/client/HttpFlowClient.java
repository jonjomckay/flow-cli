package com.boomi.flow.cli.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class HttpFlowClient implements FlowClient {
    private final static MediaType MEDIA_TYPE = MediaType.parse("application/json");

    private final Gson gson;
    private final OkHttpClient httpClient;

    public HttpFlowClient(Gson gson, OkHttpClient httpClient) {
        this.gson = gson;
        this.httpClient = httpClient;
    }

    @Override
    public void addUser(String token, String email, Callback<Void> callback) {
        JSONObject body = new JSONObject()
                .put("email", email);

        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .post(RequestBody.create(MEDIA_TYPE, body.toString()))
                .url("https://flow.boomi.com/api/admin/1/users")
                .build();

        httpClient.newCall(request)
                .enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        callback.onResponse(null);
                    }
                });
    }

    @Override
    public void listUsers(String token, Callback<List<User>> callback) {
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url("https://flow.boomi.com/api/admin/1/users?pageSize=1000")
                .build();

        httpClient.newCall(request)
                .enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        HalResponse<User> halResponse = gson.fromJson(
                                response.body().string(),
                                new TypeToken<HalResponse<User>>() {}.getType()
                        );

                        callback.onResponse(halResponse.getItems());
                    }
                });
    }
}
