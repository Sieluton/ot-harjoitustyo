package sudoku.ui;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sudoku.domain.SudokuGrid;
import java.util.*;


public class SudokuUi extends Application {
    public SudokuGrid sudokuGrid = new SudokuGrid("295743861431865927876192543387459216612387495549216738763524189928671354154938672");
    public Canvas canvas;
    public Scene gameScene;
    public Scene menuScene;
    public Stage window;
    public int selected_row = -1;
    public int selected_column = -1;
    public int key_typed = -1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Sudoku");

        // New game button to start a new game
        Button newGameButton = new Button("New Game");

        // Load game button to load old game
        Button loadGameButton = new Button("Load Game");

        // Exit game button to close the game
        Button exitGameButton = new Button("Exit");
        exitGameButton.setOnAction(e -> window.close());

        // Create VBox to use as menu layout
        VBox menuLayout = new VBox();
        menuLayout.setSpacing(10);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(newGameButton, loadGameButton, exitGameButton);
        menuScene = new Scene(menuLayout, 150,200);


        // Save button saves game to database
        Button saveGameButton = new Button("Save");
        // savegame

        // Checks if sudoku is complete and opens alert box if completed
        Button checkGameButton = new Button("Check");
        checkGameButton.setOnAction(e -> checkGameAlertBox());

        // Back button and its functionality
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> window.setScene(menuScene));

        // Create gridpane for game windows layout
        GridPane gameLayout = new GridPane();
        gameLayout.setVgap(10);
        gameLayout.setHgap(10);

        // Create VBox and insert buttons to it that is then added to game windows layout
        VBox gameSceneButtonLayout = new VBox();
        gameSceneButtonLayout.setSpacing(15);
        gameSceneButtonLayout.setAlignment(Pos.CENTER);
        gameSceneButtonLayout.getChildren().addAll(checkGameButton, saveGameButton, backButton);

        // Create canvas and it's requirements
        canvas = new Canvas(360, 360);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2.0);
        drawOnCanvas(gc);

        // Adding elements to game windows layout
        GridPane.setConstraints(canvas, 0, 0);
        GridPane.setConstraints(gameSceneButtonLayout, 1, 0);
        gameLayout.getChildren().addAll(canvas, gameSceneButtonLayout);

        // Adding the game windows layout to gameScene
        gameScene = new Scene(gameLayout, 435,360, Color.GREY);

        // Allows for choosing which square to edit
        selectSquare();

        // Allows for the game to get user input
        keyPressed();
        window.setScene(menuScene);
        window.show();
    }

    public void checkGameAlertBox() {
        Stage box = new Stage();
        box.initModality(Modality.APPLICATION_MODAL);
        box.setMinWidth(200);
        box.setMinHeight(100);

        Label label = new Label();
        label.setText("This sudoku is not solved");
        if (!sudokuGrid.checkWholeSudoku(true)) {
            label.setText("You solved this sudoku!");
        }
        Button closeButton = new Button("Close the window");
        closeButton.setOnAction(e -> box.close());

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        box.setScene(scene);
        box.showAndWait();
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
        gameScene.setOnKeyPressed(e -> {
            if (e.getCode().isKeypadKey() || e.getCode().isDigitKey()){
                key_typed = Integer.parseInt(e.getText());
                if (selected_row != -1 && selected_column != -1) {
                    sudokuGrid.setNumber(selected_row, selected_column, key_typed);
                    sudokuGrid.checkWholeSudoku(false);
                }
                drawOnCanvas(canvas.getGraphicsContext2D());
            }
        });
    }
}
