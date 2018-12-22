package sudoku.dao;
import java.sql.*;

public class SudokuDao {

    public void createDB() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sudoku.db");
            System.out.println(conn);
            stmt = conn.createStatement();

            //stmt.executeUpdate("DROP TABLE SAVES;");
            //stmt.executeUpdate("DROP TABLE NEW;");

            String sql = "CREATE TABLE SAVES " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " SUDOKU TEXT NOT NULL, " +
                    " ILLEGAL TEXT NOT NULL, " +
                    " INITIAL TEXT NOT NULL) ";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE NEW " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " SUDOKU TEXT NOT NULL, " +
                    " ILLEGAL TEXT NOT NULL, " +
                    " INITIAL TEXT NOT NULL, " +
                    " DIFFICULTY INT NOT NULL) ";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO SAVES (ID,SUDOKU,ILLEGAL,INITIAL) " +
                    "VALUES (1, '295743861431865927876192543387459216612387495549216738763524189928671354154938672', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO SAVES (ID,SUDOKU,ILLEGAL,INITIAL) " +
                    "VALUES (2, '000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO SAVES (ID,SUDOKU,ILLEGAL,INITIAL) " +
                    "VALUES (3, '000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO SAVES (ID,SUDOKU,ILLEGAL,INITIAL) " +
                    "VALUES (4, '000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO SAVES (ID,SUDOKU,ILLEGAL,INITIAL) " +
                    "VALUES (5, '000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO NEW (ID,SUDOKU,ILLEGAL,INITIAL,DIFFICULTY) " +
                    "VALUES (0, '000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', 0);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO NEW (ID,SUDOKU,ILLEGAL,INITIAL,DIFFICULTY) " +
                    "VALUES (1, '000000000000000000000000000000000000000000000000000000000000000000000000000000001', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', 1);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO NEW (ID,SUDOKU,ILLEGAL,INITIAL,DIFFICULTY) " +
                    "VALUES (2, '200000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', 2);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO NEW (ID,SUDOKU,ILLEGAL,INITIAL,DIFFICULTY) " +
                    "VALUES (3, '300000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', " +
                    "'000000000000000000000000000000000000000000000000000000000000000000000000000000000', 3);";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] getSave(int slot) {
        String[] save = new String[3];
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sudoku.db");
            System.out.println(conn);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUDOKU, ILLEGAL, INITIAL FROM SAVES WHERE ID=" + slot + ";");
            save[0] = rs.getString("SUDOKU");
            save[1] = rs.getString("ILLEGAL");
            save[2] = rs.getString("INITIAL");
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return save;
    }

    public String[] getNew(int difficulty) {
        String[] save = new String[3];
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sudoku.db");
            System.out.println(conn);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUDOKU, ILLEGAL, INITIAL FROM NEW WHERE DIFFICULTY=" + difficulty + ";");
            save[0] = rs.getString("SUDOKU");
            save[1] = rs.getString("ILLEGAL");
            save[2] = rs.getString("INITIAL");
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return save;
    }

    public void saveSudoku(int slot, String[] save) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sudoku.db");
            System.out.println(conn);
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE SAVES SET SUDOKU='" + save[0] +
                    "', ILLEGAL='" + save[1] + "', INITIAL='" + save[2] + "' WHERE ID=" + slot + ";");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
