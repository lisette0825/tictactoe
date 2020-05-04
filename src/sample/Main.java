package sample;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.security.KeyStore;
import java.util.Arrays;






public class Main extends Application {



    private int RuudustikuLaius = 600;
    private int RuudustikuKõrgus = 600;
    int[][] ruudustik = new int[3][3];
    private boolean esimesekord = true;
    Canvas canvas;
    GraphicsContext gc;
    private boolean True;
    private boolean False;
    int d = 0;
    int a = 0;
    int f=0;
    int g=0;
    int c=0;
    Stage primaryStage = new Stage();


    @Override
    public void start (Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane root = new GridPane();
        Group game = new Group();
        primaryStage.setTitle("Tic-tac-toe");
        root.setHgap(10);
        root.setVgap(12);
        root.setAlignment(Pos.CENTER);
        Button button = new Button("Mängima");
        //Button button2 = new Button("Seaded");

        //button.relocate(50,100);
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        //<>
        hbButtons.getChildren().addAll(button);
        root.add(hbButtons, 0, 0);
        hbButtons.setAlignment(Pos.CENTER);
        //root.add(button2, 0, 1);
        primaryStage.setScene(new Scene(root, 200, 400));


        button.setOnAction(value -> {

            final Canvas canvas = new Canvas(RuudustikuLaius, RuudustikuKõrgus);
            primaryStage.setScene(new Scene(game, canvas.getWidth(), canvas.getHeight()));
            gc = canvas.getGraphicsContext2D();
            game.getChildren().add(canvas);
            canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
            for (int i = 1; i < 3; i++) {

                gc.strokeLine(canvas.getWidth() / 3 * i, 0, canvas.getWidth() / 3 * i, canvas.getHeight());
                gc.strokeLine(0, canvas.getHeight() / 3 * i, canvas.getWidth(), canvas.getHeight() / 3 * i);
            }
            gc.stroke();

        });

        primaryStage.show();
    }

    public void drawRist ( int x, int y, int ruudupikkus){
        double vahe = 0.2;
        gc.strokeLine((x + vahe) * ruudupikkus, (y + vahe) * ruudupikkus, (x + 1 - vahe) * ruudupikkus, (y + 1 - vahe) * ruudupikkus);
        gc.strokeLine((x + 1 - vahe) * ruudupikkus, (y + vahe) * ruudupikkus, (x + vahe) * ruudupikkus, (y + 1 - vahe) * ruudupikkus);

    }

    public void drawRing ( int x, int y, int ruudupikkus){
        double vahe = 0.4;
        gc.strokeOval((x + vahe / 2) * ruudupikkus, (y + vahe / 2) * ruudupikkus, ruudupikkus * (1 - vahe), ruudupikkus * (1 - vahe));
    }


    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            int x = (int) e.getX();
            int y = (int) e.getY();
            int ruutX = RuudustikuLaius / 3;
            int ruutY = RuudustikuKõrgus / 3;

