
package com.week10.ah;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField nameField;
    private TextField addressField;
    private TextField phoneField;
    private TextField emailField;
    private ComboBox<String> educationBox;
    private ToggleGroup genderGroup;

    private DatePicker dateAvailablePicker;
    private TextField positionField;
    private TextField salaryField;
    private ToggleGroup authorizedGroup;
    private ToggleGroup relativesGroup;
    private TextArea explanationArea;
    private TextField signatureField;

    private final UserDAO userDAO = new UserDAO();

    @Override
    public void start(Stage stage) {

        Label heading = new Label(StringPool.APP_TITLE);
        heading.getStyleClass().add("main-heading");

        Label subtitle = new Label(StringPool.FORM_TITLE);
        subtitle.getStyleClass().add("subtitle");

        VBox header = new VBox(8, heading, subtitle);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(12);
        form.setPadding(new Insets(25));

        int row = 0;

        // Personal Information
        addSection(form, "PERSONAL INFORMATION", row++);
        
        nameField = new TextField();
        addressField = new TextField();
        phoneField = new TextField();
        emailField = new TextField();

        addField(form, StringPool.FULL_NAME, nameField, row++);
        addField(form, StringPool.ADDRESS, addressField, row++);
        addField(form, StringPool.CONTACT_NUMBER, phoneField, row++);
        addField(form, StringPool.EMAIL, emailField, row++);

        educationBox = new ComboBox<>();
        educationBox.getItems().addAll(
                "High School",
                "College Diploma",
                "Bachelor's Degree",
                "Master's Degree",
                "Other"
        );
        educationBox.setPromptText("Select Education");
        educationBox.setMaxWidth(Double.MAX_VALUE);

        addField(form, StringPool.EDUCATION, educationBox, row++);

        // Gender
        genderGroup = new ToggleGroup();

        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        RadioButton other = new RadioButton("Other");

        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
        other.setToggleGroup(genderGroup);

        HBox genderOptions = new HBox(20, male, female, other);
        addField(form, StringPool.GENDER, genderOptions, row++);

        // Employment Information
        addSection(form, "EMPLOYMENT INFORMATION", row++);

        dateAvailablePicker = new DatePicker();
        dateAvailablePicker.setMaxWidth(Double.MAX_VALUE);
        addField(form, StringPool.DATE_AVAILABLE, dateAvailablePicker, row++);

        positionField = new TextField();
        addField(form, StringPool.POSITION, positionField, row++);

        salaryField = new TextField();
        salaryField.setPromptText("Example: 45000.00");
        addField(form, StringPool.SALARY, salaryField, row++);

        // Work authorization
        authorizedGroup = new ToggleGroup();

        RadioButton authorizedYes = new RadioButton("Yes");
        RadioButton authorizedNo = new RadioButton("No");

        authorizedYes.setToggleGroup(authorizedGroup);
        authorizedNo.setToggleGroup(authorizedGroup);

        HBox authorizedOptions = new HBox(20,
                authorizedYes, authorizedNo);

        addField(form, StringPool.AUTHORIZED, authorizedOptions, row++);

        // Relatives
        relativesGroup = new ToggleGroup();

        RadioButton relativesYes = new RadioButton("Yes");
        RadioButton relativesNo = new RadioButton("No");

        relativesYes.setToggleGroup(relativesGroup);
        relativesNo.setToggleGroup(relativesGroup);

        HBox relativesOptions = new HBox(20,
                relativesYes, relativesNo);

        addField(form, StringPool.RELATIVES, relativesOptions, row++);

        explanationArea = new TextArea();
        explanationArea.setPromptText("Provide details if applicable");
        explanationArea.setPrefRowCount(3);
        addField(form, StringPool.RELATIVES_EXPLANATION,
                explanationArea, row++);

        signatureField = new TextField();
        addField(form, StringPool.SIGNATURE, signatureField, row++);

        // Buttons
        Button submitButton = new Button(StringPool.SUBMIT);
        submitButton.getStyleClass().add("submit-button");

        Button clearButton = new Button(StringPool.CLEAR);
        clearButton.getStyleClass().add("clear-button");

        submitButton.setOnAction(event -> submitApplication());
        clearButton.setOnAction(event -> clearForm());

        HBox buttons = new HBox(15, submitButton, clearButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(20));

        VBox content = new VBox(10, header, form, buttons);
        content.setPadding(new Insets(15));

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        BorderPane root = new BorderPane(scrollPane);

        Scene scene = new Scene(root, 900, 750);

        var css = getClass().getResource("/application.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        stage.setTitle(StringPool.APP_TITLE);
        stage.setScene(scene);
        stage.setMinWidth(750);
        stage.setMinHeight(650);
        stage.show();
    }

    private void addSection(GridPane grid, String title, int row) {

        Label section = new Label(title);
        section.getStyleClass().add("section-heading");

        grid.add(section, 0, row, 2, 1);
    }

    private void addField(GridPane grid, String label,
                          javafx.scene.Node field, int row) {

        Label fieldLabel = new Label(label);
        fieldLabel.getStyleClass().add("field-label");

        grid.add(fieldLabel, 0, row);
        grid.add(field, 1, row);

        GridPane.setHgrow(field, Priority.ALWAYS);

        if (field instanceof Region region) {
            region.setMaxWidth(Double.MAX_VALUE);
        }
    }

    private void submitApplication() {

        try {

            if (nameField.getText().isBlank()
                    || emailField.getText().isBlank()
                    || genderGroup.getSelectedToggle() == null
                    || educationBox.getValue() == null
                    || authorizedGroup.getSelectedToggle() == null
                    || relativesGroup.getSelectedToggle() == null) {

                showAlert("Missing Information",
                        "Please complete all required fields.");
                return;
            }

            BigDecimal salary = null;

            if (!salaryField.getText().isBlank()) {
                salary = new BigDecimal(salaryField.getText());
            }

            String gender = ((RadioButton)
                    genderGroup.getSelectedToggle()).getText();

            String authorized = ((RadioButton)
                    authorizedGroup.getSelectedToggle()).getText();

            String relatives = ((RadioButton)
                    relativesGroup.getSelectedToggle()).getText();

            LocalDate availableDate =
                    dateAvailablePicker.getValue();

            User user = new User(
                    nameField.getText(),
                    addressField.getText(),
                    phoneField.getText(),
                    emailField.getText(),
                    educationBox.getValue(),
                    gender,
                    availableDate,
                    positionField.getText(),
                    salary,
                    authorized,
                    relatives,
                    explanationArea.getText(),
                    signatureField.getText()
            );

            if (userDAO.saveUser(user)) {
                showAlert("Success",
                        "Employment application submitted successfully!");
                clearForm();
            } else {
                showAlert("Database Error",
                        "The application could not be saved.");
            }

        } catch (NumberFormatException e) {
            showAlert("Invalid Salary",
                    "Please enter a valid salary amount.");
        }
    }

    private void clearForm() {

        nameField.clear();
        addressField.clear();
        phoneField.clear();
        emailField.clear();
        educationBox.setValue(null);
        genderGroup.selectToggle(null);

        dateAvailablePicker.setValue(null);
        positionField.clear();
        salaryField.clear();
        authorizedGroup.selectToggle(null);
        relativesGroup.selectToggle(null);
        explanationArea.clear();
        signatureField.clear();
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
