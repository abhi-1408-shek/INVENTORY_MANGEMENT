package view;

import dao.SupplierDAO;
import model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SupplierView {
    private final SupplierDAO supplierDAO = new SupplierDAO();

    public void show(Stage stage) {
        TableView<Supplier> table = new TableView<>();
        ObservableList<Supplier> data = FXCollections.observableArrayList(supplierDAO.getAllSuppliers());
        table.setItems(data);

        TableColumn<Supplier, String> nameCol = new TableColumn<>("Supplier Name");
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        TableColumn<Supplier, String> contactCol = new TableColumn<>("Contact Info");
        contactCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getContactInfo()));
        table.getColumns().addAll(nameCol, contactCol);
        // Add tooltips for each row
        table.setRowFactory(tv -> {
            TableRow<Supplier> row = new TableRow<>();
            row.itemProperty().addListener((obs, oldItem, newItem) -> {
                if (newItem != null) {
                    row.setTooltip(new Tooltip("Name: " + newItem.getName() + "\nContact: " + newItem.getContactInfo()));
                } else {
                    row.setTooltip(null);
                }
            });
            return row;
        });

        Button addBtn = new Button("Add Supplier");
        addBtn.setOnAction(e -> showSupplierForm(stage, null, data));
        Button editBtn = new Button("Edit Selected");
        editBtn.setOnAction(e -> {
            Supplier s = table.getSelectionModel().getSelectedItem();
            if (s != null) showSupplierForm(stage, s, data);
        });
        Button delBtn = new Button("Delete Selected");
        delBtn.setOnAction(e -> {
            Supplier s = table.getSelectionModel().getSelectedItem();
            if (s != null) {
                supplierDAO.deleteSupplier(s.getId());
                data.setAll(supplierDAO.getAllSuppliers());
            }
        });

        HBox btns = new HBox(10, addBtn, editBtn, delBtn);
        VBox layout = new VBox(10, table, btns);
        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout, 500, 350));
        stage.setTitle("Suppliers");
        stage.show();
    }

    private void showSupplierForm(Stage stage, Supplier supplier, ObservableList<Supplier> data) {
        Stage formStage = new Stage();
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));
        TextField nameField = new TextField();
        TextField contactField = new TextField();
        if (supplier != null) {
            nameField.setText(supplier.getName());
            contactField.setText(supplier.getContactInfo());
        }
        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            String name = nameField.getText();
            String contact = contactField.getText();
            if (supplier == null) {
                supplierDAO.addSupplier(new Supplier(0, name, contact));
            } else {
                supplier.setName(name);
                supplier.setContactInfo(contact);
                supplierDAO.updateSupplier(supplier);
            }
            data.setAll(supplierDAO.getAllSuppliers());
            formStage.close();
        });
        form.getChildren().addAll(
            new Label("Name:"), nameField,
            new Label("Contact Info:"), contactField,
            saveBtn
        );
        formStage.setScene(new Scene(form));
        formStage.setTitle(supplier == null ? "Add Supplier" : "Edit Supplier");
        formStage.show();
    }
}
