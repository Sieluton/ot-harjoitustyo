package sudoku.ui;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sudoku.domain.SudokuGrid;

/**
 * Käyttöliittymä
 */
public class SudokuUi extends Application {
    private SudokuGrid sudokuGrid = new SudokuGrid();
    private Canvas canvas;
    private Scene gameScene;
    private Scene menuScene;
    private Stage window;
    private int selected_row = -1;
    private int selected_column = -1;
    private int key_typed = -1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Sudoku");

        // New game button to start a new game
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> newGameAlertBox());

        // Load game button to load old game
        Button loadGameButton = new Button("Load Game");
        loadGameButton.setOnAction(e -> loadGameAlertBox());

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
        saveGameButton.setOnAction(e -> saveGameAlertBox());

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

    /**
     * Luo uuden ponnahdusikkunan, jossa kerrotaan onko sudoku ratkaistu.
     */
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

    /**
     * Luo uuden ponnahdusikkunan, josta voi valita minkälaisen pelin haluaa luoda.
     */
    public void newGameAlertBox() {
        Stage box = new Stage();
        box.initModality(Modality.APPLICATION_MODAL);
        box.setMinWidth(200);
        box.setMinHeight(250);

        Button emptySudokuButton = new Button("Empty Sudoku");
        emptySudokuButton.setOnAction(e -> {
            sudokuGrid.getNew(0);
            drawOnCanvas(canvas.getGraphicsContext2D());
            window.setScene(gameScene);
            box.close();
        });

        Button easySudokuButton = new Button("Easy Sudoku");
        easySudokuButton.setOnAction(e -> {
            sudokuGrid.getNew(1);
            drawOnCanvas(canvas.getGraphicsContext2D());
            window.setScene(gameScene);
            box.close();
        });

        Button mediumSudokuButton = new Button("Medium Sudoku");
        mediumSudokuButton.setOnAction(e -> {
            sudokuGrid.getNew(2);
            drawOnCanvas(canvas.getGraphicsContext2D());
            window.setScene(gameScene);
            box.close();
        });

        Button hardSudokuButton = new Button("Hard Sudoku");
        hardSudokuButton.setOnAction(e -> {
            sudokuGrid.getNew(3);
            drawOnCanvas(canvas.getGraphicsContext2D());
            window.setScene(gameScene);
            box.close();
        });

        Button closeButton = new Button("Back");
        closeButton.setOnAction(e -> box.close());

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(emptySudokuButton, easySudokuButton, mediumSudokuButton, hardSudokuButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        box.setScene(scene);
        box.showAndWait();
    }

    /**
     * Luo uuden ponnahdusikkunan, josta voi valita minkä aikaisemman pelin haluaa ladata.
     */
    public void loadGameAlertBox() {
        Stage box = new Stage();
        box.initModality(Modality.APPLICATION_MODAL);
        box.setMinWidth(150);
        box.setMinHeight(300);

        Button saveSlot1Button = new Button("Slot 1");
        saveSlot1Button.setOnAction(e -> {
            sudokuGrid.getSave(1);
            drawOnCanvas(canvas.getGraphicsContext2D());
            window.setScene(gameScene);
            box.close();
        });

        Button saveSlot2Button = new Button("Slot 2");
        saveSlot2Button.setOnAction(e -> {
            sudokuGrid.getSave(2);
            drawOnCanvas(canvas.getGraphicsContext2D());
            window.setScene(gameScene);
            box.close();
        });


        Button saveSlot3Button = new Button("Slot 3");
        saveSlot3Button.setOnAction(e -> {
            sudokuGrid.getSave(3);
            drawOnCanvas(canvas.getGraphicsContext2D());
            window.setScene(gameScene);
            box.close();
        });


        Button saveSlot4Button = new Button("Slot 4");
        saveSlot4Button.setOnAction(e -> {
            sudokuGrid.getSave(4);
            drawOnCanvas(canvas.getGraphicsContext2D());
            window.setScene(gameScene);
            box.close();
        });


        Button saveSlot5Button = new Button("Slot 5");
        saveSlot5Button.setOnAction(e -> {
            sudokuGrid.getSave(5);
            drawOnCanvas(canvas.getGraphicsContext2D());
            window.setScene(gameScene);
            box.close();
        });


        Button closeButton = new Button("Back");
        closeButton.setOnAction(e -> box.close());

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(saveSlot1Button, saveSlot2Button, saveSlot3Button, saveSlot4Button, saveSlot5Button, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        box.setScene(scene);
        box.showAndWait();
    }

    /**
     * Luo uuden ponnahdusikkunan, josta voi valita mihin talletuspaikkaan haluaa sudokun tallettaa.
     */
    public void saveGameAlertBox() {
        Stage box = new Stage();
        box.initModality(Modality.APPLICATION_MODAL);
        box.setMinWidth(150);
        box.setMinHeight(300);

        Button saveSlot1Button = new Button("Slot 1");
        saveSlot1Button.setOnAction(e -> {
            sudokuGrid.saveSudoku(1);
            box.close();
        });

        Button saveSlot2Button = new Button("Slot 2");
        saveSlot2Button.setOnAction(e -> {
            sudokuGrid.saveSudoku(2);
            box.close();
        });


        Button saveSlot3Button = new Button("Slot 3");
        saveSlot3Button.setOnAction(e -> {
            sudokuGrid.saveSudoku(3);
            box.close();
        });


        Button saveSlot4Button = new Button("Slot 4");
        saveSlot4Button.setOnAction(e -> {
            sudokuGrid.saveSudoku(4);
            box.close();
        });


        Button saveSlot5Button = new Button("Slot 5");
        saveSlot5Button.setOnAction(e -> {
            sudokuGrid.saveSudoku(5);
            box.close();
        });


        Button closeButton = new Button("Back");
        closeButton.setOnAction(e -> box.close());

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(saveSlot1Button, saveSlot2Button, saveSlot3Button, saveSlot4Button, saveSlot5Button, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        box.setScene(scene);
        box.showAndWait();
    }

    /**
     * Metodi hoitaa sudoku ruudukon piirtämisen ja sen muokkaamisen aina kun sitä tapahtuu.
     * @param gc GraphicsContext olio joka tekee piirtämisen
     */
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
                if (sudokuGrid.getLegalStatus(row, col) == 1) {
                    gc.setFill(Color.LIGHTSALMON);
                }
                if (sudokuGrid.isInitial(row, col)) {
                    gc.setFill(Color.DARKGRAY);
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

    /**
     * Kertoo mikä kohta on klikattu sudoku ruudukossa.
     */
    public void selectSquare() {
        canvas.setOnMouseClicked(e ->  {
            selected_row = (int) e.getY() / 40;
            selected_column = (int) e.getX() / 40;
            if (e.getButton() == MouseButton.SECONDARY) {
                sudokuGrid.setInitial(selected_row, selected_column);
            }
            drawOnCanvas(canvas.getGraphicsContext2D());
        });
    }

    /**
     * Kertoo mikä numero on syötetty näppäimistöllä sudokulle.
     */
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
