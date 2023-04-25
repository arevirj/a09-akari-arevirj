package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    PuzzleLibrary samples = new PuzzleLibraryImpl();
    Puzzle puzzleOne = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
    Puzzle puzzleTwo = new PuzzleImpl(SamplePuzzles.PUZZLE_02);
    samples.addPuzzle(puzzleOne);
    samples.addPuzzle(puzzleTwo);
    Model gameModel = new ModelImpl(samples);

  }
}
