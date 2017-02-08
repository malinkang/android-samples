package com.malinkang.recyclerview.model;

public class Sample {
    private final Class<?> activityClass;
    private final String title;

    public Class<?> getActivityClass() {
        return activityClass;
    }

    public String getTitle() {
        return title;
    }

    public Sample(Class<?> activityClass, String title) {
        this.activityClass = activityClass;
        this.title = title;
    }
}