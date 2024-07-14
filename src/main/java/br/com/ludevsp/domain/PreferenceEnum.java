package br.com.ludevsp.domain;


import lombok.Getter;

@Getter
public enum PreferenceEnum {
    FAVORITE((byte) 1, "Favorite"),
    HATED((byte) 2, "Hated"),
    SUGGESTED((byte) 3, "Suggestion");

    private final Byte id;
    private final String name;

    PreferenceEnum(Byte id, String name) {
        this.id = id;
        this.name = name;
    }

}