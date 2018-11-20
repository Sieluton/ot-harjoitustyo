package sudoku.domain;

public class SudokuGrid {
    int[][] grid;

    public SudokuGrid(String numbers){
        grid = new int[9][9];
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

    public int[][] getGrid(){
        return grid;
    }

}
