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
