package app;

import feedbackModel.Feedbacks;
import feedbackService.FeedCollection;
import java.util.Scanner;

public class Main {
  
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		FeedCollection feeds = new FeedCollection();

	    System.out.print("Number of feeds:");
	    int n = sc.nextInt();
	    sc.nextLine();
	    
	    for (int i = 0; i < n; i++) {
	    	System.out.print("\nuser:");
	        String user= sc.nextLine();

	        System.out.print("comment:");
	        String comment = sc.nextLine();

	        int rate= 0;
	        while (true) {
	        	System.out.print("rating (1 to 5):");
	            try {
	            	rate= sc.nextInt(); 
	    	        sc.nextLine();
	                if (rate< 1 || rate> 5) {
	                	System.out.println("invalid rating retry!");
	                } 
	                else {
	                	break;
	                }
	            }catch (NumberFormatException e) {
	            	System.out.println("Enter a valid integer rating");
	            }
	        }
	        try {
	       		feeds.addFeedbacks(new Feedbacks(user, comment, rate));
	        } 
	        catch (IllegalArgumentException e) {
	            System.out.println("failed to add feedback: " + e.getMessage());
	        }
	      }
  
	      System.out.println("\nfeedbacks:");
	      feeds.sortedFeedback().forEach(System.out::println);

	      System.out.println("\naverage rating: " + feeds.averageRating());
	}
}