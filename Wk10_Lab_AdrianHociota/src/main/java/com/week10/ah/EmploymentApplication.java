
package com.week10.ah;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EmploymentApplication extends Application {

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/wk10_employment_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    private TextField fullNameField;
    private TextField addressField;
    private TextField contactField;
    private TextField emailField;
    private TextField educationField;
    private DatePicker dateAvailablePicker;
    private TextField positionField;
    private TextField salaryField;
    private TextArea relativesExplanationArea;
    private TextField signatureField;

    private ToggleGroup genderGroup;
    private ToggleGroup authorizedGroup;
    private ToggleGroup relativesGroup;

    @Override
    public void start(Stage stage) {

        Label companyName = new Label("AJUHA'S CLASSROOM");
        companyName.setStyle(
                "-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        Label formTitle = new Label("EMPLOYMENT APPLICATION");
        formTitle.setStyle(
                "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

        StackPane titleBar = new StackPane(formTitle);
        titleBar.setPadding(new Insets(12));
        titleBar.setStyle("-fx-background-color: #222222;");

        VBox header = new VBox(10, companyName, titleBar);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10, 0, 15, 0));

        // Personal Information
        Label personalTitle = sectionTitle("PERSONAL INFORMATION");

        fullNameField = new TextField();
        addressField = new TextField();
        contactField = new TextField();
        emailField = new TextField();
        educationField = new TextField();

        genderGroup = new ToggleGroup();

        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        RadioButton other = new RadioButton("Other");

        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
        other.setToggleGroup(genderGroup);

        HBox genderOptions = new HBox(20, male, female, other);
        genderOptions.setAlignment(Pos.CENTER_LEFT);
        genderOptions.setMinWidth(250);

        GridPane personalGrid = new GridPane();
        personalGrid.setHgap(15);
        personalGrid.setVgap(12);

        addRow(personalGrid, 0, "Full Name:", fullNameField);
        addRow(personalGrid, 1, "Current Address:", addressField);
        addRow(personalGrid, 2, "Contact Number:", contactField);
        addRow(personalGrid, 3, "Email Address:", emailField);
        addRow(personalGrid, 4, "Highest Education:", educationField);
        addRow(personalGrid, 5, "Gender:", genderOptions);

        // Employment Information
        Label employmentTitle = sectionTitle("EMPLOYMENT INFORMATION");

        dateAvailablePicker = new DatePicker();
        positionField = new TextField();
        salaryField = new TextField();

        GridPane employmentGrid = new GridPane();
        employmentGrid.setHgap(15);
        employmentGrid.setVgap(12);

        addRow(employmentGrid, 0, "Date Available:", dateAvailablePicker);
        addRow(employmentGrid, 1, "Desired Position:", positionField);
        addRow(employmentGrid, 2, "Desired Salary:", salaryField);

        // Employment Eligibility
        Label eligibilityTitle = sectionTitle("EMPLOYMENT ELIGIBILITY");

        authorizedGroup = new ToggleGroup();
        RadioButton authorizedYes = new RadioButton("Yes");
        RadioButton authorizedNo = new RadioButton("No");

        authorizedYes.setToggleGroup(authorizedGroup);
        authorizedNo.setToggleGroup(authorizedGroup);

        HBox authorizedOptions = new HBox(20, authorizedYes, authorizedNo);
        authorizedOptions.setAlignment(Pos.CENTER_LEFT);

        relativesGroup = new ToggleGroup();
        RadioButton relativesYes = new RadioButton("Yes");
        RadioButton relativesNo = new RadioButton("No");

        relativesYes.setToggleGroup(relativesGroup);
        relativesNo.setToggleGroup(relativesGroup);

        HBox relativesOptions = new HBox(20, relativesYes, relativesNo);
        relativesOptions.setAlignment(Pos.CENTER_LEFT);

        relativesExplanationArea = new TextArea();
        relativesExplanationArea.setPromptText(
                "If yes, please provide details...");
        relativesExplanationArea.setPrefRowCount(2);
        relativesExplanationArea.setWrapText(true);

        GridPane eligibilityGrid = new GridPane();
        eligibilityGrid.setHgap(15);
        eligibilityGrid.setVgap(12);

        addRow(eligibilityGrid, 0,
                "Are you authorized to work?", authorizedOptions);
        addRow(eligibilityGrid, 1,
                "Do you have relatives working here?", relativesOptions);
        addRow(eligibilityGrid, 2,
                "If yes, explain:", relativesExplanationArea);

        // Declaration and Signature
        Label declarationTitle = sectionTitle("DECLARATION");

        Label declaration = new Label(
                "I certify that the information provided in this application "
                + "is true and complete to the best of my knowledge.");
        declaration.setWrapText(true);

        signatureField = new TextField();
        signatureField.setPromptText("Type your full name");

        GridPane signatureGrid = new GridPane();
        signatureGrid.setHgap(15);
        signatureGrid.setVgap(12);

        addRow(signatureGrid, 0, "Applicant Signature:", signatureField);

        // Submit Button
        Button submitButton = new Button("SUBMIT APPLICATION");
        submitButton.setStyle(
                "-fx-background-color: #222222;"
                + "-fx-text-fill: white;"
                + "-fx-font-size: 14px;"
                + "-fx-font-weight: bold;"
                + "-fx-padding: 12 25 12 25;"
                + "-fx-cursor: hand;");

        submitButton.setOnAction(event -> submitApplication(stage));

        HBox buttonBox = new HBox(submitButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(15, 0, 10, 0));

        VBox content = new VBox(
                12,
                header,
                personalTitle,
                personalGrid,
                employmentTitle,
                employmentGrid,
                eligibilityTitle,
                eligibilityGrid,
                declarationTitle,
                declaration,
                signatureGrid,
                buttonBox
        );

        content.setPadding(new Insets(20));
        content.setMaxWidth(750);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: white;");

        Scene scene = new Scene(scrollPane, 850, 750);

        stage.setTitle("Ajuha's Classroom - Employment Application");
        stage.setScene(scene);
        stage.show();
    }

    private Label sectionTitle(String title) {
        Label label = new Label(title);
        label.setStyle(
                "-fx-font-size: 15px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #222222;"
                + "-fx-border-color: transparent transparent #222222 transparent;"
                + "-fx-border-width: 0 0 2 0;"
                + "-fx-padding: 0 0 6 0;");
        return label;
    }

    private void addRow(GridPane grid, int row,
                        String labelText, Region control) {

        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold;");
        label.setMinWidth(190);

        control.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(control, Priority.ALWAYS);

        grid.add(label, 0, row);
        grid.add(control, 1, row);
    }

    private void submitApplication(Stage stage) {

        String sql = """
                INSERT INTO employment_applications
                (full_name, current_address, contact_number, email_address,
                 highest_education, gender, date_available, desired_position,
                 desired_salary, authorized_to_work, relatives_working,
                 relatives_explanation, applicant_signature)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, fullNameField.getText());
            statement.setString(2, addressField.getText());
            statement.setString(3, contactField.getText());
            statement.setString(4, emailField.getText());
            statement.setString(5, educationField.getText());

            RadioButton selectedGender =
                    (RadioButton) genderGroup.getSelectedToggle();

            statement.setString(
                    6, selectedGender == null ? "" : selectedGender.getText());

            if (dateAvailablePicker.getValue() != null) {
                statement.setDate(
                        7, java.sql.Date.valueOf(dateAvailablePicker.getValue()));
            } else {
                statement.setNull(7, Types.DATE);
            }

            statement.setString(8, positionField.getText());

            if (salaryField.getText().trim().isEmpty()) {
                statement.setNull(9, Types.DECIMAL);
            } else {
                statement.setBigDecimal(
                        9, new BigDecimal(salaryField.getText().trim()));
            }

            RadioButton authorized =
                    (RadioButton) authorizedGroup.getSelectedToggle();

            statement.setString(
                    10, authorized == null ? "" : authorized.getText());

            RadioButton relatives =
                    (RadioButton) relativesGroup.getSelectedToggle();

            statement.setString(
                    11, relatives == null ? "" : relatives.getText());

            statement.setString(12, relativesExplanationArea.getText());
            statement.setString(13, signatureField.getText());

            statement.executeUpdate();

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Application Submitted");
            success.setHeaderText("Success!");
            success.setContentText(
                    "Your application was successfully saved to the database.");
            success.showAndWait();

        } catch (NumberFormatException e) {

            showError("Please enter a valid number for Desired Salary.");

        } catch (SQLException e) {

            showError("Database error: " + e.getMessage());

        } catch (NullPointerException e) {

            showError("Please check your database password environment variable (DB_PASSWORD).");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Application Could Not Be Submitted");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
