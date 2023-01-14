package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Lifepotion extends Item implements Armor{
    int health = 5;

    public Lifepotion(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "lifepotion";
    }

    @Override
    public int getHealth() {
        return this.health;
    }
}
