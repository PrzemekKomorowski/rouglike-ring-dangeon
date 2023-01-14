package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    int health = 10;
    int damage;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() != CellType.STONEWALL && nextCell.getType() != CellType.MAPLEWINDOW && nextCell.getType() != CellType.MAPLEWALL && nextCell.getType() != CellType.TORCH) {
            if(nextCell.getType() != CellType.CLOSEDDOOR) {
                if (nextCell.getActor() == null) {
                    cell.setActor(null);
                    nextCell.setActor(this);
                    cell = nextCell;
                }
                else if (nextCell.getActor() != null){
                    this.fight(nextCell.getActor());
                }
            }
        }
        if(nextCell.getType() == CellType.CLOSEDDOOR) {
            nextCell.setType(CellType.OPENDOOR);
        }
        if(nextCell.getType() == CellType.UPSTAIRS){
            cell.getGameMap().getMain().upperLevel();
        }
        if(nextCell.getType() == CellType.DOWNSTAIRS){
            cell.getGameMap().getMain().lowerLevel();
        }
    }




    public int getHealth() {
        return health;
    }

    public int getDamage() { return damage;}

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void attack(Actor enemy){
        enemy.health -= this.getDamage();
        System.out.println(this.getClass().getSimpleName() + " : " + this.health);
        System.out.println(enemy.getClass().getSimpleName() + " : " + enemy.health);
    }

    public void fight(Actor enemy) {
        System.out.println(enemy);
        System.out.println(this.getClass().getSimpleName() + " vs " + enemy.getClass().getSimpleName());
        attack(enemy);
        enemy.attack(this);
        if (enemy.health <= 0){
            removeActorFromMap(enemy.getCell());
        }
        if(this.health <= 0){
            removeActorFromMap(this.getCell());
        }
    }

    public void removeActorFromMap(Cell cell){
        cell.setActor(null);
    }
}
