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
