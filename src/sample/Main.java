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
    private int RuudustikuLaius= 600;
    private int RuudustikuKõrgus= 600;
    private int[][] ruudustik= new int [3][3];
    boolean esimesekord=true;
    Canvas canvas;
    GraphicsContext gc;
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
        Button button = new Button("Mängima");
        Button button2 = new Button("Seaded");
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

            final Canvas canvas = new Canvas(RuudustikuLaius,RuudustikuKõrgus);
            primaryStage.setScene(new Scene(game, canvas.getWidth(), canvas.getHeight()));
            gc = canvas.getGraphicsContext2D();
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

    public void drawRist(int x, int y, int ruudupikkus) {
        double vahe=0.2;
        gc.strokeLine((x+vahe)*ruudupikkus,(y+vahe)*ruudupikkus,(x+1-vahe)*ruudupikkus,(y+1-vahe)*ruudupikkus);
        gc.strokeLine((x+1-vahe)*ruudupikkus,(y+vahe)*ruudupikkus,(x+vahe)*ruudupikkus,(y+1-vahe)*ruudupikkus);

    }

    public void drawRing(int x, int y, int ruudupikkus) {
        double vahe = 0.4;
        gc.strokeOval((x+vahe/2)*ruudupikkus,(y+vahe/2)*ruudupikkus,ruudupikkus*(1-vahe),ruudupikkus*(1-vahe));
    }

    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            int x= (int) e.getX();
            int y= (int) e.getY();
            int ruutX= RuudustikuLaius/3;
            int ruutY= RuudustikuKõrgus/3;

            //<>
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (x>ruutX*i && x<ruutX*(i+1) && y>ruutY*j && y<ruutY*(j+1)){
                        System.out.println(i);
                        System.out.println(j);
                        if(esimesekord){
                            drawRist(i,j,200);
                            esimesekord=false;
                        }
                        else{
                            drawRing(i,j,200);
                            esimesekord=true;
                        }

                    }

                }
            }



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
