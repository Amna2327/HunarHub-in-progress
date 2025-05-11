import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
class Main{
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
