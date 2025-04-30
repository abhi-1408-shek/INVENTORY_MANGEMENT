package model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int productId;
    private int quantity;
    private LocalDateTime date;
    private String type; // 'sale' or 'purchase'

    public Transaction(int id, int productId, int quantity, LocalDateTime date, String type) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.date = date;
        this.type = type;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}

