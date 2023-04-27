package com.comp301.a09akari;

import com.comp301.a09akari.model.Puzzle;
import com.comp301.a09akari.model.PuzzleImpl;
import com.comp301.a09akari.model.PuzzleLibrary;
import com.comp301.a09akari.model.PuzzleLibraryImpl;

public class SampleLibrary {
    public PuzzleLibrary library = new PuzzleLibraryImpl();

    public SampleLibrary(){
        Puzzle puzzleOne = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
        Puzzle puzzleTwo = new PuzzleImpl(SamplePuzzles.PUZZLE_02);
        Puzzle puzzleThree = new PuzzleImpl(SamplePuzzles.PUZZLE_03);
        Puzzle puzzleFour = new PuzzleImpl(SamplePuzzles.PUZZLE_04);
        Puzzle puzzleFive = new PuzzleImpl(SamplePuzzles.PUZZLE_05);
        library.addPuzzle(puzzleOne);
        library.addPuzzle(puzzleTwo);
        library.addPuzzle(puzzleThree);
        library.addPuzzle(puzzleFour);
        library.addPuzzle(puzzleFive);
    }



}
