package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private final int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board.clone();
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || c < 0 || r >= getHeight() || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    int cellVal = board[r][c];
    if (cellVal <= 4) {
      return CellType.CLUE;
    } else if (cellVal == 5) {
      return CellType.WALL;
    } else {
      return CellType.CORRIDOR;
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || c < 0 || r >= getHeight() || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return board[r][c];
  }

  public int[][] getClone(){
    return board.clone();
  }
}
