package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;

public class App extends Application implements IAnimalObserver{

    private AbstractWorldMap map;
    private SimulationEngine engine;
    private GridPane gridMap;

    public void init() throws Exception{
        this.map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2, 4)};
        this.engine = new SimulationEngine(map, positions);
        this.engine.addObserver(this);
        engine.setMoveDelay(300);
        this.gridMap = new GridPane();
    }

    private void drawMap(boolean firstDraw){
        gridMap.setGridLinesVisible(false);
        gridMap.setGridLinesVisible(true);
        Label yx = new Label("y/x");
        yx.setFont(new Font(16));
        gridMap.add(yx, 0, 0);

        GridPane.setHalignment(yx, HPos.CENTER);
        GuiElementBox elementCreator;

        try {
            elementCreator = new GuiElementBox();

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
                    Vector2d curPos = new Vector2d(map.lowerLeft().x + i, map.upperRight().y - j);
                    if(map.objectAt(curPos) != null){
                        VBox sq = elementCreator.mapElement((IMapElement) map.objectAt(curPos));
                        gridMap.add(sq, i + 1, j + 1);
                        GridPane.setHalignment(sq, HPos.CENTER);
                    }
                }
            }

        }
        catch (FileNotFoundException exception){
            System.out.println("Couldn't load files");
        }

        if (firstDraw){
            for(int k = 0; k <= map.upperRight().x - map.lowerLeft().x + 1; k++){
                gridMap.getColumnConstraints().add(new ColumnConstraints(40));
            }

            for(int k = 0; k <= map.upperRight().y - map.lowerLeft().y + 1; k++){
                gridMap.getRowConstraints().add(new RowConstraints(40));
            }
        }
    }
    @Override
    public void animalMoved(){
        Platform.runLater(() ->{
            gridMap.getChildren().clear();
            drawMap(false);
        });
    }

    public void start(Stage primaryStage) throws Exception{
        TextField inputMoves = new TextField();
        Button startButton = new Button("Run");
        VBox inputBox = new VBox(inputMoves, startButton);
        VBox appBox = new VBox(this.gridMap, inputBox);

        gridMap.setAlignment(Pos.CENTER);
        inputBox.setAlignment(Pos.CENTER);
        appBox.setAlignment(Pos.CENTER);
        inputMoves.setMaxWidth(100);
        inputMoves.setFont(new Font(16));

        drawMap(true);
        Scene scene = new Scene(appBox, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction(event -> {
            String[] args = inputMoves.getText().split(" ");
            MoveDirection[] directions = OptionsParser.parse(args);
            engine.setMoves(directions);
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });
    }
}
