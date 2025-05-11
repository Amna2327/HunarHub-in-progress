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
