package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;

public class ControlView implements FXComponent{
        private ControllerImpl controller;

        public ControlView(ControllerImpl controller){
            this.controller = controller;
        }
    @Override
    public Parent render() {
        Pane layout = new HBox();
        layout.getStyleClass().add("controls-layout");
        Button resetButton = new Button("Reset");
        resetButton.setOnAction((ActionEvent event) -> {
            controller.clickResetPuzzle();
        }
    );

        Button nextButton = new Button("Next");
        nextButton.setOnAction((ActionEvent event) -> {
            controller.clickNextPuzzle();
        });

        Button previousButton = new Button("Previous");
        previousButton.setOnAction((ActionEvent event) -> {
            controller.clickPrevPuzzle();
        });


        Button randButton = new Button("Random");
        randButton.setOnAction((ActionEvent event) -> {
            controller.clickRandPuzzle();
        });
        layout.getChildren().addAll(nextButton, previousButton, resetButton, randButton);

        return layout;
    }
}
