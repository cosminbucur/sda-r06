package com.sda.hibernate.audit.history;

public enum Action {

    INSERTED("Inserted"),
    UPDATED("UPDATED"),
    DELETED("DELETED");

    private String name;

    Action(String value) {
        this.name = value;
    }

    public String value() {
        return this.name;
    }
}
