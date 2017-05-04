package com.damianogiusti.cleanandroid.viewmodel;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public class ProvinceViewModel {

    private String code;
    private String name;

    public ProvinceViewModel(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", code, name);
    }
}
