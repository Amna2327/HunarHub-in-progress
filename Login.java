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
