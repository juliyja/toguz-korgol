package toguzKorgool;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class Player_Board extends Application {


    private static ArrayList<Hole> buttons = new ArrayList<>();
    private static BorderPane pane = new BorderPane();
    private static GridPane board = new GridPane();

    public Player_Board() throws IOException {
        initializeButtons();
        System.out.println(buttons.size() + "the size of the buttons array");
    }

    public static void launch() {
        Application.launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        pane.setMaxSize(1400, 600);
        makeTopMenu();
        makeBottomMenu();


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


    private static void initializeButtons() throws IOException {
        for (int i = 0; i < File_editor.getDataList().size(); i++) {
            if (i < 10) {
                buttons.add(new Hole(true, File_editor.getDataList().get(i), i));
            } else buttons.add(new Hole(false, File_editor.getDataList().get(i), i));
        }
    }

    public static void updateBoard() throws IOException {
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
                    buttons.get(c + 1).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    board.add(buttons.get(c + 1), c, r, 1, 1);
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
        File_editor.saveGame();
    }

    public static void main(String[] args) throws IOException {
        //Player_Board playerBoard = new Player_Board();
        Application.launch();
    }

    public static ArrayList getButtons() {
        return buttons;
    }

    private static void makeTopMenu() {
        MenuBar topMenu = new MenuBar();
        Menu gameMenu = new Menu("Game");
        MenuItem save = new MenuItem("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Save");
            }
        });
        MenuItem rules = new MenuItem("Rules");
        rules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Rules");
            }
        });
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Exit");
            }
        });
        gameMenu.getItems().addAll(save, rules, exit);

        Menu aboutMenu = new Menu("About");
        MenuItem aboutGame = new MenuItem("The Game");
        aboutGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("About the game");
            }
        });
        MenuItem aboutAuthors = new MenuItem("Authors");
        aboutAuthors.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("About the authors");
            }
        });
        aboutMenu.getItems().addAll(aboutGame, aboutAuthors);

        topMenu.getMenus().addAll(gameMenu, aboutMenu);
        pane.setTop(topMenu);
    }


    private static void makeBottomMenu() {
        MenuBar bottomMenu = new MenuBar();

        pane.setBottom(bottomMenu);
    }
}
