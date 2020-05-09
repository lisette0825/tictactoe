package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {


    private int RuudustikuLaius = 600;
    private int RuudustikuKõrgus = 600;
    private int RuuduLaius = RuudustikuLaius / 3;
    private int RuuduKõrgus = RuudustikuKõrgus / 3;
    int[][] ruudustik = new int[3][3];
    private boolean esimesekord = true;
    GraphicsContext gc;
    private int MituVõituO = 0;
    private int MituVõituX = 0;
    private int Viike=0;
    private int Mänge_Võiduni = 1;
    int d = 0;
    int a = 0;
    int f = 0;
    int g = 0;
    int c = 0;
    Stage primaryStage = new Stage();
    boolean voor_läbi = false;

    public void tühi_ruudustik() {
        ruudustik[0][0] = 0;
        ruudustik[0][1] = 0;
        ruudustik[0][2] = 0;
        ruudustik[1][0] = 0;
        ruudustik[1][1] = 0;
        ruudustik[1][2] = 0;
        ruudustik[2][0] = 0;
        ruudustik[2][1] = 0;
        ruudustik[2][2] = 0;
    }

    public void Võidetud_Mängud() {
        gc.setFont(Font.font(30));
        gc.fillText("X võite:  " + MituVõituX, 650, 200);
        gc.fillText("O võite:  " + MituVõituO, 650, 300);
        gc.fillText("Viike:  "+ Viike,650, 400);
    }

    public void loo_mäng() {
        a = 0;
        tühi_ruudustik();
        f = 0;
        g = 0;
        d = 0;
        Group game = new Group();
        final Canvas canvas = new Canvas(800, 600);
        primaryStage.setScene(new Scene(game, canvas.getWidth(), canvas.getHeight()));
        gc = canvas.getGraphicsContext2D();
        game.getChildren().add(canvas);
        canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        Võidetud_Mängud();
        for (int i = 1; i < 3; i++) {
            gc.strokeLine(RuuduLaius * i, 0, RuuduLaius * i, RuudustikuKõrgus);
            gc.strokeLine(0, RuuduKõrgus * i, RuudustikuKõrgus, RuuduKõrgus * i);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane root = new GridPane();
        primaryStage.setTitle("Tic-tac-toe");
        root.setHgap(10);
        root.setVgap(12);
        root.setAlignment(Pos.CENTER);
        Button button = new Button("Mängima");
        TextField Mänge_Võiduni_Valik = new TextField();
        Mänge_Võiduni_Valik.setPromptText("Vali mitu mängu võiduni tehakse:");
        Mänge_Võiduni_Valik.setPrefSize(190, 30);
        Mänge_Võiduni_Valik.setFocusTraversable(false);

        root.add(Mänge_Võiduni_Valik, 0, 0);
        root.add(button, 1, 0);
        primaryStage.setScene(new Scene(root, 400, 200));

        button.setOnAction(value -> {
            Mänge_Võiduni = Integer.parseInt(String.valueOf(Mänge_Võiduni_Valik.getText()));
            loo_mäng();
        });
        primaryStage.show();
    }

    public void drawRist(int x, int y, int ruudupikkus) {
        double vahe = 0.2;
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine((x + vahe) * ruudupikkus, (y + vahe) * ruudupikkus, (x + 1 - vahe) * ruudupikkus, (y + 1 - vahe) * ruudupikkus);
        gc.strokeLine((x + 1 - vahe) * ruudupikkus, (y + vahe) * ruudupikkus, (x + vahe) * ruudupikkus, (y + 1 - vahe) * ruudupikkus);
    }

    public void drawRing(int x, int y, int ruudupikkus) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        double vahe = 0.4;
        gc.strokeOval((x + vahe / 2) * ruudupikkus, (y + vahe / 2) * ruudupikkus, ruudupikkus * (1 - vahe), ruudupikkus * (1 - vahe));
    }

    public void Mäng_läbi(int tulemus) {
        MituVõituO = 0;
        MituVõituX = 0;
        c = 1;
        Stage stage = new Stage();
        stage.setX(750);
        stage.setY(300);
        stage.setWidth(400);
        stage.setHeight(250);
        if (tulemus == 0) {
            stage.setTitle("VÕITIS RIST!");
        }
        if (tulemus == 1) {
            stage.setTitle("VÕITIS RING!");
        }
        if (tulemus == 2) {
            stage.setTitle("VIIK");
        }
        Button button3 = new Button("Mängi uuesti");
        Button button4 = new Button("Lõpeta");
        HBox hbox = new HBox(button3, button4);
        hbox.setSpacing(20.0);
        hbox.setAlignment(Pos.CENTER);
        button3.setOnAction(event -> {
            stage.close();
            a = 0;
            tühi_ruudustik();
            f = 0;
            g = 0;
            d = 0;
            loo_mäng();
        });
        button4.setOnAction(event -> System.exit(0));
        Scene scene2 = new Scene(hbox, RuudustikuLaius, RuudustikuKõrgus);
        stage.setScene(scene2);
        stage.showAndWait();
    }

    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            if (!voor_läbi) {
                int x = (int) e.getX();
                int y = (int) e.getY();
                int ruutX = RuudustikuLaius / 3;
                int ruutY = RuudustikuKõrgus / 3;
                //<>
                String[] lõpeta = new String[8];
                for (int p = 0; p < 1; p++) {
                    for (int i = f; i < 3; i++) {
                        for (int j = g; j < 3; j++) {
                            if (x > ruutX * i && x < ruutX * (i + 1) && y > ruutY * j && y < ruutY * (j + 1) && d == 0) {
                                if (ruudustik[i][j] == 0) {
                                    if (esimesekord) {
                                        ruudustik[i][j] = 1;
                                        a = a + 1;
                                        drawRist(i, j, RuuduLaius);
                                        esimesekord = false;
                                    } else {
                                        drawRing(i, j, RuuduLaius);
                                        ruudustik[i][j] = 2;
                                        a = a + 1;
                                        esimesekord = true;
                                    }
                                }
                                gc.setStroke(Color.RED);
                                gc.setLineWidth(10);
                                if (ruudustik[0][0] == 1 && ruudustik[0][1] == 1 && ruudustik[0][2] == 1) {
                                    d = d + 1;
                                    gc.strokeLine(100, 0, 100, 600);
                                }
                                if (ruudustik[1][0] == 1 && ruudustik[1][1] == 1 && ruudustik[1][2] == 1) {
                                    d = d + 1;
                                    gc.strokeLine(300, 0, 300, 600);
                                }
                                if (ruudustik[2][0] == 1 && ruudustik[2][1] == 1 && ruudustik[2][2] == 1) {
                                    d = d + 1;
                                    gc.strokeLine(500, 0, 500, 600);
                                }
                                if (ruudustik[0][0] == 1 && ruudustik[1][0] == 1 && ruudustik[2][0] == 1) {
                                    d = d + 1;
                                    gc.strokeLine(0, 100, 600, 100);
                                }
                                if (ruudustik[0][1] == 1 && ruudustik[1][1] == 1 && ruudustik[2][1] == 1) {
                                    d = d + 1;
                                    gc.strokeLine(0, 300, 600, 300);
                                }
                                if (ruudustik[0][2] == 1 && ruudustik[1][2] == 1 && ruudustik[2][2] == 1) {
                                    d = d + 1;
                                    gc.strokeLine(0, 500, 600, 500);
                                }
                                if (ruudustik[0][0] == 1 && ruudustik[1][1] == 1 && ruudustik[2][2] == 1) {
                                    d = d + 1;
                                    gc.strokeLine(0, 0, 600, 600);
                                }
                                if (ruudustik[2][0] == 1 && ruudustik[1][1] == 1 && ruudustik[0][2] == 1) {
                                    d = d + 1;
                                    gc.strokeLine(600, 0, 0, 600);
                                }
                                if (ruudustik[0][0] == 2 && ruudustik[0][1] == 2 && ruudustik[0][2] == 2) {
                                    d = d + 4;
                                    gc.strokeLine(100, 0, 100, 600);
                                }
                                if (ruudustik[1][0] == 2 && ruudustik[1][1] == 2 && ruudustik[1][2] == 2) {
                                    d = d + 4;
                                    gc.strokeLine(300, 0, 300, 600);
                                }
                                if (ruudustik[2][0] == 2 && ruudustik[2][1] == 2 && ruudustik[2][2] == 2) {
                                    d = d + 4;
                                    gc.strokeLine(500, 0, 500, 600);
                                }
                                if (ruudustik[0][0] == 2 && ruudustik[1][0] == 2 && ruudustik[2][0] == 2) {
                                    d = d + 4;
                                    gc.strokeLine(0, 100, 600, 100);
                                }
                                if (ruudustik[0][1] == 2 && ruudustik[1][1] == 2 && ruudustik[2][1] == 2) {
                                    d = d + 4;
                                    gc.strokeLine(0, 300, 600, 300);
                                }
                                if (ruudustik[0][2] == 2 && ruudustik[1][2] == 2 && ruudustik[2][2] == 2) {
                                    d = d + 4;
                                    gc.strokeLine(0, 500, 600, 500);
                                }
                                if (ruudustik[0][0] == 2 && ruudustik[1][1] == 2 && ruudustik[2][2] == 2) {
                                    d = d + 4;
                                    gc.strokeLine(0, 0, 600, 600);
                                }
                                if (ruudustik[2][0] == 2 && ruudustik[1][1] == 2 && ruudustik[0][2] == 2) {
                                    d = d + 4;
                                    gc.strokeLine(600, 0, 0, 600);
                                }
                            }
                        }
                    }
                }
                if (d >= 1 && d < 4 && a != 0) {
                    MituVõituX++;
                    esimesekord = false;
                    if (MituVõituX == Mänge_Võiduni) {
                        Mäng_läbi(0);
                    } else {
                        voor_läbi = true;
                    }
                }
                if (d >= 4 && a != 0) {
                    MituVõituO++;
                    if (MituVõituO == Mänge_Võiduni) {
                        Mäng_läbi(1);
                    } else {
                        voor_läbi = true;
                    }
                }
                if (d == 0 && a == 9) {
                    Viike++;
                    voor_läbi = true;
                }
            } else {
                voor_läbi = false;
                loo_mäng();
            }
        }
    };
}