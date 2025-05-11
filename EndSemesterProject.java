import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
class EndSemesterProject{
    public static void main(String args[]){
       Scanner input=new Scanner(System.in);
       ArrayList<Maker>MakerList=new ArrayList<>();
       ArrayList<Buyer>BuyerList=new ArrayList<>();
        ArrayList<Item> ItemList=null;

       File Items=new File("Item.txt");
       try{
        Items.createNewFile();
       }
       catch(IOException e){
         System.out.println(e);
       }
       if(Items.length()!=0){
         ItemList=FilehanddlingforItem.readFile(MakerList);
       }
      
       File Makers=new File("Makers.txt");
       try{
        Makers.createNewFile();
       }
       catch(IOException e){
         System.out.println(e);
       }
       if(Makers.length()!=0){
         FileHandlingforMaker.readFile(MakerList);
       }

       File Buyers=new File("Buyers.txt");
       try{
        Buyers.createNewFile();
       }
       catch(IOException e){
         System.out.println(e);
       }
       if(Buyers.length()!=0){
         FileHandlingforBuyer.readFile(BuyerList);
       }

       File Reviews=new File("Reviews.txt");
       try{
        Reviews.createNewFile();
       }
       catch(IOException e){
         System.out.println(e);
       }
       if(Reviews.length()!=0){
         FilehandlingforReviews.readFile(ItemList);
       }
      
       Management m1=new Management();
       m1.Manage(input,MakerList,BuyerList);
       
    }
}

class FileHandlingforMaker{
 
  public static void readFile(ArrayList<Maker>MakerList){
    try{
        BufferedReader reader=new BufferedReader(new FileReader("Makers.txt"));
        String line;
        while((line=reader.readLine())!=null){
            String[] data=line.split(",");
            if (data.length >= 5) { // Ensure there are at least 5 elements
                Maker m = new Maker(data[0], data[1], data[2], data[3], data[4]);
                MakerList.add(m);
            } else {
                System.out.println("Skipping malformed line: " + line);
            }

        }
        reader.close();
    }
    catch(IOException e){
      System.out.println(e);
    }
  }
   // super(userId, name, email, password,bankPassword);
  public static void writeFile(Maker m){
    try{
        BufferedWriter writer=new BufferedWriter(new FileWriter("Makers.txt",true));
        writer.write(m.getUserId()+","
                     +m.getName()+","
                     +m.getEmail()+","
                     +m.password+","
                     +m.bankPassword+"\n");
        writer.close();
    }
    catch(IOException e){
        System.out.println(e);
    }
  }
}

class FileHandlingforBuyer{
 
  public static void readFile(ArrayList<Buyer>BuyerList){
    try{
        BufferedReader reader=new BufferedReader(new FileReader("Buyers.txt"));
        String line;
        while((line=reader.readLine())!=null){
            String[] data=line.split(",");
            if (data.length >= 5) { // Ensure there are at least 5 elements
                Buyer m = new Buyer(data[0], data[1], data[2], data[3], data[4]);
                BuyerList.add(m);
            } else {
                System.out.println("Skipping malformed line: " + line);
            }

        }
        reader.close();
    }
    catch(IOException e){
      System.out.println(e);
    }
  }
   // super(userId, name, email, password,bankPassword);
  public static void writeFile(Buyer m){
    try{
        BufferedWriter writer=new BufferedWriter(new FileWriter("Buyers.txt",true));
        writer.write(m.getUserId()+","
                     +m.getName()+","
                     +m.getEmail()+","
                     +m.password+","
                     +m.bankPassword+"\n");
        writer.close();
    }
    catch(IOException e){
        System.out.println(e);
    }
  }
}

class FilehandlingforReviews{

