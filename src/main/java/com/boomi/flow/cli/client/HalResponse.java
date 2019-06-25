package com.boomi.flow.cli.client;

import java.util.List;

public class HalResponse<T> {
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
