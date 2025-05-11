class Order {
    private String orderId;
    private Buyer buyer;
    private Item item;
    private double amount;
    private LocalDateTime orderDate;

    public Order(String orderId, Buyer buyer, Item item) {
        this.orderId = orderId;
        this.buyer = buyer;
        this.item = item;
        this.amount = item.getPrice();
        this.orderDate = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Item getItem() {
        return item;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    // Generates a simple receipt
    public String generateReceipt() {
        return "----- RECEIPT -----\n" +
               "Order ID: " + orderId + "\n" +
               "Buyer: " + buyer.getName() + "\n" +
               "Item: " + item.getTitle() + "\n" +
               "Seller: " + item.getMakerName() + "\n" +
               "Price: Rs. " + amount + "\n" +
               "Date: " + orderDate.toString() + "\n" +
               "-------------------";
    }
}
