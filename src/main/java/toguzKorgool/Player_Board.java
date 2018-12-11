package toguzKorgool;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Player_Board extends Application {


    private static ArrayList<Hole> buttons = new ArrayList<>();
    private static BorderPane pane = new BorderPane();
    private static GridPane board = new GridPane();

    public Player_Board(){
        initializeButtons();
        System.out.println(buttons.size() + "the size of the buttons array");
    }

    public static void launch() {
        Application.launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        pane.setMaxSize(1400, 600);

        setColumnWidth();

        setRowSize();

        primaryStage.setTitle("Toguz Korgool");

        board.setAlignment(Pos.CENTER);

        board.setPrefSize(1400, 600);

        board.setGridLinesVisible(true);

        board.setVgap(10);
        board.setHgap(10);

        updateBoard();


        board.setStyle("-fx-background-color: transparent;");
        pane.setCenter(board);

        Image image = new Image("file:./src/main/java/toguzKorgool/board.jpg");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        pane.setBackground(new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));


        //ImagePattern pattern = new ImagePattern(new Image("file:./src/main/java/toguzKorgool/board.jpg"));

        Scene scene = new Scene(pane, 1200, 600);
        //scene.setFill(pattern);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

    }

    private static void setColumnWidth() {
        for (int i = 0; i < 9; i++) {
            board.getColumnConstraints().add(new ColumnConstraints(111));
        }
    }

    private static void setRowSize() {
        for (int i = 0; i < 5; i++) {
            if (i == 0 || i == 4) {
                board.getRowConstraints().add(new RowConstraints(50));
            } else board.getRowConstraints().add(new RowConstraints(150));
        }
    }


    private static void initializeButtons(){
        for (int i = 0; i < FileEditor.getDataList().size(); i++) {
            if (i < 10) {
                buttons.add(new Hole(FileEditor.getPlayer().get(i), FileEditor.getDataList().get(i), i));
            } else buttons.add(new Hole(FileEditor.getPlayer().get(i), FileEditor.getDataList().get(i), i));
        }
    }

    public static void updateBoard() {
        board.getChildren().clear();
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).paintButton();
        }
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 9; c++) {
                if (r == 0) {
                    Label label = new Label(9 - c + "");
                    label.setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    label.setAlignment(Pos.CENTER);
                    board.add(label, c, r, 1, 1);
                } else if (r == 4) {
                    Label label = new Label(c + 1 + "");
                    label.setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    label.setAlignment(Pos.CENTER);
                    board.add(label, c, r, 1, 1);
                } else if (r == 1) {
                    buttons.get(9-c).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    board.add(buttons.get(9-c), c, r, 1, 1);
                } else if (r == 3) {
                    buttons.get(c + 11).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    board.add(buttons.get(c + 11), c, r, 1, 1);
                } else {
                    buttons.get(0).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    buttons.get(10).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    board.add(buttons.get(0), c, r, 4, 1);
                    board.add(buttons.get(10), 5, r, 4, 1);
                    break;
                }
            }
        }
        FileEditor.saveGame();
    }

    public static ArrayList getButtons() {
        return buttons;
    }


}
