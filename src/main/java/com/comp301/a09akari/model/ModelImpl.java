package com.comp301.a09akari.model;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model{
    private PuzzleLibrary library;

    private Puzzle currentPuzzle;
    private int currentIndex;
    private List<ModelObserver> observers;

    private ArrayList<Point> lamps;
    public ModelImpl(PuzzleLibrary library){
        this.library = library;
        this.currentIndex = 0;
        this.currentPuzzle = library.getPuzzle(0);
        this.observers = null;
        this.lamps = new ArrayList<>();
    }
    @Override
    public void addLamp(int r, int c) {
        if(r < 0 || c < 0 || r > currentPuzzle.getHeight() || c > currentPuzzle.getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(currentPuzzle.getCellType(r, c) != CellType.CORRIDOR){
            throw new IllegalArgumentException("Cell Type is not Corridor");
        }
        lamps.add(new Point(c, r));

    }

    @Override
    public void removeLamp(int r, int c) {
        if(r < 0 || c < 0 || r > currentPuzzle.getHeight() || c > currentPuzzle.getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(currentPuzzle.getCellType(r, c) != CellType.CORRIDOR){
            throw new IllegalArgumentException("Cell Type is not Corridor");
        }
        lamps.removeIf(p -> p.getX() == c && p.getY() == r);
    }

    @Override
    public boolean isLit(int r, int c) {
        if(r < 0 || c < 0 || r > currentPuzzle.getHeight() || c > currentPuzzle.getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(currentPuzzle.getCellType(r, c) != CellType.CORRIDOR){
            throw new IllegalArgumentException("Cell Type is not Corridor");
        }
        if(isLamp(r,c)){
            return true;
        }
        else{
            return searchLamp(r, c);
        }
    }

    @Override
    public boolean isLamp(int r, int c) {
        if(r < 0 || c < 0 || r > currentPuzzle.getHeight() || c > currentPuzzle.getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(currentPuzzle.getCellType(r, c) != CellType.CORRIDOR){
            throw new IllegalArgumentException("Cell Type is not Corridor");
        }
        Point lamp = new Point(c, r);
        return lamps.contains(lamp);
    }

    @Override
    public boolean isLampIllegal(int r, int c) {
        if(r < 0 || c < 0 || r > currentPuzzle.getHeight() || c > currentPuzzle.getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(isLamp(r, c)) { return searchLamp(r, c);}
        else{return false;}
  }

  @Override
  public Puzzle getActivePuzzle() {
        return currentPuzzle;
    }

    @Override
    public int getActivePuzzleIndex() {
        return currentIndex;
    }

    @Override
    public void setActivePuzzleIndex(int index) {
        if(index < 0 || index >= getPuzzleLibrarySize()){
            throw new IllegalArgumentException();
        }
        else{
            this.currentPuzzle = library.getPuzzle(index);
            this.currentIndex = index;
        }
    }

    @Override
    public int getPuzzleLibrarySize() {
        return library.size();
    }

    @Override
    public void resetPuzzle() {
        lamps.clear();
    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        if(r < 0 || c < 0 || r > currentPuzzle.getHeight() || c > currentPuzzle.getWidth()){
            throw new IndexOutOfBoundsException();
        }
        return false;
    }

    @Override
    public void addObserver(ModelObserver observer) {

    }

    @Override
    public void removeObserver(ModelObserver observer) {

    }

    public boolean searchLamp(int r, int c){
        if(currentPuzzle.getHeight() == 1 && currentPuzzle.getWidth() == 1){
            return(isLamp(r,c));
        }
        //Looking Left
        if (c > 0) {
            int rowIndex = c-1;
            while (rowIndex >= 0 && currentPuzzle.getCellType(r, rowIndex) == CellType.CORRIDOR) {
                if (isLamp(r, rowIndex)) {
                    return true;
                }
                rowIndex--;
            }
        }
        //Looking Right
        if(c < currentPuzzle.getWidth() - 1){
            int rowIndex = c + 1;
            while(rowIndex <= currentPuzzle.getWidth() - 1 && currentPuzzle.getCellType(r, rowIndex) == CellType.CORRIDOR){
                if(isLamp(r, rowIndex)){
                    return true;
                }
                rowIndex++;
            }
        }
        //Looking up
        if(r > 0){
            int colIndex = r - 1;
            while (colIndex >= 0 && currentPuzzle.getCellType(colIndex, c) == CellType.CORRIDOR) {
                if (isLamp(colIndex, c)) {
                    return true;
                }
                colIndex--;
            }
        }
        //Looking Down
        if(r < currentPuzzle.getHeight() - 1){
            int colIndex = r + 1;
            while(colIndex <= currentPuzzle.getHeight() -1 && currentPuzzle.getCellType(colIndex, c) == CellType.CORRIDOR){
                if(isLamp(colIndex, c)){
                    return true;
                }
                colIndex++;
            }
        }
        return false;
    }
}
