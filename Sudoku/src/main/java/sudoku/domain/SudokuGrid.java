package sudoku.domain;
import sudoku.dao.*;

/**
 * Luokka sudokun logiikkaa varten, jossa on tarkastus ja muokkaus metodeja.
 */
public class SudokuGrid {
    private int[][] grid;
    private int[][] illegalNumbers;
    private int[][] initial;
    private SudokuDao db;

    /**
     * Sudokun konstruktori.
     */
    public SudokuGrid() {
        db = new SudokuDao();
        db.createDB();
        grid = new int[9][9];
        illegalNumbers = new int[9][9];
        initial = new int[9][9];
    }

    /**
     * Muokkaa sudokun arvon annettuun numeroon.
     * @param y Y-koordinaatti
     * @param x X-koordinaatti
     * @param number sudokuun laitettava numero
     */
    public void setNumber(int y, int x, int number) {
        if (isInitial(y, x)) {
            return;
        }
        grid[y][x] = number;
    }

    /**
     * Kertoo on onko koordinaatti alkuarvo jota ei pidä muuttaa.
     * @param y Y-koordinaatti
     * @param x X-koordinaatti
     * @return true jos kyseinen koordinaatti on alkuarvo
     */
    public boolean isInitial(int y, int x) {
        if (initial[y][x] == 1) {
            return true;
        }
        return false;
    }

    /**
     * Muokkaa aina koordinaatin vastakkaiseksi arvoksi.
     * @param y Y-koordinaatti
     * @param x X-koordinaatti
     */
    public void setInitial(int y, int x) {
        if (isInitial(y ,x)) {
            initial[y][x] = 0;
        } else {
            initial[y][x] = 1;
        }
    }

    /**
     * Palauttaa annetussa koordinaatissa olevan sudokun ruudun arvon.
     * @param y Y-koordinaatti
     * @param x X-koordinaatti
     * @return sijainnin int
     */
    public int getNumber(int y, int x) {
        return grid[y][x];
    }

    /**
     * Palauttaa kyseisen koordinaatin tiedon rikkooko se sääntöjä.
     * @param y Y-koordinaatti
     * @param x X-koordinaatti
     * @return 0 tai 1
     */
    public int getLegalStatus(int y, int x) {
        return illegalNumbers[y][x];
    }

    /**
     * Palauttaa tiedon onko riveillä tai sarakkeilla sääntörikkeitä ja tallettaa.
     * ne illegalNumbers tauluun.
     * @param y koordinaatti
     * @return boolean arvo onko sääntörike
     */
    public boolean checkRowAndColumn(int y) {
        int[] row = new int[10];
        int[] column = new int[10];
        boolean faulty = false;
        // Fills row[] and column[] with amount of each number on this row and column
        for (int x  = 0; x < 9; x++) {
            row[grid[y][x]]++;
            column[grid[x][y]]++;
        }
        // Checks if any number in row or column is present more than once and adds it to illegalNumbers as true
        for (int x  = 0; x < 9; x++) {
            if (row[grid[y][x]] > 1 && grid[y][x] != 0) {
                faulty = true;
                illegalNumbers[y][x] = 1;
            }
            if (column[grid[x][y]] > 1 && grid[x][y] != 0) {
                faulty = true;
                illegalNumbers[x][y] = 1;
            }
        }
        return faulty;
    }

    /**
     * Tarkastaa onko 3x3 laatikossa sääntörike ja talleta rikkeet illegalNumbers tauluun.
     * @param i koordinaatti
     * @param j koordinaatti
     * @return boolean arvo onko sääntörike
     */
    public boolean checkBox(int i, int j) {
        int[] amounts = new int[10];
        boolean faulty = false;
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
                    faulty = true;
                    illegalNumbers[y][x] = 1;
                }
            }
        }
        return faulty;
    }

    /**
     * Tarkistaa koko sudokun sääntörikkeiltä apumetodeilla.
     * @param emptyCheck boolean arvo tarkastetaanko tyhjien määrä
     * @return boolean arvo onko sääntörikkeitä
     */
    public boolean checkWholeSudoku(boolean emptyCheck) {
        illegalNumbers = new int[9][9];
        boolean faulty = false;
        for (int i = 0; i < 9; i++) {
            if (checkRowAndColumn(i)) {
                faulty = true;
            }
            if (i < 3) {
                for (int j = 0; j < 3; j++) {
                    if (checkBox(i, j)) {
                        faulty = true;
                    }
                }
            }
        }
        if (emptyCheck && !checkForEmptySpaces()) {
            faulty = true;
        }
        return faulty;
    }

    /**
     * Tarkastaa onko sudokussa tyhjiä kohtia.
     * @return boolean arvo onko tyhjiä
     */
    public boolean checkForEmptySpaces() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (grid[y][x] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Lähettää haun tietokannan rajapinnalle ja pyytää talletuksen tietystä paikasta.
     * @param slot talletuspaikka
     */
    public void getSave(int slot) {
        String[] numbers = db.getSave(slot);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                grid[y][x] = Character.getNumericValue(numbers[0].charAt(y*9+x));
                illegalNumbers[y][x] = Character.getNumericValue(numbers[1].charAt(y*9+x));
                initial[y][x] = Character.getNumericValue(numbers[2].charAt(y*9+x));
            }
        }
    }

    /**
     * Lähettää haun tietokannan rajapinnalle ja pyytää talletuksen tietyllä vaikeudella.
     * @param difficulty vaikeusaste
     */
    public void getNew(int difficulty) {
        String[] numbers = db.getNew(difficulty);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                grid[y][x] = Character.getNumericValue(numbers[0].charAt(y*9+x));
                illegalNumbers[y][x] = Character.getNumericValue(numbers[1].charAt(y*9+x));
                initial[y][x] = Character.getNumericValue(numbers[2].charAt(y*9+x));
            }
        }
    }

    /**
     * Lähettää talletuspyynnön tietokannan rajapinnalle sudokun tallentamiseksi annettiin talletuspaikkaan.
     * @param slot talletuspaikka
     */
    public void saveSudoku(int slot) {
        String[] save = new String[3];
        String a = "";
        String b = "";
        String c = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                a += this.grid[y][x];
                b += this.illegalNumbers[y][x];
                c += this.initial[y][x];
            }
        }
        save[0] = a;
        save[1] = b;
        save[2] = c;
        db.saveSudoku(slot, save);
    }
}
