package com.boomi.flow.cli.client;

import java.io.IOException;

public interface Callback<T> {
    void onFailure(IOException exception);
    void onResponse(T item) throws IOException;
}
