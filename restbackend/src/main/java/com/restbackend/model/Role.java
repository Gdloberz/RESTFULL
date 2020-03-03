package com.restbackend.model;

public enum Role {
    user, admin;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
