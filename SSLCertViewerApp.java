
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
public class SSLCertViewerApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField domainField = new TextField();
        domainField.setPromptText("Enter domain (e.g., www.example.com:443)");
        Button fetchButton = new Button("Fetch Certificate");

        TextArea certDisplay = new TextArea();
        certDisplay.setWrapText(true);
        certDisplay.setEditable(false);
        Label statusLabel = new Label("Status: ");

        fetchButton.setOnAction(e -> {
            String domain = domainField.getText().trim();
            if (domain.isEmpty()) {
                certDisplay.setText("Please enter a domain.");
                return;
            }
            try {
                String cert = CertificateFetcher.fetchCertificate(domain);
                certDisplay.setText(cert);
                String status = CertificateValidator.validate(cert);
                statusLabel.setText("Status: " + status);
            } catch (IOException ex) {
                certDisplay.setText("Error fetching certificate: " + ex.getMessage());
                statusLabel.setText("Status: Invalid");
            }
        });

        VBox root = new VBox(10, domainField, fetchButton, certDisplay, statusLabel);
        root.setPadding(new javafx.geometry.Insets(10));
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("SSL Certificate Viewer & Validator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

