
package com.week10.ah;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmploymentApplication extends Application {

    // MySQL database connection
    private static final String URL =
            "jdbc:mysql://localhost:3306/employment_db";

    private static final String USER = "root";

    // CHANGE THIS to your actual MySQL password
    private static final String PASSWORD =
            System.getenv("DB_PASSWORD");

    @Override
    public void start(Stage stage) {

        // Form title
        Label title = new Label("EMPLOYMENT APPLICATION");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Form fields
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField phone = new TextField();
        TextField position = new TextField();
        TextField address = new TextField();

        TextArea experience = new TextArea();
        experience.setPromptText("Describe your previous work experience...");
        experience.setPrefRowCount(4);

        // Grid layout
        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(12);

        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstName, 1, 0);

        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastName, 1, 1);

        grid.add(new Label("Email:"), 0, 2);
        grid.add(email, 1, 2);

        grid.add(new Label("Phone:"), 0, 3);
        grid.add(phone, 1, 3);

        grid.add(new Label("Position:"), 0, 4);
        grid.add(position, 1, 4);

        grid.add(new Label("Address:"), 0, 5);
        grid.add(address, 1, 5);

        grid.add(new Label("Experience:"), 0, 6);
        grid.add(experience, 1, 6);

        // Submit button
        Button submitButton = new Button("Submit Application");

        submitButton.setStyle(
                "-fx-background-color: black;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 10 25 10 25;"
        );

        Label message = new Label();

        // Submit button event
        submitButton.setOnAction(event -> {

            // Validate required fields
            if (firstName.getText().trim().isEmpty()
                    || lastName.getText().trim().isEmpty()
                    || email.getText().trim().isEmpty()
                    || position.getText().trim().isEmpty()) {

                message.setText("Please complete all required fields.");
                message.setStyle("-fx-text-fill: red;");
                return;
            }

            // SQL INSERT using PreparedStatement
            String sql = "INSERT INTO applications "
                    + "(first_name, last_name, email, phone, "
                    + "position, address, experience) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

                 PreparedStatement statement =
                    connection.prepareStatement(sql)) {

                // Set values for PreparedStatement
                statement.setString(1, firstName.getText().trim());
                statement.setString(2, lastName.getText().trim());
                statement.setString(3, email.getText().trim());
                statement.setString(4, phone.getText().trim());
                statement.setString(5, position.getText().trim());
                statement.setString(6, address.getText().trim());
                statement.setString(7, experience.getText().trim());

                // Execute database INSERT
                int result = statement.executeUpdate();

                if (result > 0) {

                    message.setText("Application submitted successfully!");
                    message.setStyle("-fx-text-fill: green;");

                    // Clear form fields
                    firstName.clear();
                    lastName.clear();
                    email.clear();
                    phone.clear();
                    position.clear();
                    address.clear();
                    experience.clear();

                }

            } catch (SQLException e) {

                message.setText("Database error: " + e.getMessage());
                message.setStyle("-fx-text-fill: red;");

                e.printStackTrace();
            }
        });

        // Main layout
        VBox root = new VBox(18);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, grid, submitButton, message);

        root.setStyle("-fx-background-color: #f2f2f2;");

        // Window setup
        Scene scene = new Scene(root, 650, 550);

        stage.setTitle("Employment Application Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
