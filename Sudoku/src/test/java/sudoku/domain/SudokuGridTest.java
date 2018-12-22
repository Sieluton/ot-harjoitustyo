package sudoku.domain;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class SudokuGridTest {

    @Test
    public void equalWhenEmptySudoku(){
        int[][] grid1 = new SudokuGrid().getGrid();
        int[] grid2 = new int[]{0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < 9; i++){
            assertTrue(Arrays.equals(grid1[i],grid2));
        }
    }

    @Test
    public void falseWhenCheckWholeSudokuGivenCompleteSudoku(){
        SudokuGrid grid = new SudokuGrid();
        int[][] grid2 = new int[][]{
                {2,9,5,7,4,3,8,6,1},
                {4,3,1,8,6,5,9,2,7},
                {8,7,6,1,9,2,5,4,3},
                {3,8,7,4,5,9,2,1,6},
                {6,1,2,3,8,7,4,9,5},
                {5,4,9,2,1,6,7,3,8},
                {7,6,3,5,2,4,1,8,9},
                {9,2,8,6,7,1,3,5,4},
                {1,5,4,9,3,8,6,7,2},
        };
        grid.setGrid(grid2);
        assertFalse(grid.checkWholeSudoku(true));
    }

    @Test
    public void trueWhenCheckWholeSudokuGivenIncompleteSudoku(){
        SudokuGrid grid = new SudokuGrid();
        int[][] grid2 = new int[][]{
                {0,9,5,7,4,3,8,6,1},
                {4,3,1,8,6,5,9,2,7},
                {8,7,6,1,9,2,5,4,3},
                {3,8,7,4,5,9,2,1,6},
                {6,1,2,3,8,7,4,9,5},
                {5,4,9,2,1,6,7,3,8},
                {7,6,3,5,2,4,1,8,9},
                {9,2,8,6,7,1,3,5,4},
                {1,5,4,9,3,8,6,7,2},
        };
        grid.setGrid(grid2);
        assertTrue(grid.checkWholeSudoku(true));
    }

    @Test
    public void falseWhenCheckForEmptySpacesGivenSudokuWithEmptySpace(){
        SudokuGrid grid = new SudokuGrid();
        int[][] grid2 = new int[][]{
                {0,9,5,7,4,3,8,6,1},
                {4,3,1,8,6,5,9,2,7},
                {8,7,6,1,9,2,5,4,3},
                {3,8,7,4,5,9,2,1,6},
                {6,1,2,3,8,7,4,9,5},
                {5,4,9,2,1,6,7,3,8},
                {7,6,3,5,2,4,1,8,9},
                {9,2,8,6,7,1,3,5,4},
                {1,5,4,9,3,8,6,7,2},
        };
        grid.setGrid(grid2);
        assertFalse(grid.checkForEmptySpaces());
    }

    @Test
    public void trueWhenCheckForEmptySpacesGivenSudokuWithoutEmptySpace(){
        SudokuGrid grid = new SudokuGrid();
        int[][] grid2 = new int[][]{
                {2,9,5,7,4,3,8,6,1},
                {4,3,1,8,6,5,9,2,7},
                {8,7,6,1,9,2,5,4,3},
                {3,8,7,4,5,9,2,1,6},
                {6,1,2,3,8,7,4,9,5},
                {5,4,9,2,1,6,7,3,8},
                {7,6,3,5,2,4,1,8,9},
                {9,2,8,6,7,1,3,5,4},
                {1,5,4,9,3,8,6,7,2},
        };
        grid.setGrid(grid2);
        assertTrue(grid.checkForEmptySpaces());
    }
}
