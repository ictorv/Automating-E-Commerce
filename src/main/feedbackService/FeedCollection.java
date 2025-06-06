package feedbackService;
import feedbackModel.Feedbacks;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FeedCollection {
	  private final List<Feedbacks> feedList;

	  public FeedCollection() {
	      this.feedList = new ArrayList<>();
	  }
	  
	  //store
	  public void addFeedbacks(Feedbacks feed) {
	      feedList.add(feed);
	  }

	  //retrieve
	  public List<Feedbacks> feedbacksByUser(String user) {
	      return feedList.stream()
	              		 .filter(f -> f.getUser().equalsIgnoreCase(user))
	              		 .collect(Collectors.toList());
	  }

	  //average rating
	  public double averageRating() {
	      return feedList.stream()
	              		 .mapToInt(Feedbacks::getRate)
	              		 .average()
	              		 .orElse(0.0);
	  }

	  //sorting feedbacks
	  public List<Feedbacks> sortedFeedback() {
	      return feedList.stream()
	    		  		 .sorted(Comparator.comparing(Feedbacks::getSubmitTime))
	              		 .collect(Collectors.toList());
	  }

	  //securing 
	  public List<Feedbacks> allFeedback() {
	      return new ArrayList<>(feedList);
	  }
}