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
    public SudokuGrid sudokuGrid = new SudokuGrid("295743861431865927876192543387459216612387495549216738763524189928671354154938672");
    public Canvas canvas;
    public Scene scene;
    public int selected_row = -1;
    public int selected_column = -1;
    public int key_typed = -1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Sudoku");
        StackPane layout = new StackPane();
        canvas = new Canvas(360, 360);
        layout.getChildren().add(canvas);
        scene = new Scene(layout, 400,400, Color.GREY);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2.0);
        drawOnCanvas(gc);
        selectSquare(); // Allows for choosing which square to edit
        keyPressed();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void drawOnCanvas(GraphicsContext gc) {
        gc.clearRect(0,0,360,360);
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {
                int y = row * 40 + 2;
                int x = col * 40 + 2;
                int width = 36;

                gc.setFill(Color.LIGHTGRAY);
                if (row == selected_row && col == selected_column) {
                    gc.setFill(Color.LIGHTBLUE);
                }
                if (sudokuGrid.getLegalStatus(row, col)) {
                    gc.setFill(Color.LIGHTSALMON);
                }
                gc.fillRoundRect(x, y, width, width, 10, 10);
                gc.setFill(Color.BLACK);
                gc.setFont(new Font(20));
                if (sudokuGrid.getNumber(row, col) != 0) {
                    gc.fillText(sudokuGrid.getNumber(row, col) + "",x+13,y+25);
                }
            }
        }
        if (key_typed > -1) {
            key_typed = -1;
        }
    }

    public void selectSquare() {
        canvas.setOnMouseClicked(e ->  {
            selected_row = (int) e.getY() / 40;
            selected_column = (int) e.getX() / 40;
            drawOnCanvas(canvas.getGraphicsContext2D());
        });
    }

    public void keyPressed() {
        scene.setOnKeyPressed(e -> {
            if (e.getCode().isKeypadKey() || e.getCode().isDigitKey()){
                key_typed = Integer.parseInt(e.getText());
                if (selected_row != -1 && selected_column != -1) {
                    sudokuGrid.setNumber(selected_row, selected_column, key_typed);
                    sudokuGrid.checkWholeSudoku();
                }
                drawOnCanvas(canvas.getGraphicsContext2D());
            }
        });
    }
}
