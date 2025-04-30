import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import view.DashboardView;
import view.ProductView;
import view.SupplierView;
import view.CategoryView;
import view.TransactionView;

// Made by Abhishek: Main entry point for the Inventory Management System
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Made by Abhishek: Initialize the main menu and navigation buttons
        VBox mainMenu = new VBox(20);
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setPadding(new Insets(40));

        Button dashboardBtn = new Button("Dashboard");
        Button productsBtn = new Button("Products");
        Button suppliersBtn = new Button("Suppliers");
        Button categoriesBtn = new Button("Categories");
        Button transactionsBtn = new Button("Transactions");

        dashboardBtn.setPrefWidth(200);
        // Made by Abhishek: Set preferred width for all navigation buttons
        productsBtn.setPrefWidth(200);
        suppliersBtn.setPrefWidth(200);
        categoriesBtn.setPrefWidth(200);
        transactionsBtn.setPrefWidth(200);

        dashboardBtn.setOnAction(e -> new DashboardView().show(new Stage()));
        // Made by Abhishek: Button actions to open respective views
        productsBtn.setOnAction(e -> new ProductView().show(new Stage()));
        suppliersBtn.setOnAction(e -> new SupplierView().show(new Stage()));
        categoriesBtn.setOnAction(e -> new CategoryView().show(new Stage()));
        transactionsBtn.setOnAction(e -> new TransactionView().show(new Stage()));

        mainMenu.getChildren().addAll(dashboardBtn, productsBtn, suppliersBtn, categoriesBtn, transactionsBtn);

        Scene scene = new Scene(mainMenu, 400, 400);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Made by Abhishek: Launch JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
