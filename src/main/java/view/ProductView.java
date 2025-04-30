package view;


import dao.CategoryDAO;
import dao.ProductDAO;
import dao.SupplierDAO;
import model.Category;
import model.Product;
import model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class ProductView {
    private final ProductDAO productDAO = new ProductDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final SupplierDAO supplierDAO = new SupplierDAO();

    public void show(Stage stage) {
        TableView<Product> table = new TableView<>();
        ObservableList<Product> data = FXCollections.observableArrayList(productDAO.getAllProducts());
        table.setItems(data);

        TableColumn<Product, String> nameCol = new TableColumn<>("Product Name");
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        TableColumn<Product, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(cell -> {
            Category cat = categoryDAO.getAllCategories().stream().filter(c -> c.getId() == cell.getValue().getCategoryId()).findFirst().orElse(null);
            return new javafx.beans.property.SimpleStringProperty(cat != null ? cat.getName() : "");
        });
        TableColumn<Product, String> supplierCol = new TableColumn<>("Supplier");
        supplierCol.setCellValueFactory(cell -> {
            Supplier sup = supplierDAO.getAllSuppliers().stream().filter(s -> s.getId() == cell.getValue().getSupplierId()).findFirst().orElse(null);
            return new javafx.beans.property.SimpleStringProperty(sup != null ? sup.getName() : "");
        });
        TableColumn<Product, String> supplierContactCol = new TableColumn<>("Supplier Contact");
        supplierContactCol.setCellValueFactory(cell -> {
            Supplier sup = supplierDAO.getAllSuppliers().stream().filter(s -> s.getId() == cell.getValue().getSupplierId()).findFirst().orElse(null);
            return new javafx.beans.property.SimpleStringProperty(sup != null ? sup.getContactInfo() : "");
        });
        TableColumn<Product, Number> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleDoubleProperty(cell.getValue().getPrice()));
        TableColumn<Product, Number> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getStock()));
        table.getColumns().addAll(nameCol, categoryCol, supplierCol, supplierContactCol, priceCol, stockCol);

        Button addBtn = new Button("Add Product");
        addBtn.setOnAction(e -> showProductForm(stage, null, data));
        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> {
            stage.close();
            // Navigation: just close the window for now. User can reopen main menu manually.
            // If you want to reopen main menu, run the Main class again from outside this context.
        });
        Button editBtn = new Button("Edit Selected");
        editBtn.setOnAction(e -> {
            Product p = table.getSelectionModel().getSelectedItem();
            if (p != null) showProductForm(stage, p, data);
        });
        Button delBtn = new Button("Delete Selected");
        delBtn.setOnAction(e -> {
            Product p = table.getSelectionModel().getSelectedItem();
            if (p != null) {
                productDAO.deleteProduct(p.getId());
                data.setAll(productDAO.getAllProducts());
            }
        });

        HBox btns = new HBox(10, addBtn, editBtn, delBtn);
        VBox layout = new VBox(10, table, btns);
        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout, 600, 400));
        stage.setTitle("Products");
        stage.show();
    }

    private void showProductForm(Stage parentStage, Product product, ObservableList<Product> data) {
        Stage formStage = new Stage();
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));
        TextField nameField = new TextField();
        TextField priceField = new TextField();
        TextField stockField = new TextField();
        ComboBox<Category> categoryBox = new ComboBox<>();
        ComboBox<Supplier> supplierBox = new ComboBox<>();
        // Always reload categories and suppliers
        List<Category> categories = categoryDAO.getAllCategories();
        List<Supplier> suppliers = supplierDAO.getAllSuppliers();
        categoryBox.setItems(FXCollections.observableArrayList(categories));
        supplierBox.setItems(FXCollections.observableArrayList(suppliers));
        if (product != null) {
            nameField.setText(product.getName());
            priceField.setText(String.valueOf(product.getPrice()));
            stockField.setText(String.valueOf(product.getStock()));
            for (Category c : categories) if (c.getId() == product.getCategoryId()) categoryBox.setValue(c);
            for (Supplier s : suppliers) if (s.getId() == product.getSupplierId()) supplierBox.setValue(s);
        }
        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            // Reload category/supplier in case they were added in another window
            List<Category> freshCategories = categoryDAO.getAllCategories();
            List<Supplier> freshSuppliers = supplierDAO.getAllSuppliers();
            categoryBox.setItems(FXCollections.observableArrayList(freshCategories));
            supplierBox.setItems(FXCollections.observableArrayList(freshSuppliers));
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            int catId = categoryBox.getValue().getId();
            int supId = supplierBox.getValue().getId();
            if (product == null) {
                productDAO.addProduct(new Product(0, name, catId, supId, price, stock));
            } else {
                product.setName(name);
                product.setPrice(price);
                product.setStock(stock);
                product.setCategoryId(catId);
                product.setSupplierId(supId);
                productDAO.updateProduct(product);
            }
            data.setAll(productDAO.getAllProducts());
            formStage.close();
        });
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> formStage.close());
        form.getChildren().addAll(
            new Label("Name:"), nameField,
            new Label("Price:"), priceField,
            new Label("Stock:"), stockField,
            new Label("Category:"), categoryBox,
            new Label("Supplier:"), supplierBox,
            new HBox(10, saveBtn, backBtn)
        );
        formStage.setScene(new Scene(form));
        formStage.setTitle(product == null ? "Add Product" : "Edit Product");
        formStage.show();
    }
}
