package com.boomi.flow.cli.client;

import java.util.List;

public interface FlowClient {
    void addUser(String token, String email, Callback<Void> callback);
    void listUsers(String token, Callback<List<User>> callback);
}
