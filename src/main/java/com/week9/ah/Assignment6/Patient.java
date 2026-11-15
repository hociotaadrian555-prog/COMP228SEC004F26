package com.week9.ah.Assignment6;

import java.time.LocalDate;

import javafx.beans.property.*;

public class Patient {

    private final IntegerProperty patientId;
    private final StringProperty name;
    private final IntegerProperty age;
    private final StringProperty gender;
    private final StringProperty phone;
    private final StringProperty diagnosis;
    private final ObjectProperty<LocalDate> admissionDate;

    public Patient(int patientId, String name, int age,
                   String gender, String phone,
                   String diagnosis, LocalDate admissionDate) {

        this.patientId = new SimpleIntegerProperty(patientId);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.gender = new SimpleStringProperty(gender);
        this.phone = new SimpleStringProperty(phone);
        this.diagnosis = new SimpleStringProperty(diagnosis);
        this.admissionDate = new SimpleObjectProperty<>(admissionDate);
    }

    public int getPatientId() {
        return patientId.get();
    }

    public String getName() {
        return name.get();
    }

    public int getAge() {
        return age.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getDiagnosis() {
        return diagnosis.get();
    }

    public LocalDate getAdmissionDate() {
        return admissionDate.get();
    }

    public IntegerProperty patientIdProperty() {
        return patientId;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty diagnosisProperty() {
        return diagnosis;
    }

    public ObjectProperty<LocalDate> admissionDateProperty() {
        return admissionDate;
    }
}
