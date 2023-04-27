package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


import java.util.Collection;

public class PuzzleView implements FXComponent{
    private ControllerImpl controller;

    public PuzzleView(ControllerImpl controller){
        this.controller = controller;
    }
    @Override
    public Parent render() {
        GridPane layout = new GridPane();
        Puzzle currentPuzzle = controller.getActivePuzzle();
        layout.getStyleClass().add("board");
        int columns = currentPuzzle.getWidth();
        int rows = currentPuzzle.getHeight();
        Button corridor = new Button();
        layout.getChildren().add(corridor);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(currentPuzzle.getCellType(i, j) == CellType.CORRIDOR){
                Button corridorButton = new Button();
                    int finalI = i;
                    int finalJ = j;
                    corridorButton.setOnAction((ActionEvent event) -> {
                    controller.clickCell(finalI, finalJ);
                });
                    layout.add(corridorButton, j, i);
                    if(controller.isLit(i, j)){
                        if(controller.isLamp(i, j)){
                            if(controller.isLampIllegal(i, j)){
                                Label illegal = new Label("X");
                                illegal.getStyleClass().add("lit");
                                layout.add(illegal, j, i);
                            } else {
                            Label lit = new Label("L");
                            lit.getStyleClass().add("lit");
                            layout.add(lit, j, i);
                                     }
                        }
                        else{
                            Label lit = new Label("   ");
                            lit.getStyleClass().add("lit");
                            layout.add(lit, j, i);
                        }

                    }

            }
                else if(controller.getActivePuzzle().getCellType(i, j) == CellType.CLUE){
                    Label tile =  new Label(String.valueOf(controller.getActivePuzzle().getClue(i, j)));
                    if(controller.isClueSatisfied(i, j)){
                        tile.getStyleClass().add("solved-clue");
                    }
                    layout.add(tile, j, i);
                }
                else{
                    Label wall = new Label("   ");
                    wall.getStyleClass().add("wall");
                    layout.add(wall, j, i);

                }
        }
    }
        return layout;
}

}
