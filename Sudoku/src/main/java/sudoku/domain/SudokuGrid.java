package sudoku.domain;

public class SudokuGrid {
    int[][] grid;

    // Creates empty grid if input is not accepted by regex
    public SudokuGrid(String numbers){
        grid = new int[9][9];
        // if input is 81 characters long and contains only numbers from 0-9 it is made
        if (numbers.matches("[0-9]{81}")) {
            int next = 0;
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    grid[y][x] = Character.getNumericValue(numbers.charAt(next));
                    next++;
                }
            }
        }
    }

    // Returns whole grid
    public int[][] getGrid(){
        return grid;
    }

    // Sets specific number in grid
    public void setNumber(int y, int x, int number){
        grid[y][x] = number;
    }

    // Returns specific number from grid
    public int getNumber(int y, int x){
        return grid[y][x];
    }

    // Checks if row contains only one of each number (1-9)
    public boolean checkRow(int y){
        for (int x  = 0; x < 9; x++){

        }
        return true;
    }

    // Checks if column contains only one of each number (1-9)
    public boolean checkColumn(int x){
        for (int y  = 0; y < 9; y++){

        }
        return true;
    }

}
