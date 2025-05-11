
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
