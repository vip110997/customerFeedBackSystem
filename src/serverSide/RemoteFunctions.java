package serverSide;

import java.rmi.Remote;
import java.util.Vector;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public interface RemoteFunctions extends Remote {

	//Completed
	// Authenticates username and password in admin login
	public boolean adminLogin(String userName, String password) throws Exception;

	//Completed
	// Returns the customer Feedback Questions
	public Vector<String> getQuestions() throws Exception;

	// Stores the email address in the database to send promotion details
	public void sendEmail(String recipientEmail, String message) throws Exception;

	//Completed
	// Stores the user Feedback in the database
	public void userFeedback(int feedbackID, int taste, int valueForMoney, int temperature, int cleanliness,
			int lighting, int comfort, int music, int speedOfService, int qualityOfService, int knowledgeOfServer,
			String suggestion, String diningfrequency, String recommendFrequency, String restaurantPromotedWay) throws Exception;

	
	// Get all customer Suggestions
	public Vector<String> getSuggestions() throws Exception;

	// Returns the average of ratings for each aspect ex: Cleanliness, Ambience
	public int getAverage(String aspect, String value) throws Exception;

	// Returns value for frequency of Recommendation
	public int getRecommendationFrequency(String recommendSelect) throws Exception;

	// Returns value for frequency of visiting the restaurant again
	public int getVisitingFrequencyNumber(String visitSelect) throws Exception;

	// Returns value for way the restaurant got promoted to the customer
	public int getPromotedWay(String promotedSelect) throws Exception;

	public void PDFCreator(JFreeChart barChart, int width, int height, String filename) throws Exception;

	public void PDFcreator(String string, String filename) throws Exception;


	//Create PDF from JFreeCharts

	

	
}
