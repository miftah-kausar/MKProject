package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        String schemaFile = "C:\\Users\\miffu\\Downloads\\SafeBet_Core_Features (2)\\schema.sql";  // Provide the correct relative path to the schema file

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Using a direct file path (relative to project directory)
            InputStream schemaStream = new FileInputStream(schemaFile);

            if (schemaStream == null) {
                throw new SQLException("Schema file not found.");
            }

            String schema = new String(schemaStream.readAllBytes());

            // Execute the schema to create tables
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(schema);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
