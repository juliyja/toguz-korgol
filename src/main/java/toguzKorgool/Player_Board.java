package main.java.toguzKorgool;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;



//TODO: CLOSES WHEN PRESSING THE EXIT BUTTON OR IF WE HAVE TIME - ASKS IF YOU REALLY WANT TO EXIT OR IF YOU WANT TO SAVE
// MIGHT SAVE WHEN PRESSING EXIT. NO IDEA YET

public class Player_Board extends Application  {


    private static ArrayList<Holes> buttons = new ArrayList<>();
    private static GridPane board = new GridPane();

    public Player_Board() throws IOException{
        initializeButtons();
    }

    public void launch(){
        Application.launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        setColumnWidth();

        setRowSize();

        primaryStage.setTitle("Toguz Korgool");

        board.setAlignment(Pos.CENTER);

        board.setPrefSize(1600, 700);

        board.setGridLinesVisible(true);

        board.setVgap(10);
        board.setHgap(10);

        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 9; c++){
                if(r == 0 || r == 4){
                        Label label = new Label(c+1 +"" );
                        label.setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                        label.setAlignment(Pos.CENTER);
                        board.add(label, c, r, 1, 1);
                }
                else if(r ==1) {
                    buttons.get(c + 1).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    board.add(buttons.get(c + 1), c, r, 1, 1);
                }
                else if(r==3){
                    buttons.get(c+11).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    board.add(buttons.get(c + 11), c, r, 1, 1);
                }
                else{
                    buttons.get(0).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    buttons.get(10).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    board.add(buttons.get(0), c, r, 4, 1);
                    board.add(buttons.get(10), 5, r, 4, 1);
                    break;
                }
            }
        }


        board.setStyle("-fx-background-color: transparent;");


        Scene scene = new Scene(board, 1350, 700);
        ImagePattern pattern = new ImagePattern(new Image("file:./src/main/java/toguzKorgool/board.jpg"));
        scene.setFill(pattern);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void setColumnWidth(){
        for(int i = 0; i < 9; i++) {
                board.getColumnConstraints().add(new ColumnConstraints(111));
        }
    }

    private static void setRowSize(){
        for(int i = 0; i < 5; i++) {
            if(i == 0 || i == 4){
                board.getRowConstraints().add(new RowConstraints(50));
            }
            else board.getRowConstraints().add(new RowConstraints(150));
        }
    }


    private static void initializeButtons() throws IOException {
        File_editor.makeDataArray("default.txt");
        for(int i = 0; i < File_editor.getDataList().size(); i++){
            if(i < 10){
                buttons.add(new Holes("player", File_editor.getDataList().get(i)));
            }
            else buttons.add(new Holes("PC", File_editor.getDataList().get(i)));
        }
    }
    public static void main(String[] args) throws IOException{
        Player_Board playerBoard = new Player_Board();
        Application.launch();
    }
}
