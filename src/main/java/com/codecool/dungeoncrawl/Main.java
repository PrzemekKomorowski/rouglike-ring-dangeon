package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {
    GameMap map;

    List<GameMap> maps = new ArrayList<>();
    int level;
    List<String> levels = Arrays.asList("/map.txt","/map2.txt","/map3.txt");

    int left = 13;
    int right = 13;
    int up = 10;
    int down = 10;
    int height = 10;

    public Main() {
        maps.add(MapLoader.loadMap(this, levels.get(level)));
        this.map = maps.get(level);
    }
    Canvas canvas = new Canvas(
            25 * Tiles.TILE_WIDTH,
            20 * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label damageLabel = new Label();
    Label inventory = new Label();
    Button button = new Button("Pick up");


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));


        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Damage: "), 0, 1);
        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(button, 4, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(damageLabel, 1, 1);
        ui.add(inventory, 1, 2);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        button.setOnAction(this::handeButtonClick);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void handeButtonClick(javafx.event.ActionEvent actionEvent) {
        map.getPlayer().checkPickUp();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
        }
//        map.getMobs().forEach(Actor::move);
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Cell playerCell = map.getPlayer().getCell();

//        for (int x = 0; x < map.getWidth(); x++) {
//            for (int y = 0; y < map.getHeight(); y++) {
        for (int x = playerCell.getX() - left; x <= playerCell.getX() + right; x++) {
            for (int y = playerCell.getY() - height; y <= playerCell.getY() + height; y++) {
                int canvaX = x - playerCell.getX() + left;
                int canvaY = y - playerCell.getY() + up;
                Cell cell;
                if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight()) {
                    cell = map.getCell(x, y);
                } else {
                    cell = new Cell();
                }
                Tiles.drawTile(context, cell, canvaX, canvaY);
            }
            healthLabel.setText("" + map.getPlayer().getHealth());
            damageLabel.setText("" + map.getPlayer().getDamage());
            inventory.setText("" + map.getPlayer().getItemNames());
            button.setFocusTraversable(false);
        }
    }
    public void addMap(GameMap map){
        maps.add(map);
    }
    public void upperLevel(){
        level++;
        if(level >= maps.size()){
            GameMap nowaMapa = MapLoader.loadMap(this,levels.get(level));
            addMap(nowaMapa);
            System.out.println(level);
            System.out.println(maps.size());
        }
        this.map = maps.get(level);
        Player player = maps.get(level-1).getPlayer();
        map.getPlayer().setAttribute(player.getInventory(),player.getHealth(), player.getDamage());
    }
    public void lowerLevel(){
        level--;
        this.map = maps.get(level);
        Player player = maps.get(level+1).getPlayer();
        map.getPlayer().setAttribute(player.getInventory(),player.getHealth(), player.getDamage());
    }
}