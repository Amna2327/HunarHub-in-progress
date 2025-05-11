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
