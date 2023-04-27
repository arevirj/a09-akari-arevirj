package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MessageView implements FXComponent {
  private final ControllerImpl controller;

  public MessageView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Pane layout = new HBox();
    Pane layout1 = new HBox();
    Pane layout2 = new HBox();
    layout.getStyleClass().add("message");
    Label puzzleIndexMessage =
        new Label(
            "Puzzle "
                + (controller.getActivePuzzleIndex() + 1)
                + " of "
                + controller.getPuzzleLibrarySize());
    layout1.getChildren().add(puzzleIndexMessage);
    if (controller.isSolved()) {
      Label complete = new Label("   Congratulations! Puzzle Solved!");
      layout2.getChildren().add(complete);
    } else {
      Label inProgress = new Label("    ...");
      layout2.getChildren().add(inProgress);
    }
    layout.getChildren().addAll(layout1, layout2);
    return layout;
  }
}