    public static void readFile(ArrayList<Item>ItemList){
        try{//Review(String reviewId,String reviewerName, Item item, int rating, String comment,String reviewerId) {
            BufferedReader reader=new BufferedReader(new FileReader("Reviews.txt"));
            String line;
            while((line=reader.readLine())!=null){
                String[] data=line.split(",");
                Review r=new Review( data[0],data[1],data[2],Integer.parseInt(data[3]),data[4],data[5]);
                for(int i=0;i<ItemList.size();i++){
                    if(ItemList.get(i).getItemId().equals(data[2]))
                        ItemList.get(i).addReview(r);
                }
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static void writeFile(Review review){
        try{
          BufferedWriter writer=new BufferedWriter(new FileWriter("Reviews.txt",true));
          writer.write(review.getReviewId()+","
                       +review.getReviewerName()+","
                       +review.getItemId()+","
                       +review.getRating()+","
                       +review.getComment()+","
                       +review.getReviewerId()+"\n");
          writer.close();
        }
        catch(IOException e){

        }
    }
}

class FilehanddlingforItem{
    static ArrayList<Item>ItemList=new ArrayList<>();
    
    public static ArrayList readFile(ArrayList<Maker>MakerList) {
       try{
         BufferedReader reader=new BufferedReader(new FileReader("Item.txt"));
         String line;
         while((line=reader.readLine())!=null){
           String pieces[]=line.split(",");
           Item item=new Item(pieces[0],pieces[1],pieces[2],Double.parseDouble(pieces[3]),pieces[4],pieces[5],pieces[6],Integer.parseInt(pieces[7]));
           ItemList.add(item);
         }
         reader.close();
       }
       catch(Exception e){
        System.out.println(e);
       }

       for(int i=0;i<MakerList.size();i++){
        for(int j=0;j<ItemList.size();j++){
           if(ItemList.get(j).getMakerID().equals(MakerList.get(i).getUserId()))
              MakerList.get(i).AddITEM(ItemList.get(j));
        }
       }
       return ItemList;
    }
    
    public static void  writeFile(Item item, String purpose){
        if(purpose.equals("add")){
            ItemList.add(item);
            try{
              FileWriter writer=new FileWriter("Item.txt",true);
              
                writer.write( ItemList.get(ItemList.size()-1).getItemId() + "," +
                              ItemList.get(ItemList.size()-1).getTitle() + "," +
                              ItemList.get(ItemList.size()-1).getDescription() + "," +
                              ItemList.get(ItemList.size()-1).getPrice() + "," +
                              ItemList.get(ItemList.size()-1).getMakerName() + "," +
                              ItemList.get(ItemList.size()-1).getCategory() + "," +
                              ItemList.get(ItemList.size()-1).getMakerID() + "," +
                              ItemList.get(ItemList.size()-1).getStock() + "\n"); // Add newline for each item
                              writer.close();
            }
            catch(IOException e){
               System.out.println(e);
            }
        }
        else if(purpose.equals("remove")){
          ItemList.remove(item);
         
            try{
              FileWriter writer=new FileWriter("Item.txt");
              for (Item it : ItemList) {
                writer.write(it.getItemId() + "," +
                             it.getTitle() + "," +
                             it.getDescription() + "," +
                             it.getPrice() + "," +
                             it.getMakerName() + "," +
                             it.getCategory() + "," +
                             it.getMakerID() + "," +
                             it.getStock() + "\n"); // Add newline for each item
                }
                writer.close();
                System.out.println("Your item has been removed");
            }
            catch(IOException e){
            System.out.println(e);
            }
         
        }
        
        else if(purpose.equals("modify")){
            int index=0;
           for(int i=0;i<ItemList.size();i++){
             if(ItemList.get(i).getItemId().equals(item.getMakerID()))
               index=i;
           }
          ItemList.remove(index);//removed old version of the item
          ItemList.add(item);//added modified version
         
            try{
              FileWriter writer=new FileWriter("Item.txt");
              for (Item it : ItemList) {
                writer.write(it.getItemId() + "," +
                             it.getTitle() + "," +
                             it.getDescription() + "," +
                             it.getPrice() + "," +
                             it.getMakerName() + "," +
                             it.getCategory() + "," +
                             it.getMakerID() + "," +
                             it.getStock() + "\n"); // Add newline for each item
                }
                writer.close();
                System.out.println("Your item has been modified");
            }
            catch(IOException e){
            System.out.println(e);
            }
         
        }
        
    }
}   
class Management{//class responsible for showing intial choice menu
    void Manage(Scanner input,ArrayList<Maker>MakerList,ArrayList<Buyer>BuyerList){
        System.out.println("\n=== Welcome to HunarHub ===");
        boolean continueLoop1=true;
        boolean continueLoop2=false;
        int choice=0;
        do{//loop for choices
         System.out.println("Choose one of the following");
         System.out.println("1.To login");
         System.out.println("2.To sign up");
         System.out.println("3.Exit");
         do{
             try{//for valid input
                 choice=input.nextInt();
                 if(choice!=1&&choice!=2&&choice!=3){//throws custom exception
                     continueLoop2=true;
                     throw new WrongInput("Please choose bwtween 1 and 3");
                 }
                 else 
                 continueLoop2=false;
             }
             catch(WrongInput e){
                 input.nextLine();
                 System.out.println(e);
             }
             catch(InputMismatchException e){//catches exception if input was not integer
                 input.nextLine();
                 System.out.println("Error: Please enter a valid integer between 1 and 3");
                 continueLoop2=true;
             }
         }while(continueLoop2);
         switch(choice){
             case 1:
                System.out.println("Choose your user account type");
                System.out.println("1.Maker");
                System.out.println("2.Buyer");
                int AccountType=input.nextInt();
                while(AccountType!=1&&AccountType!=2){
                    System.out.println("Wrong input, please enter again");
                    AccountType=input.nextInt();
                }
               
                Login login=new Login();
                if(AccountType==1)
                    login.LoginMenuMaker(MakerList,input);
                else
                    login.LoginMenuBuyer(BuyerList,input);
                break;

             case 2:
                Signup signup=new Signup();
                signup.SignUpMenu(input,MakerList,BuyerList);
                break;

             case 3:
                continueLoop1=false;
                break;
         }
 
        }while(continueLoop1);
        input.close();
    }
}
class Login{//for login
   void LoginMenuMaker(ArrayList<Maker>MakerList,Scanner input){
       System.out.println("--- Login ----");
       System.out.println("Enter password");
       boolean passwordExists=false;
       Maker A=null;//empty user refernce
       input.nextLine();
       do{
        try{
            String password=input.nextLine();
            for(Maker a:MakerList){
                if(password.equals(a.password)){
                   A=a;//points to the user in question
                   passwordExists=true;
                   break;
                }
            }
            if(passwordExists==false)
              throw new WrongInput("Entered password is wrong");
        }
        catch(WrongInput e){
            System.out.println(e);
        }
       }while(!passwordExists);
        A.showMenu();;
        return;
    }
    void LoginMenuBuyer(ArrayList<Buyer>BuyerList,Scanner input){
        System.out.println("--- Login ----");
       System.out.println("Enter password");
       boolean passwordExists=false;
       User A=null;//empty user refernce
       input.nextLine();
       do{
        try{
            String password=input.nextLine();
            for(Buyer a:BuyerList){
                if(password.equals(a.password)){
                   A=a;//points to the user in question
                   passwordExists=true;
                   break;
                }
            }
            if(passwordExists==false)
              throw new WrongInput("Entered password is wrong");
        }
        catch(WrongInput e){
            System.out.println(e);
        }
       }while(!passwordExists);
        A.showMenu();;
        return;
    }
}
class Signup{//for signup
    void SignUpMenu(Scanner input,ArrayList<Maker>MakerList,ArrayList<Buyer>BuyerList){
        
      boolean continueLoop=true;
      boolean UserNameExists=false;
      String Username=null;
      String accountPassword=null;
      String bankPassword=null;

      System.out.println("\n--- Signup ----");
      System.out.println("Enter a User name");
      input.nextLine();
      do{
        try{
            Username =input.nextLine();
            for(User a:MakerList){
               if(Username.equals(a.getName())){
                 UserNameExists=true;
                 break;
               }
            } 
            for(User a:BuyerList){
               if(Username.equals(a.getName())){
                 UserNameExists=true;
                 break;
               }
            } 
            if(UserNameExists==true)
              throw new WrongInput("This user name already exists, Please add some characters to it to make it unique");
            continueLoop=false;
        }
        catch(WrongInput e){
            System.out.println(e);
            UserNameExists=false;//resetting
        }
      }while(continueLoop);
    
       System.out.print("Enter email: ");
       String email = input.nextLine();

      continueLoop=true;//resetting
      boolean hasSpecialCharacter=false;
      System.out.println("Enter a Strong password for your account"); 
      System.out.println("It must have atleast 8 characters with atleast one special character");
      do{
        try{
          accountPassword=input.nextLine();
          if(accountPassword.length()<8)
            throw new WrongInput("Password must atleast be 8 characters long, enter again");
          for(int i=0;i<accountPassword.length();i++){
            if((int)accountPassword.charAt(i)<=47&&(int)accountPassword.charAt(i)>=33||(int)accountPassword.charAt(i)<=64&&(int)accountPassword.charAt(i)>=58||(int)accountPassword.charAt(i)<=96&&(int)accountPassword.charAt(i)>=91||(int)accountPassword.charAt(i)<=126&&(int)accountPassword.charAt(i)>=123){
                hasSpecialCharacter=true;
                break;
            }
           }
           if(hasSpecialCharacter==false)
             throw new WrongInput("Password must contain atleast one special character, enter again");
           continueLoop=false;
        }
        catch(WrongInput e){
            System.out.println( e);
        }
      }while(continueLoop);

      continueLoop=true;//resetting
       hasSpecialCharacter=false;//resetting
      System.out.println("You will need to create a bank account");
      System.out.println("For this purpose,Enter a strong password for your account");
      System.out.println("It must have atleast 8 characters with atleast one special character");
      do{
        try{
          bankPassword=input.nextLine();
          if( bankPassword.length()<8)
            throw new WrongInput("\nPassword must atleast be 8 characters long, enter again");
          for(int i=0;i<bankPassword.length();i++){
            if((int) bankPassword.charAt(i)<=47&&(int)bankPassword.charAt(i)>=33||(int)bankPassword.charAt(i)<=64&&(int)bankPassword.charAt(i)>=58||(int)bankPassword.charAt(i)<=96&&(int)bankPassword.charAt(i)>=91||(int)bankPassword.charAt(i)<=126&&(int)bankPassword.charAt(i)>=123){
                hasSpecialCharacter=true;
                break;
            }
           }
           if(hasSpecialCharacter==false)
             throw new WrongInput("Password must contain atleast one special character, enter again");
           continueLoop=false;
        }
        catch(WrongInput e){
            System.out.println( e);
        }
      }while(continueLoop);
    
      System.out.println("Lastly, choose your account type");
      System.out.println("Choose your user account type");
      System.out.println("1.Maker");
      System.out.println("2.Buyer");
      int AccountType=input.nextInt();
      while(AccountType!=1&&AccountType!=2){
        System.out.println("Wrong input, please enter again");
        AccountType=input.nextInt();
      }

      String userId = "U" + (MakerList.size() + BuyerList.size() + 1);
      //public User(String userId, String name, String email, String password,String bankPassword)
      if(AccountType==1){
         Maker m=new Maker(userId, Username,email,accountPassword,bankPassword);
         MakerList.add(m);
         FileHandlingforMaker.writeFile(m);
        return;
      }
      else{
         Buyer b=new Buyer(userId, Username,email,accountPassword,bankPassword);
         b.setMakerList((List)MakerList);
         BuyerList.add(b);
         FileHandlingforBuyer.writeFile(b);
        return;
      }
       
    
    }
}
class WrongInput extends Exception{
    String message;
    WrongInput( String message){
      this.message=message;
    }
    public String toString(){
       return "Error: "+message;
    }
}

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
 abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected String bankPassword;
    protected BankAccount bankAccount;


    // Constructor: called when creating a Maker or Buyer
    public User(String userId, String name, String email, String password,String bankPassword) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bankPassword=bankPassword;
        this.bankAccount=new BankAccount(bankPassword);
    }

    // Common getter methods (you can add setters if needed)
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // This is an abstract method. Maker and Buyer will implement this in their own way.
    public abstract void showMenu();
}
 class BankAccount {
    private double balance;
    private String password;

