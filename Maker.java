class Maker extends User {
    private List<Item> items;

    public Maker(String userId, String name, String email, String password, String bankPassword) {
        super(userId, name, email, password,bankPassword);
        this.items = new ArrayList<>();
    }

    // Add product
    public void addItem(Item item) {
        items.add(item);
        System.out.println("Item added successfully.");
        FilehanddlingforItem.writeFile(item,"add");
    }

    //method when reading from file
    public void AddITEM(Item item){
        items.add(item);
    }

    // View all products added by this maker
    public void viewMyItems() {
        if (items.isEmpty()) {
            System.out.println("You have not added any items yet.");
        } else {
            int counter=1;
            System.out.println("Your Items:");
            for (Item item : items) {
                System.out.println(counter+". "+item);
                counter++;
            }
        }
    }

    // View account balance
    public void viewAccountBalance() {
        System.out.println("Current Balance: Rs. " + bankAccount.getBalance());
    }

    //remove product
    public void RemoveProduct(){
      Scanner scanner=new Scanner(System.in);
      viewMyItems();
      if (items.isEmpty()) {
         scanner.close();
         return;
      }

      System.out.println("Enter item serial number");
      int index=scanner.nextInt()-1;
      if(index<0||index>items.size()){
        System.out.println("Wrong serial number");
        scanner.close();
        return;
      }
      FilehanddlingforItem.writeFile(items.get(index),"remove");
      items.remove(index);
      scanner.close();
    }

    //modify product details
    public void ModifyProduct(){
      Scanner scanner=new Scanner(System.in);
      viewMyItems();
      int index=-1;
      if (items.isEmpty()) {
        scanner.close();
        return;
      }

      System.out.println("Enter item serial number");
      index=scanner.nextInt()-1;
      if(index<0||index>items.size()){
        System.out.println("Wrong serial number");
        scanner.close();
        return;
      }
      System.out.println("Choose one of the following");
      System.out.println("1.Modify Title");
      System.out.println("2.Modify Price");
      System.out.println("3.Modify Description");
      System.out.println("4.Modify Quantity");

      int choice=scanner.nextInt();
      while(choice!=1&&choice!=2&&choice!=3&&choice!=4){
        System.out.println("Invalid input,please enter again");
        choice=scanner.nextInt();
      }
      scanner.nextLine();

      if(choice==1){
        System.out.print("Enter item new title: ");
        items.get(index).setTitle(scanner.nextLine());
      }
      else if(choice==2){
        System.out.print("Enter item new price: ");
        items.get(index).setPrice(scanner.nextDouble());
      }
      else if(choice==3){
        System.out.print("Enter item new description: ");
        items.get(index).setDescription(scanner.nextLine());
      }
      else{
        System.out.print("Enter item updated stock quantity: ");
        items.get(index). setStock(scanner.nextInt());
      }
        FilehanddlingforItem.writeFile(items.get(index),"modify");
        scanner.close();
    }
      
    @Override
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Maker Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove product");
            System.out.println("3. Modify existing product details");
            System.out.println("4. View My Products");
            System.out.println("5. View Account Balance");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    ArrayList<String> CategoryList=new ArrayList<>(Arrays.asList("Ceramics","Brass and copper utensils","Wood carving","Marble carving","Blue pottery","Ajrak","Khussa","Truck art","Pashmina shawls","Basketry","Embroidered shawls"));
                    System.out.print("Enter item title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter item description: ");
                    String desc = scanner.nextLine();

                    System.out.print("Enter item price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    System.out.println("Your handicraft should fit one of these categories");
                    int counter=0;
                    for(String a:CategoryList){
                        System.out.println((counter)+"." +a);
                        counter++;
                    }
                    System.out.println("Enter the category number of your product");
                    int index=scanner.nextInt();
                    while(index<0&&index<CategoryList.size()){
                        System.out.println("Invalid choice, please enter again");
                        index=scanner.nextInt();
                    }
                    scanner.nextLine();
                    String category=CategoryList.get(index);

                    System.out.println("Enter available stock of your product");
                    int Stock=scanner.nextInt();

                    String itemId = "I" + (items.size() + 1); // simple ID generation
                    Item item = new Item(itemId, title, desc, price,this.name,category,this.getUserId(),Stock);
                    addItem(item);
                    break;
                
                case 2:
                     RemoveProduct();
                     break;
                case 3:
                    ModifyProduct();
                    break;

                case 4:
                    viewMyItems();
                    break;

                case 5:
                    viewAccountBalance();
                    break;
                
                case 6:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 6);
        scanner.close();
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public List<Item> getItems() {
        return items;
    }
}
