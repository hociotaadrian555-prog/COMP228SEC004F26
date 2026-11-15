
package com.week9.ah.Assignment6;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // ================= CREATE =================

    public void addPatient(Patient patient) throws SQLException {

        String sql = "INSERT INTO patients "
                + "(name, age, gender, phone, diagnosis, admission_date) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getPhone());
            statement.setString(5, patient.getDiagnosis());
            statement.setDate(6,
                    Date.valueOf(patient.getAdmissionDate()));

            statement.executeUpdate();
        }
    }

    // ================= READ =================

    public List<Patient> getAllPatients() throws SQLException {

        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patients ORDER BY patient_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Patient patient = new Patient(
                    result.getInt("patient_id"),
                    result.getString("name"),
                    result.getInt("age"),
                    result.getString("gender"),
                    result.getString("phone"),
                    result.getString("diagnosis"),
                    result.getDate("admission_date").toLocalDate()
                );

                patients.add(patient);
            }
        }

        return patients;
    }

    // ================= UPDATE =================

    public void updatePatient(Patient patient) throws SQLException {

        String sql = "UPDATE patients SET name=?, age=?, gender=?, "
                + "phone=?, diagnosis=?, admission_date=? "
                + "WHERE patient_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getPhone());
            statement.setString(5, patient.getDiagnosis());
            statement.setDate(6,
                    Date.valueOf(patient.getAdmissionDate()));
            statement.setInt(7, patient.getPatientId());

            statement.executeUpdate();
        }
    }

    // ================= DELETE =================

    public void deletePatient(int patientId) throws SQLException {

        String sql = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            statement.executeUpdate();
        }
    }

    // ================= SEARCH =================

    public List<Patient> searchPatients(String keyword)
            throws SQLException {

        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patients "
                + "WHERE name LIKE ? OR phone LIKE ? "
                + "ORDER BY patient_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {

                    Patient patient = new Patient(
                        result.getInt("patient_id"),
                        result.getString("name"),
                        result.getInt("age"),
                        result.getString("gender"),
                        result.getString("phone"),
                        result.getString("diagnosis"),
                        result.getDate("admission_date").toLocalDate()
                    );

                    patients.add(patient);
                }
            }
        }

        return patients;
    }
}