    public BankAccount(String password) {
        this.balance = 0.0;
        this.password=password;
    }

    public double getBalance() {
        return balance;
    }

    // Add money to account (e.g., after a buyer purchases an item)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited Rs. " + amount + " successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money from account (optional, may not be needed now)
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew Rs. " + amount + " successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}
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
class Review {
    private String reviewId;
    private String itemId;
    private String reviewerName;
    private String reviewerId;
    private int rating;
    private String comment;

    public Review(String reviewId,String reviewerName, String itemID, int rating, String comment,String reviewerId) {
        this.reviewId = reviewId;
        this.itemId = itemID;
        this.rating = rating;
        this.comment = comment;
        this.reviewerName=reviewerName;
        this.reviewerId=reviewerId;
    }

    public String toString() {
        return "Review by " + reviewerName + " | Rating: " + rating + "/5\nComment: " + comment;
    }
    /*writer.write(ReviewList.get(j).getReviewId()+","
                +ReviewList.get(j).getReviewerName()+","
                +ReviewList.get(j).getItemId()+","
                +ReviewList.get(j).getRating()+","
                +ReviewList.get(j).getComment()+","
                +ReviewList.get(j).getReviewerId()+"\n"); */
    String getReviewId(){
        return reviewId;
    }
    String getReviewerName(){
        return reviewerName;    
    }
    String getItemId(){
        return itemId;
    }
    int getRating(){
        return rating;
    }
    String getComment(){
        return comment;
    }
    String getReviewerId(){
        return reviewerId;
    }
}
class Buyer extends User {
    private Cart cart = new Cart();
    private List<Order> orders = new ArrayList<>();
    private List<Maker> allMakers;