            //<>
            String[] lõpeta = new String[8];
            for(int p=0;p<1;p++){


                for (int i = f; i < 3; i++) {
                    for (int j = g; j < 3; j++) {
                        if (x > ruutX * i && x < ruutX * (i + 1) && y > ruutY * j && y < ruutY * (j + 1) && d == 0) {

                            if (ruudustik[i][j] == 0) {
                                if (esimesekord ) {
                                    ruudustik[i][j] = 1;
                                    a = a + 1;
                                    drawRist(i, j, 200);
                                    esimesekord = false;
                                } else{
                                    drawRing(i, j, 200);
                                    ruudustik[i][j] = 2;
                                    a = a + 1;
                                    esimesekord = true;
                                }

                            }


                            if (ruudustik[0][0] == 1 && ruudustik[0][1] == 1 && ruudustik[0][2] == 1) {
                                d = d + 1;

                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(100, 0, 100, 600);
                            }
                            if (ruudustik[1][0] == 1 && ruudustik[1][1] == 1 && ruudustik[1][2] == 1) {
                                d = d + 1;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(300, 0, 300, 600);
                            }
                            if (ruudustik[2][0] == 1 && ruudustik[2][1] == 1 && ruudustik[2][2] == 1) {
                                d = d + 1;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(500, 0, 500, 600);
                            }
                            if (ruudustik[0][0] == 1 && ruudustik[1][0] == 1 && ruudustik[2][0] == 1) {
                                d = d + 1;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(0, 100, 600, 100);
                            }
                            if (ruudustik[0][1] == 1 && ruudustik[1][1] == 1 && ruudustik[2][1] == 1) {
                                d = d + 1;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(0, 300, 600, 300);
                            }
                            if (ruudustik[0][2] == 1 && ruudustik[1][2] == 1 && ruudustik[2][2] == 1) {
                                d = d + 1;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(0, 500, 600, 500);
                            }
                            if (ruudustik[0][0] == 1 && ruudustik[1][1] == 1 && ruudustik[2][2] == 1) {
                                d = d + 1;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(0, 0, 600, 600);
                            }
                            if (ruudustik[2][0] == 1 && ruudustik[1][1] == 1 && ruudustik[0][2] == 1) {
                                d = d + 1;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(600, 0, 0, 600);
                            }
                            if (ruudustik[0][0] == 2 && ruudustik[0][1] == 2 && ruudustik[0][2] == 2) {
                                d = d + 4;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(100, 0, 100, 600);
                            }
                            if (ruudustik[1][0] == 2 && ruudustik[1][1] == 2 && ruudustik[1][2] == 2) {
                                d = d + 4;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(300, 0, 300, 600);
                            }
                            if (ruudustik[2][0] == 2 && ruudustik[2][1] == 2 && ruudustik[2][2] == 2) {
                                d = d + 4;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(500, 0, 500, 600);
                            }
                            if (ruudustik[0][0] == 2 && ruudustik[1][0] == 2 && ruudustik[2][0] == 2) {
                                d = d + 4;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(0, 100, 600, 100);
                            }
                            if (ruudustik[0][1] == 2 && ruudustik[1][1] == 2 && ruudustik[2][1] == 2) {
                                d = d + 4;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(0, 300, 600, 300);
                            }
                            if (ruudustik[0][2] == 2 && ruudustik[1][2] == 2 && ruudustik[2][2] == 2) {
                                d = d + 4;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(0, 500, 600, 500);
                            }
                            if (ruudustik[0][0] == 2 && ruudustik[1][1] == 2 && ruudustik[2][2] == 2) {
                                d = d + 4;
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                gc.strokeLine(0, 0, 600, 600);
                            }
                            if (ruudustik[2][0] == 2 && ruudustik[1][1] == 2 && ruudustik[0][2] == 2) {
                                d = d + 4;
                                gc.setLineWidth(10);
                                gc.setStroke(Color.RED);
                                gc.strokeLine(600, 0, 0, 600);
                            }


                        }

                    }

                }
                if (d>=1&& d < 4 && a != 0) {
                    System.out.println("ESIMENE VÕITIS");
                    esimesekord=true;
                    Stage stage = new Stage();
                    stage.setX(750);
                    stage.setY(300);
                    stage.setWidth(400);
                    stage.setHeight(250);
                    stage.setTitle("VÕITIS RIST!");
                    Button button3 = new Button("Mängi uuesti");
                    Button button4 = new Button("Lõpeta");
                    HBox hbox = new HBox(button3, button4);
                    hbox.setSpacing(20.0);
                    hbox.setAlignment(Pos.CENTER);
                    button3.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            stage.close();
                            a = 0;
                            ruudustik[0][0]=0;
                            ruudustik[0][1]=0;
                            ruudustik[0][2]=0;
                            ruudustik[1][0]=0;
                            ruudustik[1][1]=0;
                            ruudustik[1][2]=0;
                            ruudustik[2][0]=0;
                            ruudustik[2][1]=0;
                            ruudustik[2][2]=0;


                            f = 0;
                            g = 0;
                            d=0;




                            try {
                                start(new Stage());
                            } catch (Exception ex) {
                                ex.printStackTrace();


                            }

                        }
                    });
                    button4.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.exit(0);
                        }
                    });

                    Scene scene2 = new Scene(hbox, 600, 600);
                    stage.setScene(scene2);
                    stage.showAndWait();


                }


                if (d >=4 && a != 0) {
                    System.out.println("TEINE VÕITIS");
                    c=1;
                    Stage stage = new Stage();
                    esimesekord=false;
                    stage.setX(750);
                    stage.setY(300);
                    stage.setWidth(400);
                    stage.setHeight(250);
                    stage.setTitle("VÕITIS RING!");
                    Button button3 = new Button("Mängi uuesti");
                    Button button4 = new Button("Lõpeta");
                    HBox hbox = new HBox(button3, button4);
                    hbox.setSpacing(20.0);
                    hbox.setAlignment(Pos.CENTER);
                    button3.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            stage.close();
                            a = 0;
                            ruudustik[0][0]=0;
                            ruudustik[0][1]=0;
                            ruudustik[0][2]=0;
                            ruudustik[1][0]=0;
                            ruudustik[1][1]=0;
                            ruudustik[1][2]=0;
                            ruudustik[2][0]=0;
                            ruudustik[2][1]=0;
                            ruudustik[2][2]=0;


                            f = 0;
                            g = 0;
                            d=0;




                            try {
                                start(new Stage());
                            } catch (Exception ex) {
                                ex.printStackTrace();


                            }

                        }
                    });
                    button4.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.exit(0);
                        }

                    });
                    Scene scene2 = new Scene(hbox, 600, 600);
                    stage.setScene(scene2);
                    stage.showAndWait();


                }
                if (d == 0 && a == 9) {
                    System.out.println("Kumbki ei võitnud");
                    System.out.println("VIIK");
                    Stage stage = new Stage();
                    stage.setX(750);
                    stage.setY(300);
                    stage.setWidth(400);
                    stage.setHeight(250);
                    stage.setTitle("VIIK!");
                    Button button3 = new Button("Mängi uuesti");
                    Button button4 = new Button("Lõpeta");
                    HBox hbox = new HBox(button3, button4);
                    hbox.setSpacing(20.0);
                    hbox.setAlignment(Pos.CENTER);
                    button3.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            stage.close();
                            a = 0;
                            ruudustik[0][0]=0;
                            ruudustik[0][1]=0;
                            ruudustik[0][2]=0;
                            ruudustik[1][0]=0;
                            ruudustik[1][1]=0;
                            ruudustik[1][2]=0;
                            ruudustik[2][0]=0;
                            ruudustik[2][1]=0;
                            ruudustik[2][2]=0;


                            f = 0;
                            g = 0;


                            try {
                                start(new Stage());
                            } catch (Exception ex) {
                                ex.printStackTrace();


                            }

                        }
                    });
                    button4.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.exit(0);
                        }

                    });
                    Scene scene2 = new Scene(hbox, 600, 600);
                    stage.setScene(scene2);
                    stage.showAndWait();


                }

            }

        }


    };


}