package feedbackModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Feedbacks {
	private String user;
	private String comment;
	private int rate;
	private LocalDateTime submitTime;
  
	public Feedbacks(String user, String comment, int rating) {
		  // validate rating
	      if (rating < 1 || rating > 5) {
	          throw new IllegalArgumentException("Provide rating between 1 to 5");
	      }
	      this.user = user;
	      this.comment = comment;
	      this.rate= rating;
	      this.submitTime = LocalDateTime.now();
	  }

	  public String getUser() {
	      return user;
	  }

	  public String getComment() {
	      return comment;
	  }

	  public int getRate() {
	      return rate;
	  }

	  public LocalDateTime getSubmitTime() {
	      return submitTime;
	  }

	  @Override
	  public String toString() {
		  DateTimeFormatter dateformat=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	      return user + " - (Rating:" + rate + " | Comment:" + comment + " | Submitted at:" + submitTime.format(dateformat)+")";
	  }
	
}