    public Buyer(String userId, String name, String email, String password, String bankpassword) {
        super(userId, name, email, password,bankpassword);
    }

    public void viewAccountBalance() {
        System.out.println("Your current balance is: Rs. " + bankAccount.getBalance());
    }

    // This will be filled with product browsing logic soon
    
        public void browseProducts() {
            Scanner scanner = new Scanner(System.in);
            int choice;
        
            do {
                System.out.println("\n--- Browse Products ---");
                System.out.println("1. Browse by Maker");
                System.out.println("2. Browse by Category (coming soon)");
                System.out.println("3. Back");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
        
                if (choice == 1) {
                    System.out.println("\nAvailable Makers:");
                    for (int i = 0; i < allMakers.size(); i++) {
                        System.out.println((i + 1) + ". " + allMakers.get(i).getName());
                    }
        
                    System.out.print("Choose a maker (or 0 to cancel): ");
                    int mIndex = scanner.nextInt();
                    scanner.nextLine();
        
                    if (mIndex > 0 && mIndex <= allMakers.size()) {
                        Maker selected = allMakers.get(mIndex - 1);
                        List<Item> makerItems = selected.getItems();
        
                        for (int i = 0; i < makerItems.size(); i++) {
                            Item item = makerItems.get(i);
                            System.out.println((i + 1) + ". " + item.getTitle() + " - Rs. " + item.getPrice());
                        }
        
                        System.out.print("Select an item to (or 0 to go back): ");
                        int iIndex = scanner.nextInt();
                        scanner.nextLine();
        
                        if (iIndex > 0 && iIndex <= makerItems.size()) {
                            Item item = makerItems.get(iIndex - 1);
                            System.out.println("1. Add to Cart\n2. Buy Now\n3. Cancel");
                            int action = scanner.nextInt();
                            scanner.nextLine();
        
                            if (action == 1) cart.addItem(item);
                            else if (action == 2) placeOrder(item);
                        }
                    }
                } else if (choice == 2) {
                    System.out.println("Category browsing not implemented yet.");
                }
        
            } while (choice != 3);
            scanner.close();
        }
        
   

