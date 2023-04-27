package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import com.comp301.a09akari.model.Puzzle;
import java.util.Random;

public class ControllerImpl implements AlternateMvcController {
  private final Model gameModel;

  public ControllerImpl(Model model) {
    gameModel = model;
  }

  @Override
  public void clickNextPuzzle() {

    gameModel.setActivePuzzleIndex(
        (gameModel.getActivePuzzleIndex() + 1) % gameModel.getPuzzleLibrarySize());
  }

  @Override
  public void clickPrevPuzzle() {
    if (gameModel.getActivePuzzleIndex() == 0) {
      return;
    }
    gameModel.setActivePuzzleIndex(
        gameModel.getActivePuzzleIndex() - 1 % gameModel.getPuzzleLibrarySize());
  }

  @Override
  public void clickRandPuzzle() {
    Random rand = new Random();
    int current = getActivePuzzleIndex();
    int n = rand.nextInt(gameModel.getPuzzleLibrarySize());
    while (n == current) {
      n = rand.nextInt(gameModel.getPuzzleLibrarySize());
    }
    gameModel.setActivePuzzleIndex(n);
  }

  @Override
  public void clickResetPuzzle() {
    gameModel.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (gameModel.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (gameModel.isLamp(r, c)) {
        gameModel.removeLamp(r, c);
      } else {
        gameModel.addLamp(r, c);
      }
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    return gameModel.isLit(r, c);
  }

  @Override
  public boolean isLamp(int r, int c) {
    return gameModel.isLamp(r, c);
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    return gameModel.isClueSatisfied(r, c);
  }

  @Override
  public boolean isSolved() {
    return gameModel.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return gameModel.getActivePuzzle();
  }

  public int getActivePuzzleIndex() {
    return gameModel.getActivePuzzleIndex();
  }

  public boolean isLampIllegal(int r, int c) {
    return gameModel.isLampIllegal(r, c);
  }

  public void addModelObserver(ModelObserver observer) {
    gameModel.addObserver(observer);
  }

  public int getPuzzleLibrarySize() {
    return gameModel.getPuzzleLibrarySize();
  }
}
