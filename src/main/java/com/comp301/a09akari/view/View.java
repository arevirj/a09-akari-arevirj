package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View implements ModelObserver {
  private final ControllerImpl controller;
  private final Stage stage;

  public View(ControllerImpl controller, Stage stage) {
    this.controller = controller;
    this.stage = stage;
    controller.addModelObserver(this);
  }

  public Parent render() {
    VBox layout = new VBox();

    // Message View
    MessageView message = new MessageView(controller);
    layout.getChildren().add(message.render());
    // Controls View
    ControlView controlButtons = new ControlView(controller);
    layout.getChildren().add(controlButtons.render());
    // Puzzle View
    PuzzleView puzzleBoard = new PuzzleView(controller);
    layout.getChildren().add(puzzleBoard.render());

    return layout;
  }

  @Override
  public void update(Model model) {
    Scene scene = new Scene(render());
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);
  }
}
