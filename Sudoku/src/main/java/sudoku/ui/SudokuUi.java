package sudoku.ui;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sudoku.domain.SudokuGrid;

import java.util.*;

public class SudokuUi extends Application {
    public SudokuGrid sudokuGrid = new SudokuGrid("295743861431865927876192543389459216612387495549216738763534189928671354154938672");
    public Canvas canvas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Sudoku");
        StackPane layout = new StackPane();
        canvas = new Canvas(378, 378);
        layout.getChildren().add(canvas);
        Scene scene = new Scene(layout, 400,400, Color.GREY);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2.0);
        drawOnCanvas(gc);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawOnCanvas(GraphicsContext gc) {
        gc.clearRect(0,0,378,378);
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {
                int y = row * 40 + 2;
                int x = col * 40 + 2;
                int width = 36;

                gc.setFill(Color.LIGHTGRAY);
                gc.fillRoundRect(x, y, width, width, 10, 10);
                gc.setFill(Color.BLACK);
                gc.setFont(new Font(20));
                if (sudokuGrid.getNumber(row, col) != 0) {
                    gc.fillText(sudokuGrid.getNumber(row, col) + "",x+13,y+25);
                }
            }
        }
    }
}
