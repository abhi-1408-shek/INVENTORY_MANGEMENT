package view;

import dao.ProductDAO;
import dao.TransactionDAO;
import model.Product;
import model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionView {
    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final ProductDAO productDAO = new ProductDAO();

    public void show(Stage stage) {
        TableView<Transaction> table = new TableView<>();
        ObservableList<Transaction> data = FXCollections.observableArrayList(transactionDAO.getAllTransactions());
        table.setItems(data);

        TableColumn<Transaction, Number> productIdCol = new TableColumn<>("Product ID");
        productIdCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getProductId()));
        TableColumn<Transaction, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getType()));
        TableColumn<Transaction, Number> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getQuantity()));
        TableColumn<Transaction, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDate().toString()));
        table.getColumns().addAll(productIdCol, typeCol, qtyCol, dateCol);

        Button addBtn = new Button("Add Transaction");
        addBtn.setOnAction(e -> showTransactionForm(stage, data));

        HBox btns = new HBox(10, addBtn);
        VBox layout = new VBox(10, table, btns);
        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout, 600, 350));
        stage.setTitle("Transactions");
        stage.show();
    }

    private void showTransactionForm(Stage stage, ObservableList<Transaction> data) {
        Stage formStage = new Stage();
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));
        ComboBox<Product> productBox = new ComboBox<>();
        List<Product> products = productDAO.getAllProducts();
        productBox.setItems(FXCollections.observableArrayList(products));
        TextField qtyField = new TextField();
        ComboBox<String> typeBox = new ComboBox<>(FXCollections.observableArrayList("sale", "purchase"));
        typeBox.setValue("sale");
        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            Product p = productBox.getValue();
            int qty = Integer.parseInt(qtyField.getText());
            String type = typeBox.getValue();
            Transaction t = new Transaction(0, p.getId(), qty, LocalDateTime.now(), type);
            transactionDAO.addTransaction(t);
            data.setAll(transactionDAO.getAllTransactions());
            formStage.close();
        });
        form.getChildren().addAll(
            new Label("Product:"), productBox,
            new Label("Quantity:"), qtyField,
            new Label("Type:"), typeBox,
            saveBtn
        );
        formStage.setScene(new Scene(form));
        formStage.setTitle("Add Transaction");
        formStage.show();
    }
}
