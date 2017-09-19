package com.damianogiusti.cleanandroid.uimodel;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public class ProvinceUiModel {

    private String code;
    private String name;

    public ProvinceUiModel(String code, String name) {
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
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s - %s", code, name);
    }
}
