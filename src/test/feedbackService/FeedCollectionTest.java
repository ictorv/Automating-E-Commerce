package feedbackService;
import feedbackModel.Feedbacks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FeedCollectionTest {
	private FeedCollection feeds;

	//initialization
	@BeforeEach
	public void setInitials() {
    	feeds = new FeedCollection();
    	feeds.addFeedbacks(new Feedbacks("user1", "great", 5));
    	feeds.addFeedbacks(new Feedbacks("user2", "okay", 3));
    	feeds.addFeedbacks(new Feedbacks("user1", "not bad", 4));
	}

	//add and retrieve
	@Test
	public void testAddRetrieveFeedback() {
    	List<Feedbacks> user1Feedback = feeds.feedbacksByUser("user1");
    	assertEquals(2, user1Feedback.size());

    	List<Feedbacks> user2Feedback = feeds.feedbacksByUser("user2");
    	assertEquals(1, user2Feedback.size());

    	List<Feedbacks> unknownUser = feeds.feedbacksByUser("unknown");
    	assertTrue(unknownUser.isEmpty());
	}

	//average rating
	@Test
	public void testAverageRating() {
    	double average = feeds.averageRating();
    	assertEquals(4.0, average, 0.01);
	}

	//sorted feedback
	@Test
	public void testSortedFeedback() {
    	List<Feedbacks> sorted = feeds.sortedFeedback();
    	for (int i = 0; i < sorted.size() - 1; i++) {
        	assertTrue(sorted.get(i).getSubmitTime()
                	.isBefore(sorted.get(i + 1).getSubmitTime()) || sorted.get(i).getSubmitTime()
                	.isEqual(sorted.get(i + 1).getSubmitTime()));
    	}
	}

	//feedback list
	@Test
	public void testAllFeedback() {
    	List<Feedbacks> all = feeds.allFeedback();
    	assertEquals(3, all.size());
	}

	//below rating
	@Test
	public void testRatingTooLow() {
		Exception ex = assertThrows(IllegalArgumentException.class, () -> new Feedbacks("user", "bad", 0));
		assertEquals("Provide rating between 1 to 5", ex.getMessage());
	}

	//above rating
	@Test
	public void testRatingTooHigh() {
		Exception ex = assertThrows(IllegalArgumentException.class, () -> new Feedbacks("user", "bad", 6));
		assertEquals("Provide rating between 1 to 5", ex.getMessage());
	}

	//output format
	@Test
	public void testFeedbackObjectGettersAndToString() {
    	Feedbacks feed = new Feedbacks("testUser", "testComment", 4);
    	assertEquals("testUser", feed.getUser());
    	assertEquals("testComment", feed.getComment());
    	assertEquals(4, feed.getRate());
    	assertNotNull(feed.getSubmitTime());
    	assertTrue(feed.toString().contains("testUser"));
    	assertTrue(feed.toString().contains("Rating:4"));
	}
}
