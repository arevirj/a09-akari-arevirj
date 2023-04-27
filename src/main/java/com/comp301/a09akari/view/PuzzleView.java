package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PuzzleView implements FXComponent {
  private final ControllerImpl controller;

  public PuzzleView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane layout = new GridPane();
    Puzzle currentPuzzle = controller.getActivePuzzle();
    layout.getStyleClass().add("board");
    int columns = currentPuzzle.getWidth();
    int rows = currentPuzzle.getHeight();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (currentPuzzle.getCellType(i, j) == CellType.CORRIDOR) {
          Button corridorButton = new Button("  ");
          int finalI = i;
          int finalJ = j;
          corridorButton.setOnAction(
              (ActionEvent event) -> {
                controller.clickCell(finalI, finalJ);
              });

          corridorButton.getStyleClass().add("corridor");
          layout.add(corridorButton, j, i);
          if (controller.isLit(i, j)) {
            if (controller.isLamp(i, j)) {
              if (controller.isLampIllegal(i, j)) {
                corridorButton.setText("X");
                corridorButton.getStyleClass().add("lit");
              } else {
                ImageView img = new ImageView(new Image("light-bulb.png"));
                img.setFitHeight(8.0);
                img.setFitWidth(8.0);
                corridorButton.setText("");
                corridorButton.setGraphic(img);
                corridorButton.getStyleClass().add("lit");
              }
            } else {
              corridorButton.getStyleClass().add("lit");
            }
          }

        } else if (controller.getActivePuzzle().getCellType(i, j) == CellType.CLUE) {
          Label tile = new Label(String.valueOf(controller.getActivePuzzle().getClue(i, j)));
          if (controller.isClueSatisfied(i, j)) {
            tile.getStyleClass().add("solved-clue");
          } else {
            tile.getStyleClass().add("clue");
          }
          layout.add(tile, j, i);
        } else {
          Label wall = new Label("   ");
          wall.getStyleClass().add("wall");
          layout.add(wall, j, i);
        }
      }
    }
    return layout;
  }
}
