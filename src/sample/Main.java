package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.security.KeyStore;

public class Main extends Application {

    private boolean True;
    private boolean False;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane root = new GridPane();
        Group game = new Group();
        primaryStage.setTitle("Tic-tac-toe");
        root.setHgap(10);
        root.setVgap(12);
        root.setAlignment(Pos.CENTER);
        Button button = new Button("1 mängija");
        Button button2 = new Button("2 mängijat");
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
            int RuudustikuLaius= 600;
            int RuudustikuKõrgus= 600;
            final Canvas canvas = new Canvas(RuudustikuLaius,RuudustikuKõrgus);
            primaryStage.setScene(new Scene(game, canvas.getWidth(), canvas.getHeight()));
            GraphicsContext gc = canvas.getGraphicsContext2D();
            game.getChildren().add(canvas);
            canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
            for (int i = 1;i<3;i++){

                gc.strokeLine(canvas.getWidth()/3*i,0,canvas.getWidth()/3*i,canvas.getHeight());
                gc.strokeLine(0,canvas.getHeight()/3*i,canvas.getWidth(),canvas.getHeight()/3*i);
            }
            gc.stroke();
        });

        primaryStage.show();
    }
    //Creating the mouse event handler
    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            System.out.println(e.getX());
            System.out.println(e.getY());
        }
    };
//Adding event Filter

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
