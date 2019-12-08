package serverSide;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.jfree.chart.JFreeChart;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class FunctionsImplementation extends UnicastRemoteObject implements RemoteFunctions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DatabaseConnection dbCon = new DatabaseConnection();

	protected FunctionsImplementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean adminLogin(String userName, String password) {
		if (userName.trim().equals("vishwa") && password.trim().equals("vishwa123")
				|| userName.trim().equals("tharsikan") && password.trim().equals("tharsikan123")
				|| userName.trim().equals("sajid") && password.trim().equals("sajid123")) {
			System.out.println("Welcome Admin");
			return true;
		} else {
			System.out.println("Access Denied! Enter a valid username and password");
			return false;
		}
	}

	@Override
	public Vector<String> getQuestions() {
		// TODO Auto-generated method stub
		Vector<String> questionSet = new Vector<String>();
		questionSet.add("How would you rate our Burgers?");
		questionSet.add("Liked the ambience in our restaurant? Rate it");
		questionSet.add("What do you think about the customer service of our restaurant");
		questionSet.add("How often do you dine with us?");
		questionSet.add("How likely is it for you recommend us to a friend?");
		questionSet.add("How did you find about the restaurant?");
		questionSet.add("Anything you would like to add?");
		return questionSet;
	}

	@Override
	public void sendEmail(String recipientEmail, String userMessage) {
		// TODO Auto-generated method stub
		System.out.println("Sending email...");
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		String myEmailAddress = "vishwa.vip.punchihewa@gmail.com";
		String myPassword = "Oshinipunchihewa1!";

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmailAddress, myPassword);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmailAddress));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject("This is an email");
			message.setText(userMessage);
			Transport.send(message);
			System.out.println("Message sent successfully");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void PDFCreator(JFreeChart barChart, int width, int height, String filename) {
		Document barChartDoc = new Document();
		PdfWriter creator = null;	
		try {
			OutputStream chartFile = new FileOutputStream(new File("D:\\chartReports\\"+filename));
			
			creator = PdfWriter.getInstance(barChartDoc,chartFile);
			barChartDoc.open();
			PdfContentByte pdfconByte = creator.getDirectContent();
			PdfTemplate template = pdfconByte.createTemplate(width, height);
			@SuppressWarnings("deprecation")
			Graphics2D graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
					height);

			barChart.draw(graphics2d, rectangle2d);
			
			graphics2d.dispose();
			pdfconByte.addTemplate(template, 0, 0);
			barChartDoc.close();
			creator.close();
			System.out.println("pdf functioning");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void PDFcreator(String string, String filename) {
		// TODO Auto-generated method stub
		
		Document document= new Document();
		try {
			PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
			document.add(new Paragraph(string));
			document.close();
			writer.close();
		}
		catch(DocumentException e) {
			e.printStackTrace();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void userFeedback(int feedbackID, int taste, int valueForMoney, int temperature, int cleanliness,
			int lighting, int comfort, int music, int speedOfService, int qualityOfService, int knowledgeOfServer,
			String suggestion, String diningfrequency, String recommendFrequency, String restaurantPromotedWay) {
		// TODO Auto-generated method stub

		Connection con = dbCon.connectToDatabase();

		String query = "INSERT INTO burgerjoint(suggestion, diningFrequency, recommendFrequency, promotedWay,ratings)VALUES('"
				+ suggestion + "', '" + diningfrequency + "', '" + recommendFrequency + "','" + restaurantPromotedWay
				+ "', '{\"feedbackID\": " + feedbackID + ", \"Food\": {\"Taste\": " + taste + ", \"ValueForMoney\": "
				+ valueForMoney + ", \"Temperature\": " + temperature + ",\"Cleanliness\":" + cleanliness
				+ "}, \"Ambiance\": {\"Lighting\": " + lighting + ", \"Comfort\": " + comfort + ", \"Music\": " + music
				+ "}, \"CustomerService\": {\"SpeedOfService\": " + speedOfService + "," + " \"QualityOfService\": "
				+ qualityOfService + ", \"KnowledgeOfServer\": " + knowledgeOfServer + "}}')";

		try {
			Statement statement;
			statement = (Statement) con.createStatement();
			((java.sql.Statement) statement).executeUpdate(query);
			statement.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Data was not inserted to the database");
		}

	}

	@Override
	public Vector<String> getSuggestions() {
		// TODO Auto-generated method stub
		Vector<String> suggestions = new Vector<String>();
		Connection con = dbCon.connectToDatabase();
		String query = "select suggestion from burgerjoint";
		String suggestion;

		try {
			Statement statement;
			statement = (Statement) con.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			while (resultset.next()) {
				suggestion = resultset.getString("suggestion");
				suggestions.add(suggestion);
			}

			statement.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return suggestions;
	}

	@Override
	public int getAverage(String aspect, String value) {
		// TODO Auto-generated method stub
		Connection con = dbCon.connectToDatabase();
		String query = "select AVG(ratings->>\"$." + aspect + "." + value + "\") Avg from burgerjoint;";
		int average = 0;

		try {
			Statement statement;
			statement = (Statement) con.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			while (resultset.next()) {
				average = resultset.getInt("Avg");
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return average;
	}

	@Override
	public int getVisitingFrequencyNumber(String visitSelect) {
		// TODO Auto-generated method stub
		int counter = 0;
		Connection con = dbCon.connectToDatabase();
		String query = "select COUNT(diningFrequency) recsel from burgerjoint where diningFrequency = '" + visitSelect
				+ "'";

		try {
			Statement statement;
			statement = (Statement) con.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			if (resultset.next()) {
				counter = resultset.getInt("recsel");
			}

			statement.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return counter;
	}

	@Override
	public int getRecommendationFrequency(String recommendSelect) {
		// TODO Auto-generated method stub
		int counter = 0;
		Connection con = dbCon.connectToDatabase();
		String query = "select COUNT(recommendFrequency) recsel from burgerjoint where recommendFrequency = '"
				+ recommendSelect + "'";

		try {
			Statement statement;
			statement = (Statement) con.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			if (resultset.next()) {
				counter = resultset.getInt("recsel");
			}

			statement.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return counter;
	}

	@Override
	public int getPromotedWay(String promotedSelect) {
		// TODO Auto-generated method stub
		int counter = 0;
		Connection con = dbCon.connectToDatabase();
		String query = "select COUNT(promotedWay) recsel from burgerjoint where promotedWay = '" + promotedSelect + "'";

		try {
			Statement statement;
			statement = (Statement) con.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			if (resultset.next()) {
				counter = resultset.getInt("recsel");
			}

			statement.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return counter;
	}

}
