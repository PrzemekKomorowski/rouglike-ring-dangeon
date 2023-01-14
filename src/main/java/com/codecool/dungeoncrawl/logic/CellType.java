package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    STONEFLOOR("stonefloor"),

    CLOSEDDOOR("closeddoor"),

    OPENDOOR("opendoor"),

    UPSTAIRS("upstairs"),
    DOWNSTAIRS("downstairs"),
    MAPLEWALL("maplewall"),
    MAPLEWINDOW("maplewindow"),
    TORCH("torch"),

    STONEWALL("stonewall");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
