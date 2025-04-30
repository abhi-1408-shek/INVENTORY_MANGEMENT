package view;

import dao.CategoryDAO;
import model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CategoryView {
    private final CategoryDAO categoryDAO = new CategoryDAO();

    public void show(Stage stage) {
        TableView<Category> table = new TableView<>();
        ObservableList<Category> data = FXCollections.observableArrayList(categoryDAO.getAllCategories());
        table.setItems(data);

        TableColumn<Category, String> nameCol = new TableColumn<>("Category Name");
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        table.getColumns().addAll(nameCol);
        // Add tooltips for each row
        table.setRowFactory(tv -> {
            TableRow<Category> row = new TableRow<>();
            row.itemProperty().addListener((obs, oldItem, newItem) -> {
                if (newItem != null) {
                    row.setTooltip(new Tooltip("Category: " + newItem.getName()));
                } else {
                    row.setTooltip(null);
                }
            });
            return row;
        });

        Button addBtn = new Button("Add Category");
        addBtn.setOnAction(e -> showCategoryForm(stage, null, data));
        Button editBtn = new Button("Edit Selected");
        editBtn.setOnAction(e -> {
            Category c = table.getSelectionModel().getSelectedItem();
            if (c != null) showCategoryForm(stage, c, data);
        });
        Button delBtn = new Button("Delete Selected");
        delBtn.setOnAction(e -> {
            Category c = table.getSelectionModel().getSelectedItem();
            if (c != null) {
                categoryDAO.deleteCategory(c.getId());
                data.setAll(categoryDAO.getAllCategories());
            }
        });

        HBox btns = new HBox(10, addBtn, editBtn, delBtn);
        VBox layout = new VBox(10, table, btns);
        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout, 400, 300));
        stage.setTitle("Categories");
        stage.show();
    }

    private void showCategoryForm(Stage stage, Category category, ObservableList<Category> data) {
        Stage formStage = new Stage();
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));
        TextField nameField = new TextField();
        if (category != null) {
            nameField.setText(category.getName());
        }
        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            String name = nameField.getText();
            if (category == null) {
                categoryDAO.addCategory(new Category(0, name));
            } else {
                category.setName(name);
                categoryDAO.updateCategory(category);
            }
            data.setAll(categoryDAO.getAllCategories());
            formStage.close();
        });
        form.getChildren().addAll(
            new Label("Name:"), nameField,
            saveBtn
        );
        formStage.setScene(new Scene(form));
        formStage.setTitle(category == null ? "Add Category" : "Edit Category");
        formStage.show();
    }
}
