package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Random;

public class Skeleton extends Actor {
    private Cell cell;

    public Skeleton(Cell cell) {
        super(cell);
        this.setDamage(2);
    }

    public void setDamage(int damage){
        this.damage = damage;
    }
    @Override
    public String getTileName() {
        return "skeleton";
    }
}
