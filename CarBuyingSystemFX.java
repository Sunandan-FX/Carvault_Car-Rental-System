
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CarBuyingSystemFX extends Application {
    private static final Logger logger = Logger.getLogger(CarBuyingSystemFX.class.getName());
    private Showroom showroom;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showroom = new Showroom();

        primaryStage.setTitle("Car Buying System");

        
        Label welcomeLabel = new Label("Welcome to Wheel-Emporium Zone!");
        welcomeLabel.setStyle("-fx-font-size: 35px; -fx-text-fill: #333;");

        Button visitButton = new Button("Visit");
        visitButton.setStyle("-fx-background-color: #008080; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5; -fx-font-size: 24px;");
        visitButton.setOnAction(e -> showAvailableCars());

        VBox welcomeLayout = new VBox(10);
        welcomeLayout.setStyle("-fx-padding: 20; -fx-background-color: #ADD8E6;"); 
        welcomeLayout.setAlignment(Pos.CENTER); 
        welcomeLayout.getChildren().addAll(welcomeLabel, visitButton);
        
        Scene welcomeScene = new Scene(welcomeLayout, 800, 600);
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    private void showAvailableCars() {
        
        TextArea carListArea = new TextArea();
        carListArea.setEditable(false);//not editable
        carListArea.setText(showroom.getAvailableCars());
        carListArea.setStyle("-fx-background-color: #90EE90; -fx-text-fill: #000000; -fx-border-color: #cccccc; -fx-border-radius: 5;"); 

        
        ComboBox<String> carModelComboBox = new ComboBox<>();
        carModelComboBox.getItems().addAll(showroom.getCarModels());
        carModelComboBox.setPromptText("Select a car model");

        // Create buttons for payment options
        Button payInCashButton = new Button("Pay in Cash");
        payInCashButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5;");
        payInCashButton.setOnAction(e -> handlePayment("Cash", carModelComboBox));

        Button payInCheckButton = new Button("Pay in Check");
        payInCheckButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5;");
        payInCheckButton.setOnAction(e -> handlePayment("Check", carModelComboBox));

        VBox carSelectionLayout = new VBox(10);
        carSelectionLayout.setStyle("-fx-padding: 20; -fx-background-color: #ADD8E6;"); 
        carSelectionLayout.setAlignment(Pos.CENTER);
        carSelectionLayout.getChildren().addAll(carListArea, carModelComboBox, payInCashButton, payInCheckButton);

        Scene carSelectionScene = new Scene(carSelectionLayout, 800, 600);
        primaryStage.setScene(carSelectionScene);
    }

    private void handlePayment(String paymentMethod, ComboBox<String> carModelComboBox) {
        String carModel = carModelComboBox.getValue(); 

        if (carModel == null) {
            showAlert("Error", "Please select a car model.");
            return;
        }

        try {
            Car purchasedCar = showroom.buyCar(carModel);
            String confirmationMessage = "Congratulations! You have purchased a " + purchasedCar.getModel() + " " + purchasedCar.getColour() + " for $" + purchasedCar.getPrice() + ".";
            String paymentDetails = "Payment Method: " + paymentMethod;
            showCustomerDetails(confirmationMessage, paymentDetails, purchasedCar);
        } catch (Exception x) {
            logger.log(Level.SEVERE, "Error purchasing car", x);
            showAlert("Error", "Error purchasing car: " + x.getMessage());
        }
    }

    private void showCustomerDetails(String confirmationMessage, String paymentDetails, Car purchasedCar) {
        
        Label confirmationLabel = new Label(confirmationMessage);
        confirmationLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #333;");

        Label paymentLabel = new Label(paymentDetails);
        paymentLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #333;");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");
        nameField.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #cccccc; -fx-border-radius: 5;");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #cccccc; -fx-border-radius: 5;");

        TextField nidField = new TextField();
        nidField.setPromptText("Enter your National ID (NID)");
        nidField.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #cccccc; -fx-border-radius: 5;");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter your phone number");
        phoneField.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #cccccc; -fx-border-radius: 5;");

        Button confirmButton = new Button("Confirm Purchase");
        confirmButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5;");
        confirmButton.setOnAction(e -> {
           
            String customerName = nameField.getText();
            String email = emailField.getText();
            String nid = nidField.getText();
            String phoneNumber = phoneField.getText();
          
            showOutputResult(confirmationMessage, paymentDetails, purchasedCar, customerName, email, nid, phoneNumber);
        });

        VBox customerDetailsLayout = new VBox(10);
        customerDetailsLayout.setStyle("-fx-padding: 20; -fx-background-color: #ADD8E6;"); // Light blue background
        customerDetailsLayout.setAlignment(Pos.CENTER); 
        customerDetailsLayout.getChildren().addAll(confirmationLabel, paymentLabel, nameField, emailField, nidField, phoneField, confirmButton);

        Scene customerDetailsScene = new Scene(customerDetailsLayout, 800, 600);
        primaryStage.setScene(customerDetailsScene);
    }

    private void showOutputResult(String confirmationMessage, String paymentDetails, Car purchasedCar, String customerName, String email, String nid, String phoneNumber) {
        
        Label resultLabel = new Label("Purchase Confirmation");
        resultLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #333;");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setText("Confirmation Message: " + confirmationMessage + "\n" +
                           paymentDetails + "\n" +
                           "Car Model: " + purchasedCar.getModel() + "\n" +
                           "Color: " + purchasedCar.getColour() + "\n" +
                           "Mileage: " + purchasedCar.getMileage() + "\n" +
                           "Price: $" + purchasedCar.getPrice() + "\n" +
                           "Engine Details: " + purchasedCar.getEngineDetails() + "\n" +
                           "Customer Name: " + customerName + "\n" +
                           "Email: " + email + "\n" +
                           "NID: " + nid + "\n" +
                           "Phone Number: " + phoneNumber);
        resultArea.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-border-color: #cccccc; -fx-border-radius: 5;");

        Button backButton = new Button("Back to Car Selection");
        backButton.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white; -fx-padding: 10px ; -fx-border-radius: 5;");
        backButton.setOnAction(e -> showAvailableCars()); 

        Button exportButton = new Button("Export Purchase Details");
        exportButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: black; -fx-padding: 10px; -fx-border-radius: 5;");
        exportButton.setOnAction(e -> exportPurchaseDetails(confirmationMessage, paymentDetails, purchasedCar, customerName, email, nid, phoneNumber));

        VBox outputLayout = new VBox(10);
        outputLayout.setStyle("-fx-padding: 20; -fx-background-color: #ADD8E6;"); // Light blue background
        outputLayout.setAlignment(Pos.CENTER);
        outputLayout.getChildren().addAll(resultLabel, resultArea, backButton, exportButton);

        Scene outputScene = new Scene(outputLayout, 800, 600);
        primaryStage.setScene(outputScene);
    }

    private void exportPurchaseDetails(String confirmationMessage, String paymentDetails, Car purchasedCar, String customerName, String email, String nid, String phoneNumber) {
        String fileName = "purchase_details.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Purchase Confirmation\n");
            writer.write("Confirmation Message: " + confirmationMessage + "\n");
            writer.write(paymentDetails + "\n");
            writer.write("Car Model: " + purchasedCar.getModel() + "\n");
            writer.write("Color: " + purchasedCar.getColour() + "\n");
            writer.write("Mileage: " + purchasedCar.getMileage() + "\n");
            writer.write("Price: $" + purchasedCar.getPrice() + "\n");
            writer.write("Engine Details: " + purchasedCar.getEngineDetails() + "\n");
            writer.write("Customer Name: " + customerName + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("NID: " + nid + "\n");
            writer.write("Phone Number: " + phoneNumber + "\n");
            writer.write("--------------------------------------------------\n");
            showAlert("Success", "Purchase details exported to " + fileName);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error exporting purchase details", e);
            showAlert("Error", "Error exporting purchase details: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait(); 
    }
}
