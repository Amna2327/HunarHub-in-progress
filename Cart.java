class Cart {
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    // Add item to cart
    public void addItem(Item item) {
        items.add(item);
        System.out.println("Item added to cart.");
    }

    // Remove item by index
    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            Item removed = items.remove(index);
            System.out.println("Removed: " + removed.getTitle());
        } else {
            System.out.println("Invalid item number.");
        }
    }

    // Get all items
    public List<Item> getItems() {
        return items;
    }

    // Check if cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Show all cart items
    public void showCartItems() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\n--- Your Cart ---");
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.println((i + 1) + ". " + item.getTitle() + " - Rs. " + item.getPrice());
            }
        }
    }
}
