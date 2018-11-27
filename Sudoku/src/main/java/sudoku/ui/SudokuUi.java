package sudoku.ui;
// 295743861431865927876192543389459216612387495549216738763534189928671354154938672
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class SudokuUi extends Application {
    private static final int RECT_HEIGHT = 30;
    private static final int RECT_WIDTH = 30;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Sudoku");
        StackPane layout = new StackPane();
        Canvas canvas = new Canvas(378, 378);
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
                int position_y = row * 40 + 2;
                int position_x = col * 40 + 2;
                int width = 36;

                gc.setFill(Color.LIGHTGRAY);
                gc.fillRoundRect(position_x, position_y, width, width, 10, 10);
            }
        }
    }
}
