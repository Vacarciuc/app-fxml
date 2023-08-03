package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 * Trebuie creat un formular pentru înregistrarea vânzării pachetului de internet. Pachetul de internet conţine câţiva parametri, şi anume:
 *
 * viteza;
 * traficul de date;
 * durata contractului;
 * numărul de identificare unic;
 * prenumele şi numele utilizatorului;
 * adresa utilizatorului.
 *
 * Viteza de internet poate fi de 2, 5, 10, 20, 50 şi 100 Mbit/s.
 *
 * Traficul de date poate fi de 1, 5, 10, 100 GB sau Flat.
 *
 * Durata contractului poate fi de unul sau doi ani.
 *
 * Prenumele, numele şi adresa utilizatorului se introduc.
 *
 * Trebuie asigurată o evidenţă a creării contractelor şi pachetelor vândute, care trebuie să conţină următoarele:
 *
 * revizuirea tuturor pachetelor vândute;
 * adăugarea noilor vânzări realizate;
 * ştergerea vânzărilor existente.
 *
 * Aplicația poate fi realizată într-o singură fereastră sau în ferestre separate.
 *
 * Se recomandă utilizarea şablonului MVC şi FXML, însă acestea nu sunt obligatorii. Pentru modelul de domeniu, trebuie creată clasa separată, care va reprezenta pachetul de internet.
 *
 * Este de preferat să stilizați aplicaţia, utilizând CSS.
 */
public class App extends Application {

    private double dragOffsetX, dragOffsetY;

    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getClassLoader().getResource("overview.fxml");
        GridPane gridPane = FXMLLoader.load(url);
        Scene scene = new Scene(gridPane, 1000, 600);
        stage.initStyle(StageStyle.UNDECORATED);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.show();


        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragOffsetX=event.getScreenX()-stage.getX();
                dragOffsetY=event.getScreenY()-stage.getY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX()-dragOffsetX);
                stage.setY(event.getScreenY()-dragOffsetY);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}