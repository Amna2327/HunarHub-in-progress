class Item {
    private String itemId;
    private String makerId;
    private String title;
    private String description;
    private String category;
    private double price;
    private String makerName;
    private int Stock;
    private List<Review> reviews = new ArrayList<>();


    public Item(String itemId, String title, String description, double price, String makerName,String category,String makerId, int Stock) {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.makerName=makerName;
        this.category=category;
        this.makerId=makerId;
        this.Stock=Stock;
    }

    // Getters
    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getMakerName() {
        return makerName;
    }
    public String getMakerID(){
        return makerId;
    }
    public String getCategory(){
        return category;
    }

    public int getStock() {
        return Stock;
    }
     
    //setters
    void setTitle(String title){
       this.title=title;
    }

    void setPrice(Double price){
        this.price=price;
    }

    void setDescription(String description){
        this.description=description;
    }

    void setStock(int Stock){
      this.Stock=Stock;
    }
    @Override
    public String toString() {
        return "[" + itemId + "] " + title + " - Rs. " + price + "\n  Description: " + description +
               "\n  Maker: " + makerName;
    }
    public void addReview(Review review) {
        reviews.add(review);
        FilehandlingforReviews.writeFile(review);
    }
    public void showReviews() {
        if (reviews.isEmpty()) {
            System.out.println("No reviews for this item yet.");
        } else {
            System.out.println("Reviews:");
            for (Review r : reviews) {
                System.out.println(r);
            }
        }
    }
    public List<Review> getReviews() {
        return reviews;
    }
    
    
}
