
package com.week10.ah;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    private static final String INSERT_SQL =
            "INSERT INTO employment_applications (" +
            "full_name, current_address, contact_number, " +
            "email_address, highest_education, gender, " +
            "date_available, desired_position, desired_salary, " +
            "authorized_to_work, relatives_working, " +
            "relatives_explanation, applicant_signature" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public boolean saveUser(User user) {

        try (Connection connection = Database.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_SQL)) {

            statement.setString(1, user.getFullName());
            statement.setString(2, user.getCurrentAddress());
            statement.setString(3, user.getContactNumber());
            statement.setString(4, user.getEmailAddress());
            statement.setString(5, user.getHighestEducation());
            statement.setString(6, user.getGender());

            if (user.getDateAvailable() != null) {
                statement.setDate(7,
                        java.sql.Date.valueOf(user.getDateAvailable()));
            } else {
                statement.setNull(7, java.sql.Types.DATE);
            }

            statement.setString(8, user.getDesiredPosition());
            statement.setBigDecimal(9, user.getDesiredSalary());
            statement.setString(10, user.getAuthorizedToWork());
            statement.setString(11, user.getRelativesWorking());
            statement.setString(12, user.getRelativesExplanation());
            statement.setString(13, user.getApplicantSignature());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error saving application: " + e.getMessage());
            return false;
        }
    }
}