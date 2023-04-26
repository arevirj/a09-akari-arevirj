package com.comp301.a09akari;

import com.comp301.a09akari.model.*;
import com.comp301.a09akari.view.AppLauncher;
import javafx.application.Application;

public class Main {
  public static void main(String[] args) {
    //Application.launch(AppLauncher.class);
    PuzzleLibrary samples = new PuzzleLibraryImpl();
//    Puzzle puzzleOne = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
//    Puzzle puzzleTwo = new PuzzleImpl(SamplePuzzles.PUZZLE_02);
//    samples.addPuzzle(puzzleOne);
//    samples.addPuzzle(puzzleTwo);
    int[][] sampBoard = {{6,6}, {6,3}, {6,6}};
    samples.addPuzzle(new PuzzleImpl(sampBoard));
    Model gameModel = new ModelImpl(samples);
    gameModel.addLamp(0,1);
    gameModel.addLamp(1,0);
    gameModel.addLamp(2,1);

    System.out.println(gameModel.isSolved());
  }
}
