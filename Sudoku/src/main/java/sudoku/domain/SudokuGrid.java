package sudoku.domain;

public class SudokuGrid {
    int[][] grid;
    boolean[][] illegalNumbers;

    // Creates empty grid if input is not accepted by regex
    public SudokuGrid(String numbers) {
        grid = new int[9][9];
        illegalNumbers = new boolean[9][9];
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
    public int[][] getGrid() {
        return grid;
    }

    // Sets specific number in grid
    public void setNumber(int y, int x, int number) {
        grid[y][x] = number;
    }

    // Returns specific number from grid
    public int getNumber(int y, int x) {
        return grid[y][x];
    }

    // Return true if number is breaking rules
    public boolean getLegalStatus(int y, int x) {
        return illegalNumbers[y][x];
    }

    // Checks if row contains only one of each number (1-9), 0 is empty space in row.
    public boolean checkRow(int y) {
        int[] amounts = new int[10];
        boolean faulty = true;
        // Fills amounts[] with amount of each number on this row
        for (int x  = 0; x < 9; x++) {
            amounts[grid[y][x]]++;
        }
        // Checks if any number in row is present more than once and adds it to illegalNumbers as true
        for (int x  = 0; x < 9; x++) {
            if (amounts[grid[y][x]] > 1 && grid[y][x] != 0) {
                faulty = false;
                illegalNumbers[y][x] = true;
            }
        }
        return faulty;
    }

    // Checks if column contains only one of each number (1-9), 0 is empty space in column.
    public boolean checkColumn(int x) {
        int[] amounts = new int[10];
        boolean faulty = true;
        // Fills amounts[] with amount of each number on this column
        for (int y  = 0; y < 9; y++) {
            amounts[grid[y][x]]++;
        }
        // Checks if any number in column is present more than once and adds it to illegalNumbers as true
        for (int y  = 0; y < 9; y++) {
            if (amounts[grid[y][x]] > 1 && grid[y][x] != 0) {
                faulty = false;
                illegalNumbers[y][x] = true;
            }
        }
        return faulty;
    }

    // Checks if 3x3 box contains only one of each number (1-9), 0 is empty space in box.
    public boolean checkBox(int i, int j) {
        int[] amounts = new int[10];
        boolean faulty = true;
        // Fills amounts[] with amount of each number on this box
        for (int y = i * 3; y < i * 3 + 3; y++) {
            for (int x = j * 3; x < j * 3 + 3; x++) {
                amounts[grid[y][x]]++;
            }
        }
        // Checks if any number between 1-9 is present more than once
        for (int y = i * 3; y < i * 3 + 3; y++) {
            for (int x = j * 3; x < j * 3 + 3; x++) {
                if (amounts[grid[y][x]] > 1 && grid[y][x] != 0) {
                    faulty = false;
                    illegalNumbers[y][x] = true;
                }
            }
        }
        return faulty;
    }

    public boolean checkWholeSudoku() {
        illegalNumbers = new boolean[9][9];
        boolean faulty = true;
        for (int i = 0; i < 9; i++) {
            if (!checkColumn(i)) {
                faulty = checkColumn(i);
            }
            if (!checkRow(i)) {
                faulty = checkRow(i);
            }
            for (int j = 0; j < 3; j++) {
                if (i < 3) {
                    if (!checkBox(i, j)) {
                        faulty = checkBox(i, j);
                    }
                }
            }
        }
        return faulty;
    }
}
