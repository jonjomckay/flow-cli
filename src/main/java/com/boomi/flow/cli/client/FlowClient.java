package com.boomi.flow.cli.client;

import java.util.List;

public interface FlowClient {
    void listUsers(String token, Callback<List<User>> callback);
}
