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
