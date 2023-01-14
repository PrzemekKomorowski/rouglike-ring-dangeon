package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item implements Weapon{
    int damage = 5;

    public Sword(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "sword";
    }

    @Override
    public int getDamage() {
       return this.damage;
    }
}
