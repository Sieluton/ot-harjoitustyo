package sudoku.ui;

import sudoku.domain.SudokuGrid;

import java.util.*;

public class SudokuUi {

    public static void main(String[] args){
        SudokuGrid sudoku = new SudokuGrid("295743861431865927876192543389459216612387495549216738763534189928671354154938672");
        int[][] grid = sudoku.getGrid();
        for (int i = 0; i < 9; i++){
            System.out.println(Arrays.toString(grid[i]));
        }

    }
}
