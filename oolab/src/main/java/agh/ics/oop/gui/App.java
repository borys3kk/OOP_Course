package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {


    Label lab = new Label("Zwierzak");
    private AbstractWorldMap map;
    private SimulationEngine engine;
    private GridPane gridMap;

    public void init() throws Exception{
        String[] args = getParameters().getRaw().toArray(new String[0]);
        try{
            MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "b", "b"});
            this.map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2, 4)};
            this.engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
        }
        catch (IllegalArgumentException exception){
            System.out.println("Exception caught: " + exception);
        }
    }

    private void drawMap(){
        this.gridMap = new GridPane();

        Label yx = new Label("y/x");
        yx.setFont(new Font(16));
        gridMap.add(yx, 0, 0);
        GridPane.setHalignment(yx, HPos.CENTER);

        for(int k = 0; k <= map.upperRight().x - map.lowerLeft().x; k++){
            Label idx = new Label("" + (map.lowerLeft().x + k));
            idx.setFont(new Font(16));
            gridMap.add(idx, k + 1, 0);
            GridPane.setHalignment(idx, HPos.CENTER);
        }

        for(int k = 0; k <= map.upperRight().y - map.lowerLeft().y; k++){
            Label idx = new Label("" + (map.upperRight().y - k));
            idx.setFont(new Font(16));
            gridMap.add(idx, 0, k + 1);
            GridPane.setHalignment(idx, HPos.CENTER);
        }

        for(int i = 0;i <= map.upperRight().x - map.lowerLeft().x; i++){
            for (int j = 0; j <= map.upperRight().y - map.lowerLeft().y; j++) {
                Label cell;
                Vector2d curPos = new Vector2d(map.lowerLeft().x + i, map.upperRight().y - j);
                if (map.objectAt(curPos) != null){
                    cell = new Label(map.objectAt(curPos).toString());
                }
                else {
                    cell = new Label(" ");
                }
                cell.setFont(new Font(16));;
                gridMap.add(cell, i + 1, j + 1);
                GridPane.setHalignment(cell, HPos.CENTER);
            }
        }

        for(int k = 0; k <= map.upperRight().x - map.lowerLeft().x + 1; k++){
            gridMap.getColumnConstraints().add(new ColumnConstraints(30));
        }

        for(int k = 0; k <= map.upperRight().y - map.lowerLeft().y + 1; k++){
            gridMap.getRowConstraints().add(new RowConstraints(30));
        }

        gridMap.setGridLinesVisible(true);
    }

    public void start(Stage primaryStage){
        drawMap();
        Scene scene = new Scene(gridMap, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
