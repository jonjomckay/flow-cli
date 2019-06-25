package com.boomi.flow.cli.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class HttpFlowClient implements FlowClient {
    private final Gson gson;
    private final OkHttpClient httpClient;

    @Inject
    public HttpFlowClient(Gson gson, OkHttpClient httpClient) {
        this.gson = gson;
        this.httpClient = httpClient;
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
