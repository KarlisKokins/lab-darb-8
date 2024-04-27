package com.example.main_java;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Random;
//Kārlis Kokins grupa.17 231RDC004. JavaFX
public class Main extends Application
{
    private int numberToGuess;
    private int attemptsLeft = 3;
    @Override
    public void start(Stage primaryStage) {

        Label label1, label2;
        TextField textField;
        Button btnOk, btnNewGame;
        VBox vbox;
        Scene scene;

        label1 = new Label("Input number (1-10):");
        textField = new TextField();
        label2 = new Label("You have 3 attempts to guess a number");
        textField.setMaxWidth(250);


        btnOk = new Button("OK");
        btnNewGame = new Button("New Game");

        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String input = textField.getText();
                try {
                    int guessedNumber = Integer.parseInt(input);
                    if (guessedNumber < 1 || guessedNumber > 10) {
                        label2.setText("Error: Please enter a number between 1 and 10");
                        return;
                    }
                    if (guessedNumber == numberToGuess) {
                        label2.setText("You win!");
                        attemptsLeft = 0; // End the game
                    } else {
                        attemptsLeft--;
                        if (attemptsLeft == 0) {
                            label2.setText("You lose! The number was " + numberToGuess);
                        } else if (guessedNumber < numberToGuess) {
                            label2.setText("My number is greater than yours. You have " + attemptsLeft + " attempts left");
                        } else {
                            label2.setText("My number is less than yours. You have " + attemptsLeft + " attempts left");
                        }
                    }
                } catch (NumberFormatException ex) {
                    label2.setText("Error: Please enter a valid number");
                }
            }
        });

        btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                numberToGuess = new Random().nextInt(10) + 1;
                attemptsLeft = 3;
                label2.setText("You have " + attemptsLeft + " attempts to guess a number");
                textField.clear();
            }
        });

        vbox = new VBox(label1, textField, label2, btnOk, btnNewGame);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 300, 300);

        primaryStage.setTitle("Guess a number");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 