    @Override
    public void showMenu() {
            Scanner scanner = new Scanner(System.in);
            int choice;
        
            do {
                System.out.println("\n--- Buyer Menu ---");
                System.out.println("1. Browse Products");
                System.out.println("2. View Account Balance");
                System.out.println("3. View Cart");
                System.out.println("4. Leave a Review");
                System.out.println("5.Deposit money in bank Account");
                 System.out.println("6. Logout");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
        
                switch (choice) {
                    case 1:
                        browseProducts();
                        System.out.println("Product browsing only available with maker list.");
                        break;
                    case 2:
                        viewAccountBalance();
                        break;
                    case 3:
                        viewCart();
                        break;
                    
                    case 4:
                        leaveReview();
                        break;
                    
                    case 5:
                       System.out.println("Enter amount of money to be deposited");
                       Double amount=scanner.nextDouble();
                       bankAccount.deposit(amount);
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
    public void placeOrder(Item item) {
        double price = item.getPrice();
        Scanner scanner=new Scanner(System.in);
    
        // Check if buyer has enough balance
        if (bankAccount.getBalance() < price) {
            System.out.println("Insufficient balance to place order.");
            scanner.close();
            return;
        }
    
        // Transfer money: Buyer → Maker
        System.out.println("Please enter your bank password before proceeding");
        String password = scanner.nextLine();
        while(!password.equals(bankPassword)){
            System.out.println("Incorrect password, please enter again");
            password = scanner.nextLine();
        }
        bankAccount.withdraw(price);
         Maker m1=null;
        for(int i=0;i<allMakers.size();i++){
           if(allMakers.get(i).getUserId()==item.getMakerID())
             m1=allMakers.get(i);
        }
        m1.getBankAccount().deposit(price);
    
        // Create Order
        String orderId = "O" + (orders.size() + 1);
        Order order = new Order(orderId, this, item);
        orders.add(order);
    
        // Show Receipt
        System.out.println(order.generateReceipt());
        scanner.close();
    }
    
    public void viewCart() {
        Scanner scanner = new Scanner(System.in);
        int choice;
    
        do {
            cart.showCartItems();
    
            if (cart.isEmpty()) break;
    
            System.out.println("\nChoose an item to:");
            System.out.println("1. Buy Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
    
            if (choice == 1 || choice == 2) {
                System.out.print("Enter item number: ");
                int index = scanner.nextInt() - 1;
                scanner.nextLine();
    
                if (index >= 0 && index < cart.getItems().size()) {
                    Item item = cart.getItems().get(index);
    
                    if (choice == 1) {
                        placeOrder(item);
                        cart.getItems().remove(item);
                    } else {
                        cart.removeItem(index);
                    }
                } else {
                    System.out.println("Invalid item number.");
                }
            }
    
        } while (choice != 3);
        scanner.close();
    }
    public void leaveReview() {
        Scanner scanner = new Scanner(System.in);
    
        if (orders.isEmpty()) {
            System.out.println("You haven't placed any orders yet.");
            scanner.close();
            return;
        }
    
        System.out.println("\n--- Your Orders ---");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println((i + 1) + ". " + order.getItem().getTitle() +
                    " - Rs. " + order.getAmount() +
                    " (Maker: " + order.getItem().getMakerName() + ")");
        }
    
        System.out.print("Choose an order to review (or 0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
    
        if (choice > 0 && choice <= orders.size()) {
            Order order = orders.get(choice - 1);
            Item item = order.getItem();
    
            System.out.print("Enter rating (1 to 5): ");
            int rating = scanner.nextInt();
            scanner.nextLine();
    
            System.out.print("Enter your comment: ");
            String comment = scanner.nextLine();
    
            String reviewId = "R" + (item.getReviews().size() + 1);
            Review review = new Review(reviewId, this.name,item.getItemId(), rating, comment,this.getUserId());
            item.addReview(review);
    
            System.out.println("Thank you! Your review has been submitted.");
        }
        scanner.close();
    }
    public void setMakerList(List<Maker> makers) {
        this.allMakers = makers;
    }
    
}

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
