package com.uphf.livedemo.service.entity;

public record Product(
        String name,
        double price
) {

    public String getIdentity() {
        return name;
    }

}
