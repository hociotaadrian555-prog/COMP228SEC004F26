
package com.week9.ah.Assignment6;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HospitalApp extends Application {

    // Database access
    private final PatientDAO patientDAO = new PatientDAO();

    // Patient form fields
    private TextField patientIdField;
    private TextField nameField;
    private TextField ageField;
    private ComboBox<String> genderBox;
    private TextField phoneField;
    private TextField diagnosisField;
    private DatePicker admissionDatePicker;

    // Table and search
    private TableView<Patient> patientTable;
    private TextField searchField;

    // Status message
    private Label statusLabel;

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        // ================= HEADER =================

        Label title = new Label("Hospital Management System");
        title.setStyle(
            "-fx-font-size: 28px; -fx-font-weight: bold;"
        );

        Label subtitle = new Label(
            "Patient Registration and Records Management"
        );

        VBox header = new VBox(8, title, subtitle);
        header.setPadding(new Insets(15));

        root.setTop(header);

        // ================= PATIENT FORM =================

        Label formTitle = new Label("Patient Details");
        formTitle.setStyle(
            "-fx-font-size: 20px; -fx-font-weight: bold;"
        );

        patientIdField = new TextField();
        patientIdField.setEditable(false);
        patientIdField.setPromptText("Auto-generated");

        nameField = new TextField();
        nameField.setPromptText("Enter full name");

        ageField = new TextField();
        ageField.setPromptText("Enter age (0-125)");

        genderBox = new ComboBox<>();
        genderBox.getItems().addAll(
            "Female",
            "Male",
            "Non-binary",
            "Prefer not to say"
        );
        genderBox.setPromptText("Select gender");

        phoneField = new TextField();
        phoneField.setPromptText("Enter phone number");

        diagnosisField = new TextField();
        diagnosisField.setPromptText("Enter diagnosis");

        admissionDatePicker = new DatePicker();
        admissionDatePicker.setPromptText("Admission date");

        // GridPane form layout
        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(15);

        form.add(new Label("Patient ID:"), 0, 0);
        form.add(patientIdField, 1, 0);

        form.add(new Label("Full Name:"), 0, 1);
        form.add(nameField, 1, 1);

        form.add(new Label("Age:"), 0, 2);
        form.add(ageField, 1, 2);

        form.add(new Label("Gender:"), 0, 3);
        form.add(genderBox, 1, 3);

        form.add(new Label("Phone:"), 0, 4);
        form.add(phoneField, 1, 4);

        form.add(new Label("Diagnosis:"), 0, 5);
        form.add(diagnosisField, 1, 5);

        form.add(new Label("Admission Date:"), 0, 6);
        form.add(admissionDatePicker, 1, 6);

        // Field sizing
        nameField.setMaxWidth(Double.MAX_VALUE);
        ageField.setMaxWidth(Double.MAX_VALUE);
        genderBox.setMaxWidth(Double.MAX_VALUE);
        phoneField.setMaxWidth(Double.MAX_VALUE);
        diagnosisField.setMaxWidth(Double.MAX_VALUE);
        admissionDatePicker.setMaxWidth(Double.MAX_VALUE);

        // ================= ACTION BUTTONS =================

        Button addButton = new Button("Add Patient");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button clearButton = new Button("Clear");

        HBox buttons = new HBox(
            10, addButton, updateButton, deleteButton, clearButton
        );

        VBox formContainer = new VBox(
            20, formTitle, form, buttons
        );

        formContainer.setPadding(new Insets(15));
        formContainer.setPrefWidth(430);
        formContainer.setStyle(
            "-fx-border-color: lightgray; -fx-border-radius: 8px;"
        );

        // ================= PATIENT TABLE =================

        Label recordsTitle = new Label("Patient Records");
        recordsTitle.setStyle(
            "-fx-font-size: 20px; -fx-font-weight: bold;"
        );

        searchField = new TextField();
        searchField.setPromptText("Search by name or phone");

        Button searchButton = new Button("Search");
        Button showAllButton = new Button("Show All");

        HBox searchArea = new HBox(
            10, searchField, searchButton, showAllButton
        );

        HBox.setHgrow(searchField, Priority.ALWAYS);

        patientTable = new TableView<>();
        patientTable.setPlaceholder(
            new Label("No patient records available")
        );

        // Patient ID column
        TableColumn<Patient, Integer> idColumn =
            new TableColumn<>("ID");

        idColumn.setCellValueFactory(
            cell -> cell.getValue().patientIdProperty().asObject()
        );

        // Name column
        TableColumn<Patient, String> nameColumn =
            new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
            cell -> cell.getValue().nameProperty()
        );

        // Age column
        TableColumn<Patient, Integer> ageColumn =
            new TableColumn<>("Age");

        ageColumn.setCellValueFactory(
            cell -> cell.getValue().ageProperty().asObject()
        );

        // Gender column
        TableColumn<Patient, String> genderColumn =
            new TableColumn<>("Gender");

        genderColumn.setCellValueFactory(
            cell -> cell.getValue().genderProperty()
        );

        // Phone column
        TableColumn<Patient, String> phoneColumn =
            new TableColumn<>("Phone");

        phoneColumn.setCellValueFactory(
            cell -> cell.getValue().phoneProperty()
        );

        // Diagnosis column
        TableColumn<Patient, String> diagnosisColumn =
            new TableColumn<>("Diagnosis");

        diagnosisColumn.setCellValueFactory(
            cell -> cell.getValue().diagnosisProperty()
        );

        // Admission date column
        TableColumn<Patient, LocalDate> dateColumn =
            new TableColumn<>("Admission Date");

        dateColumn.setCellValueFactory(
            cell -> cell.getValue().admissionDateProperty()
        );

        patientTable.getColumns().addAll(
            idColumn,
            nameColumn,
            ageColumn,
            genderColumn,
            phoneColumn,
            diagnosisColumn,
            dateColumn
        );

        patientTable.setColumnResizePolicy(
            TableView.CONSTRAINED_RESIZE_POLICY
        );

        VBox recordsContainer = new VBox(
            15, recordsTitle, searchArea, patientTable
        );

        recordsContainer.setPadding(new Insets(15));
        HBox.setHgrow(recordsContainer, Priority.ALWAYS);
        VBox.setVgrow(patientTable, Priority.ALWAYS);

        // ================= CENTER LAYOUT =================

        HBox centerLayout = new HBox(
            20, formContainer, recordsContainer
        );

        HBox.setHgrow(recordsContainer, Priority.ALWAYS);
        root.setCenter(centerLayout);

        // ================= STATUS BAR =================

        statusLabel = new Label("Ready.");
        statusLabel.setPadding(new Insets(12));

        root.setBottom(statusLabel);

        // ================= BUTTON EVENTS =================

        addButton.setOnAction(event -> addPatient());

        updateButton.setOnAction(event -> updatePatient());

        deleteButton.setOnAction(event -> deletePatient());

        clearButton.setOnAction(event -> clearForm());

        searchButton.setOnAction(event -> searchPatients());

        showAllButton.setOnAction(event -> {
            searchField.clear();
            loadPatients();
        });

        // Selecting a table row fills the form
        patientTable.getSelectionModel()
            .selectedItemProperty()
            .addListener((observable, oldValue, selectedPatient) -> {

                if (selectedPatient != null) {
                    populateForm(selectedPatient);
                }
            });

        // ================= WINDOW =================

        Scene scene = new Scene(root, 1250, 750);

        stage.setTitle("Hospital Management System");
        stage.setMinWidth(1050);
        stage.setMinHeight(650);
        stage.setScene(scene);
        stage.show();

        // Load existing records when application starts
        loadPatients();
    }

    // ================= VALIDATION =================

    private Patient getPatientFromForm(boolean updating) {

        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String phone = phoneField.getText().trim();
        String diagnosis = diagnosisField.getText().trim();

        String gender = genderBox.getValue();
        LocalDate admissionDate = admissionDatePicker.getValue();

        if (name.isEmpty() || ageText.isEmpty()
                || phone.isEmpty() || diagnosis.isEmpty()
                || gender == null || admissionDate == null) {

            throw new IllegalArgumentException(
                "Please complete all patient fields."
            );
        }

        if (!ageText.matches("\\d+")) {
            throw new IllegalArgumentException(
                "Age must be a whole number."
            );
        }

        int age = Integer.parseInt(ageText);

        if (age < 0 || age > 125) {
            throw new IllegalArgumentException(
                "Age must be between 0 and 125."
            );
        }

        if (!phone.matches("[0-9+()\\-\\s]{7,20}")
                || phone.replaceAll("\\D", "").length() < 7) {

            throw new IllegalArgumentException(
                "Please enter a valid phone number."
            );
        }

        if (admissionDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                "Admission date cannot be in the future."
            );
        }

        int patientId = 0;

        if (updating) {
            patientId = Integer.parseInt(patientIdField.getText());
        }

        return new Patient(
            patientId,
            name,
            age,
            gender,
            phone,
            diagnosis,
            admissionDate
        );
    }

    // ================= CREATE =================

    private void addPatient() {

        try {
            Patient patient = getPatientFromForm(false);

            patientDAO.addPatient(patient);

            loadPatients();
            clearForm();

            statusLabel.setText("Patient added successfully!");

        } catch (IllegalArgumentException e) {

            statusLabel.setText(e.getMessage());

        } catch (SQLException e) {

            statusLabel.setText(
                "Database error: " + e.getMessage()
            );
            e.printStackTrace();
        }
    }

    // ================= READ =================

    private void loadPatients() {

        try {
            patientTable.setItems(
                FXCollections.observableArrayList(
                    patientDAO.getAllPatients()
                )
            );

            statusLabel.setText(
                "Patient records loaded successfully."
            );

        } catch (SQLException e) {

            statusLabel.setText(
                "Unable to load patients: " + e.getMessage()
            );
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================

    private void updatePatient() {

        if (patientIdField.getText().isEmpty()) {
            statusLabel.setText(
                "Please select a patient to update."
            );
            return;
        }

        try {
            Patient patient = getPatientFromForm(true);

            patientDAO.updatePatient(patient);

            loadPatients();
            clearForm();

            statusLabel.setText("Patient updated successfully!");

        } catch (IllegalArgumentException e) {

            statusLabel.setText(e.getMessage());

        } catch (SQLException e) {

            statusLabel.setText(
                "Database error: " + e.getMessage()
            );
            e.printStackTrace();
        }
    }

    // ================= DELETE =================

    private void deletePatient() {

        if (patientIdField.getText().isEmpty()) {
            statusLabel.setText(
                "Please select a patient to delete."
            );
            return;
        }

        Alert confirmation = new Alert(
            Alert.AlertType.CONFIRMATION
        );

        confirmation.setTitle("Delete Patient");
        confirmation.setHeaderText("Confirm Patient Deletion");
        confirmation.setContentText(
            "Are you sure you want to delete patient ID "
            + patientIdField.getText() + "?"
        );

        if (confirmation.showAndWait().orElse(ButtonType.CANCEL)
                == ButtonType.OK) {

            try {
                int patientId = Integer.parseInt(
                    patientIdField.getText()
                );

                patientDAO.deletePatient(patientId);

                loadPatients();
                clearForm();

                statusLabel.setText(
                    "Patient deleted successfully!"
                );

            } catch (SQLException e) {

                statusLabel.setText(
                    "Database error: " + e.getMessage()
                );
                e.printStackTrace();
            }
        }
    }

    // ================= SEARCH =================

    private void searchPatients() {

        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {
            loadPatients();
            return;
        }

        try {
            patientTable.setItems(
                FXCollections.observableArrayList(
                    patientDAO.searchPatients(keyword)
                )
            );

            statusLabel.setText(
                "Search completed successfully."
            );

        } catch (SQLException e) {

            statusLabel.setText(
                "Search failed: " + e.getMessage()
            );
            e.printStackTrace();
        }
    }

    // ================= SELECT PATIENT =================

    private void populateForm(Patient patient) {

        patientIdField.setText(
            String.valueOf(patient.getPatientId())
        );

        nameField.setText(patient.getName());

        ageField.setText(
            String.valueOf(patient.getAge())
        );

        genderBox.setValue(patient.getGender());

        phoneField.setText(patient.getPhone());

        diagnosisField.setText(patient.getDiagnosis());

        admissionDatePicker.setValue(
            patient.getAdmissionDate()
        );

        statusLabel.setText(
            "Patient " + patient.getPatientId() + " selected."
        );
    }

    // ================= CLEAR FORM =================

    private void clearForm() {

        patientIdField.clear();
        nameField.clear();
        ageField.clear();
        genderBox.setValue(null);
        phoneField.clear();
        diagnosisField.clear();
        admissionDatePicker.setValue(null);

        patientTable.getSelectionModel().clearSelection();

        statusLabel.setText("Form cleared.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
