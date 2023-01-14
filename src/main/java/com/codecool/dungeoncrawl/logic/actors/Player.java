package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

    int damage = 5;
    private List<Item> inventory = new ArrayList<Item>();
    public Player(Cell cell) {
        super(cell);
    }


    public List<Item> getInventory(){
        return inventory;
    }

    public int getDamage(){
        return damage;
    }

    public List<String> getItemNames(){
        List<String> itemNames = new ArrayList<>();
        for (Item item : inventory)
            {itemNames.add(item.getTileName());
        }
        return itemNames;
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }
    public void checkPickUp(){
        if(this.getCell().getItem() != null){
            addToInventory(this.getCell().getItem());
            this.increaseStatistics(this.getCell().getItem());
            this.getCell().getItem().removeItemFromMap(this.getCell());
        }
    }
    public void increaseStatistics(Item item){
        if(item instanceof Weapon){
            this.damage = this.damage + ((Weapon) item).getDamage();
        }
        else if(item instanceof Armor){
            this.health = this.health + ((Armor) item).getHealth();
        }
    }
    public String getTileName() {
        return "player";
    }

    public void setAttribute(List<Item> inventory, int health,int damage){
        this.inventory = inventory;
        this.health = health;
        this.damage = damage;
    }
}
