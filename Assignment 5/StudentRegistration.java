package com.week8.ah.assignment5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentRegistration extends Application {

    @Override
    public void start(Stage stage) {

        // Main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        // TOP: Application title
        Label title = new Label("Student Registration System");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        HBox header = new HBox(title);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(15));

        root.setTop(header);

        // LEFT: Navigation menu
        Label menuTitle = new Label("Navigation");

        Button dashboardButton = new Button("Dashboard");
        Button registrationButton = new Button("Registration");
        Button coursesButton = new Button("Courses");
        Button helpButton = new Button("Help");

        VBox navigation = new VBox(15);
        navigation.getChildren().addAll(
                menuTitle,
                dashboardButton,
                registrationButton,
                coursesButton,
                helpButton
        );

        navigation.setPadding(new Insets(20));
        navigation.setPrefWidth(170);

        root.setLeft(navigation);

        // CENTER: Registration form
        Label formTitle = new Label("Register a Student");
        formTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // GridPane for form fields
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(20));

        // Text fields
        TextField studentIdField = new TextField();
        TextField fullNameField = new TextField();
        TextField emailField = new TextField();
        TextField programField = new TextField();

        // Field labels
        formGrid.add(new Label("Student ID:"), 0, 0);
        formGrid.add(studentIdField, 1, 0);

        formGrid.add(new Label("Full Name:"), 0, 1);
        formGrid.add(fullNameField, 1, 1);

        formGrid.add(new Label("Email:"), 0, 2);
        formGrid.add(emailField, 1, 2);

        formGrid.add(new Label("Program:"), 0, 3);
        formGrid.add(programField, 1, 3);

        // Action buttons
        Button registerButton = new Button("Register");
        Button clearButton = new Button("Clear");

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(registerButton, clearButton);

        // Status message
        Label statusLabel = new Label();

        // Center layout
        VBox centerLayout = new VBox(20);
        centerLayout.setPadding(new Insets(25));
        centerLayout.getChildren().addAll(
                formTitle,
                formGrid,
                buttonBox,
                statusLabel
        );

        root.setCenter(centerLayout);

        // REGISTER BUTTON EVENT
        registerButton.setOnAction(event -> {

            if (studentIdField.getText().trim().isEmpty()
                    || fullNameField.getText().trim().isEmpty()
                    || emailField.getText().trim().isEmpty()
                    || programField.getText().trim().isEmpty()) {

                statusLabel.setText("Error: Please complete all fields.");
                statusLabel.setStyle("-fx-text-fill: red;");

            } else {

                statusLabel.setText(
                        "Registration successful! Welcome, "
                                + fullNameField.getText() + "."
                );

                statusLabel.setStyle("-fx-text-fill: green;");
            }
        });

        // CLEAR BUTTON EVENT
        clearButton.setOnAction(event -> {

            studentIdField.clear();
            fullNameField.clear();
            emailField.clear();
            programField.clear();

            statusLabel.setText("");
        });

        // Create scene
        Scene scene = new Scene(root, 850, 550);

        stage.setTitle("Student Registration System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}