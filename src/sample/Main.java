package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane root = new GridPane();
        Group game = new Group();
        primaryStage.setTitle("Tic-tac-toe");
        root.setHgap(10);
        root.setVgap(12);
        root.setAlignment(Pos.CENTER);
        Button button = new Button("Mängima");
        Button button2 = new Button(".");
        //button.relocate(50,100);
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        //<>
        hbButtons.getChildren().addAll(button,button2);
        root.add(hbButtons, 0, 0);
        hbButtons.setAlignment(Pos.CENTER);
        //root.add(button2, 0, 1);
        primaryStage.setScene(new Scene(root, 200, 400));





        button.setOnAction(value ->  {
            final Canvas canvas = new Canvas(600,600);
            primaryStage.setScene(new Scene(game, canvas.getWidth(), canvas.getHeight()));
            GraphicsContext gc = canvas.getGraphicsContext2D();
            game.getChildren().add(canvas);


            for (int i = 0;i<3;i++){
                for (int j = 0;j<3;j++){
                    Rectangle ruut = ruut();
                    ruut.setTranslateX(j*200);
                    ruut.setTranslateY(i*200);


                    game.getChildren().add(ruut);
                }
            }

        });
        //root.getChildren().add(button);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
    public Rectangle ruut(){
        Rectangle border= new Rectangle(200,200);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        //setAlignment(Pos.CENTER);
        //game.getChildren().addAll(border);
        return border;
    }
}
