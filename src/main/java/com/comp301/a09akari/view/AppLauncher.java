package com.comp301.a09akari.view;

import com.comp301.a09akari.SampleLibrary;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    stage.setTitle("Akari!");
    PuzzleLibrary library = new SampleLibrary().library;
    Model gameModel = new ModelImpl(library);
    ControllerImpl controller = new ControllerImpl(gameModel);

    View view = new View(controller, stage);

    Scene scene = new Scene(view.render());
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);

    gameModel.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        });
    stage.show();
  }
}
