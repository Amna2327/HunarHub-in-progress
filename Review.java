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
