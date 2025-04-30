package view;

import dao.ProductDAO;
import dao.TransactionDAO;
import model.Product;
import model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class DashboardView {
    private final ProductDAO productDAO = new ProductDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public void show(Stage stage) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        Label title = new Label("Inventory Dashboard");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2a3b8f;");

        // Summary Section
        List<Product> products = productDAO.getAllProducts();
        int totalProducts = products.size();
        int totalCategories = (int) products.stream().map(Product::getCategoryId).distinct().count();
        int totalSuppliers = (int) products.stream().map(Product::getSupplierId).distinct().count();
        Label summary = new Label("Total Products: " + totalProducts + "    Total Categories: " + totalCategories + "    Total Suppliers: " + totalSuppliers);
        summary.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 5 0 15 0;");

        // Stock Level Bar Chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> stockChart = new BarChart<>(xAxis, yAxis);
        stockChart.setTitle("Stock Levels");
        xAxis.setLabel("Product");
        yAxis.setLabel("Stock");
        XYChart.Series<String, Number> stockSeries = new XYChart.Series<>();
        for (Product p : products) {
            stockSeries.getData().add(new XYChart.Data<>(p.getName(), p.getStock()));
        }
        stockChart.getData().add(stockSeries);

        // Sales Summary
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        long salesCount = transactions.stream().filter(t -> t.getType().equals("sale")).count();
        Label salesLabel = new Label("Total Sales Transactions: " + salesCount);
        salesLabel.setStyle("-fx-font-size: 14px; -fx-padding: 10 0 0 0;");

        layout.getChildren().addAll(title, summary, stockChart, salesLabel);
        stage.setScene(new Scene(layout, 700, 500));
        stage.setTitle("Dashboard");
        stage.show();
    }
}
