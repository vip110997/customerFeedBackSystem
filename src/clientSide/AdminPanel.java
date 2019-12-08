package clientSide;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import serverSide.RemoteFunctions;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class AdminPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static String serviceName = "rmi://0.0.0.0:2000/connect";
	int taste = 0;
	int value = 0;
	int temperature = 0;
	int cleanliness = 0;
	int lighting = 0;
	int comfort = 0;
	int music = 0;
	int speed = 0;
	int quality = 0;
	int knowledge = 0;

//	Suggestions
	Vector<String> suggestions = null;

//	Multiple Choice
	int weekly = 0;
	int monthly = 0;
	int daily = 0;
	int firstTime = 0;

	int likely = 0;
	int highlyLikely = 0;
	int unlikely = 0;
	int somewhatLikely = 0;

	int newspaper = 0;
	int Website = 0;
	int SocialMedia = 0;
	int Friend = 0;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public AdminPanel() {

//		Ratings

		try {
			RemoteFunctions service = (RemoteFunctions) Naming.lookup(serviceName);
			service = (RemoteFunctions) Naming.lookup(serviceName);

			taste = service.getAverage("Food", "Taste");
			value = service.getAverage("Food", "ValueForMoney");
			;
			temperature = service.getAverage("Food", "Temperature");
			cleanliness = service.getAverage("Food", "Cleanliness");
			lighting = service.getAverage("Ambiance", "Lighting");
			comfort = service.getAverage("Ambiance", "Comfort");
			music = service.getAverage("Ambiance", "Music");
			speed = service.getAverage("CustomerService", "SpeedOfService");
			quality = service.getAverage("CustomerService", "QualityOfService");
			knowledge = service.getAverage("CustomerService", "KnowledgeOfServer");

			suggestions = new Vector<String>(service.getSuggestions());

			weekly = service.getVisitingFrequencyNumber("Weekly");
			monthly = service.getVisitingFrequencyNumber("Monthly");
			daily = service.getVisitingFrequencyNumber("Daily");
			firstTime = service.getVisitingFrequencyNumber("First Time");
//			System.out.println(weekly);

			likely = service.getRecommendationFrequency("Likely");
			highlyLikely = service.getRecommendationFrequency("Highly Likely");
			unlikely = service.getRecommendationFrequency("Unlikely");
			somewhatLikely = service.getRecommendationFrequency("Somewhat Likely");
//			System.out.println(likely);

			newspaper = service.getPromotedWay("Newspaper");
			Website = service.getPromotedWay("Website");
			SocialMedia = service.getPromotedWay("Social Media");
			Friend = service.getPromotedWay("Friend");
//			System.out.println(newspaper);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 820);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBackground(new Color(56, 132, 242));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Show Ratings for Food");
		btnNewButton.setBounds(48, 41, 600, 57);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset foodDataSet = new DefaultCategoryDataset();
				foodDataSet.setValue(taste, "Rating", "Taste");
				foodDataSet.setValue(value, "Rating", "Value For Money");
				foodDataSet.setValue(temperature, "Rating", "Temperature");
				foodDataSet.setValue(cleanliness, "Rating", "Cleanliness");

				JFreeChart jchart = ChartFactory.createBarChart("Food", "Aspect", "Average Rating", foodDataSet,
						PlotOrientation.VERTICAL, true, true, false);
				jchart.getPlot().setBackgroundPaint(new Color(255, 255, 255));

				CategoryPlot plot = jchart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				BarRenderer renderer = (BarRenderer) plot.getRenderer();

				Color color = new Color(56, 132, 242);
				renderer.setSeriesPaint(0, color);

				ChartFrame cf = new ChartFrame("Food Ratings", jchart, true);
				cf.setVisible(true);
				cf.setSize(1000, 800);
				
				try {
					RemoteFunctions service;
					service = (RemoteFunctions) Naming.lookup(serviceName);
					service.PDFCreator(jchart, 600, 800, "foodChart.pdf");
					System.out.println("WORKING");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		contentPane.setLayout(null);
		btnNewButton.setBackground(new Color(149, 189, 250));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(true);
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Roboto", Font.BOLD, 17));
		contentPane.add(btnNewButton);

		JButton btnShowRatingsFor = new JButton("Show ratings for Ambience");
		btnShowRatingsFor.setBounds(48, 109, 600, 57);
		btnShowRatingsFor.setForeground(Color.WHITE);
		btnShowRatingsFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset ambienceDataSet = new DefaultCategoryDataset();
				ambienceDataSet.setValue(lighting, "Rating", "Lighting");
				ambienceDataSet.setValue(comfort, "Rating", "Comfort");
				ambienceDataSet.setValue(music, "Rating", "Music");

				JFreeChart jchart = ChartFactory.createBarChart("Ambience", "Aspect", "Average Rating", ambienceDataSet,
						PlotOrientation.VERTICAL, true, true, false);
				jchart.getPlot().setBackgroundPaint(new Color(255, 255, 255));

				CategoryPlot plot = jchart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				BarRenderer renderer = (BarRenderer) plot.getRenderer();

				Color color = new Color(56, 132, 242);
				renderer.setSeriesPaint(0, color);
				ChartFrame cf = new ChartFrame("Food Ratings", jchart, true);
				cf.setVisible(true);
				cf.setSize(1000, 800);
				
				try {
					RemoteFunctions service;
					service = (RemoteFunctions) Naming.lookup(serviceName);
					service.PDFCreator(jchart, 600, 800, "ambienceChart.pdf");
					System.out.println("WORKING");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowRatingsFor.setBackground(new Color(149, 189, 250));
		btnShowRatingsFor.setContentAreaFilled(false);
		btnShowRatingsFor.setOpaque(true);
		btnShowRatingsFor.setBorder(null);
		btnShowRatingsFor.setFont(new Font("Roboto", Font.BOLD, 17));
		contentPane.add(btnShowRatingsFor);

		JButton btnShowRatingsFor_1 = new JButton("Show Ratings for Customer Service");
		btnShowRatingsFor_1.setBounds(48, 177, 600, 57);
		btnShowRatingsFor_1.setForeground(Color.WHITE);
		btnShowRatingsFor_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset serviceDataSet = new DefaultCategoryDataset();
				serviceDataSet.setValue(speed, "Rating", "Speed Of Service");
				serviceDataSet.setValue(quality, "Rating", "Quality Of Service");
				serviceDataSet.setValue(knowledge, "Rating", "Knowledge Of Server");

				JFreeChart jchart = ChartFactory.createBarChart("Customer Service", "Aspect", "Average Rating", serviceDataSet,
						PlotOrientation.VERTICAL, true, true, false);
				jchart.getPlot().setBackgroundPaint(new Color(255, 255, 255));

				CategoryPlot plot = jchart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				BarRenderer renderer = (BarRenderer) plot.getRenderer();

				Color color = new Color(56, 132, 242);
				renderer.setSeriesPaint(0, color);

				ChartFrame cf = new ChartFrame("Food Ratings", jchart, true);
				cf.setVisible(true);
				cf.setSize(1000, 800);

				try {
					RemoteFunctions service;
					service = (RemoteFunctions) Naming.lookup(serviceName);
					service.PDFCreator(jchart, 600, 800, "customerServiceChart.pdf");
					System.out.println("WORKING");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowRatingsFor_1.setBackground(new Color(149, 189, 250));
		btnShowRatingsFor_1.setContentAreaFilled(false);
		btnShowRatingsFor_1.setOpaque(true);
		btnShowRatingsFor_1.setBorder(null);
		btnShowRatingsFor_1.setFont(new Font("Roboto", Font.BOLD, 17));
		contentPane.add(btnShowRatingsFor_1);

		JButton btnHowOftenDo = new JButton("How often do you dine with us?");
		btnHowOftenDo.setBounds(48, 250, 600, 57);
		btnHowOftenDo.setForeground(Color.WHITE);
		btnHowOftenDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset diningFrequencyDataSet = new DefaultCategoryDataset();
				diningFrequencyDataSet.setValue(weekly, "Rating", "Weekly");
				diningFrequencyDataSet.setValue(monthly, "Rating", "Monthly");
				diningFrequencyDataSet.setValue(daily, "Rating", "Daily");
				diningFrequencyDataSet.setValue(firstTime, "Rating", "First Time");

				JFreeChart jchart1 = ChartFactory.createBarChart("Dining Frequency", "Aspect", "Average Rating",
						diningFrequencyDataSet, PlotOrientation.VERTICAL, true, true, false);
				jchart1.getPlot().setBackgroundPaint(new Color(255, 255, 255));

				CategoryPlot plot = jchart1.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				BarRenderer renderer = (BarRenderer) plot.getRenderer();

				Color color = new Color(56, 132, 242);
				renderer.setSeriesPaint(0, color);

				ChartFrame cf = new ChartFrame("Food Ratings", jchart1, true);
				cf.setVisible(true);
				cf.setSize(1000, 800);

				try {
					RemoteFunctions service;
					service = (RemoteFunctions) Naming.lookup(serviceName);
					service.PDFCreator(jchart1, 600, 800, "diningFrequency.pdf");
					System.out.println("WORKING");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnHowOftenDo.setBackground(new Color(149, 189, 250));
		btnHowOftenDo.setContentAreaFilled(false);
		btnHowOftenDo.setOpaque(true);
		btnHowOftenDo.setBorder(null);
		btnHowOftenDo.setFont(new Font("Roboto", Font.BOLD, 17));
		contentPane.add(btnHowOftenDo);

		JButton btnHowLikelyIs = new JButton("How likely is it for you to recommend us to a friend?");
		btnHowLikelyIs.setBounds(48, 318, 600, 57);
		btnHowLikelyIs.setForeground(Color.WHITE);
		btnHowLikelyIs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset recommendFrequencyDataSet = new DefaultCategoryDataset();
				recommendFrequencyDataSet.setValue(likely, "Rating", "Likely");
				recommendFrequencyDataSet.setValue(highlyLikely, "Rating", "Highly Likely");
				recommendFrequencyDataSet.setValue(unlikely, "Rating", "Unlikely");
				recommendFrequencyDataSet.setValue(somewhatLikely, "Rating", "Somewhat likely");

				JFreeChart jchart = ChartFactory.createBarChart("Recommend Frequency", "Aspect", "Average Rating",
						recommendFrequencyDataSet, PlotOrientation.VERTICAL, true, true, false);
				jchart.getPlot().setBackgroundPaint(new Color(255, 255, 255));

				CategoryPlot plot = jchart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				BarRenderer renderer = (BarRenderer) plot.getRenderer();

				Color color = new Color(56, 132, 242);
				renderer.setSeriesPaint(0, color);
				ChartFrame cf = new ChartFrame("Food Ratings", jchart, true);
				cf.setVisible(true);
				cf.setSize(1000, 800);
				
				try {
					RemoteFunctions service;
					service = (RemoteFunctions) Naming.lookup(serviceName);
					service.PDFCreator(jchart, 600, 800, "recommendFrequency.pdf");
					System.out.println("WORKING");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnHowLikelyIs.setBackground(new Color(149, 189, 250));
		btnHowLikelyIs.setContentAreaFilled(false);
		btnHowLikelyIs.setOpaque(true);
		btnHowLikelyIs.setBorder(null);
		btnHowLikelyIs.setFont(new Font("Roboto", Font.BOLD, 17));
		contentPane.add(btnHowLikelyIs);

		JButton btnHowDidYou = new JButton("How did you find out about the restaurant?");
		btnHowDidYou.setBounds(48, 386, 600, 57);
		btnHowDidYou.setForeground(Color.WHITE);
		btnHowDidYou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset promotedWayDataSet = new DefaultCategoryDataset();
				promotedWayDataSet.setValue(newspaper, "Rating", "Newspaper");
				promotedWayDataSet.setValue(Website, "Rating", "Website");
				promotedWayDataSet.setValue(SocialMedia, "Rating", "Social Media");
				promotedWayDataSet.setValue(Friend, "Rating", "Friend");

				JFreeChart jchart = ChartFactory.createBarChart("Promoted Ways", "Aspect", "Average Rating", promotedWayDataSet,
						PlotOrientation.VERTICAL, true, true, false);
				jchart.getPlot().setBackgroundPaint(new Color(255, 255, 255));

				CategoryPlot plot = jchart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				BarRenderer renderer = (BarRenderer) plot.getRenderer();

				Color color = new Color(56, 132, 242);
				renderer.setSeriesPaint(0, color);

				ChartFrame cf = new ChartFrame("Food Ratings", jchart, true);

				cf.setVisible(true);
				cf.setSize(1000, 800);
				
				try {
					RemoteFunctions service;
					service = (RemoteFunctions) Naming.lookup(serviceName);
					service.PDFCreator(jchart, 600, 800, "promotedWay.pdf");
					System.out.println("WORKING");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnHowDidYou.setBackground(new Color(149, 189, 250));
		btnHowDidYou.setContentAreaFilled(false);
		btnHowDidYou.setOpaque(true);
		btnHowDidYou.setBorder(null);
		btnHowDidYou.setFont(new Font("Roboto", Font.BOLD, 17));
		contentPane.add(btnHowDidYou);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 0, 418, 90);
		textArea_1.setFont(new Font("Roboto", Font.BOLD, 15));
		contentPane.add(textArea_1);

		JScrollPane suggestionsBox = new JScrollPane(textArea_1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		suggestionsBox.setBounds(48, 470, 600, 241);
		contentPane.add(suggestionsBox);

		String tasteString = Integer.toString(taste);
		String valueString = Integer.toString(value);
		String temperatureString = Integer.toString(temperature);
		String cleanlinessString = Integer.toString(cleanliness);
		String lightingString = Integer.toString(lighting);
		String comfortString = Integer.toString(comfort);
		String musicString = Integer.toString(music);
		String speedString = Integer.toString(speed);
		String qualityString = Integer.toString(quality);
		String knowledgeString = Integer.toString(knowledge);

		String weeklyString = Integer.toString(weekly);
		String monthlyString = Integer.toString(monthly);
		String dailyString = Integer.toString(daily);
		String firstTimeString = Integer.toString(firstTime);

		String likelyString = Integer.toString(likely);
		String highlyLikelyString = Integer.toString(highlyLikely);
		String unlikelyString = Integer.toString(unlikely);
		String somewhatLikelyString = Integer.toString(somewhatLikely);

		String newspaperString = Integer.toString(newspaper);
		String websiteString = Integer.toString(Website);
		String socialMediaString = Integer.toString(SocialMedia);
		String FriendString = Integer.toString(Friend);

		String feedbackMessage = "BURGER RESTAURANT REPORT \t\t\n\n Here is the report you requested. The following are average ratings your restuarant recieved for different aspects \n\n"
				+ "Taste : " + tasteString + "/5\n" + "valueString : " + valueString + "/5\n" + "Temperature : "
				+ temperatureString + "/5\n" + "cleanliness : " + cleanlinessString + "/5\n\n" + "Lighting : "
				+ lightingString + "/5\n" + "Comfort : " + comfortString + "/5\n" + "Music : " + musicString + "/5\n\n"
				+ "speed Of Service: " + speedString + "/5\n" + "Quality Of Service : " + qualityString + "/5\n"
				+ "Knowledge Of Server : " + knowledgeString + "/5\n\n" + "Number of People who dine with us : \n\n"
				+ "Weekly : " + weeklyString + "\n" + "Monthly : " + monthlyString + "\n" + "Daily : " + dailyString
				+ "\n" + "First Time : " + firstTimeString + "\n\n"
				+ "How likely is it for you to recommend us to a friend? : \n\n" + "Likely : " + likelyString + "\n"
				+ "highly Likely : " + highlyLikelyString + "\n" + "unlikely : " + unlikelyString + "\n"
				+ "somewhat Likely : " + somewhatLikelyString + "\n\n" + "How did you hear about us? : \n\n"
				+ "Newspaper : " + newspaperString + "\n" + "Website : " + websiteString + "\n" + "Friend : "
				+ FriendString + "\n" + "Social Media : " + socialMediaString + "\n\n";

		JButton emailButton = new JButton("Send Email");
		emailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					RemoteFunctions service;
					service = (RemoteFunctions) Naming.lookup(serviceName);
					service.sendEmail("vishwa.vip.punchihewa@gmail.com", feedbackMessage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		emailButton.setForeground(Color.WHITE);
		emailButton.setBounds(48, 722, 299, 48);
		emailButton.setBackground(new Color(149, 189, 250));
		emailButton.setContentAreaFilled(false);
		emailButton.setOpaque(true);
		emailButton.setBorder(null);
		emailButton.setFont(new Font("Roboto", Font.BOLD, 17));
		emailButton.setBounds(369, 722, 279, 48);

		contentPane.add(emailButton);

		JButton button = new JButton("Create PDF");
		emailButton.setForeground(Color.WHITE);
		emailButton.setBounds(48, 722, 299, 48);
		emailButton.setBackground(new Color(149, 189, 250));
		emailButton.setContentAreaFilled(false);
		emailButton.setOpaque(true);
		emailButton.setBorder(null);
		emailButton.setFont(new Font("Roboto", Font.BOLD, 17));
		contentPane.add(button);

		JButton createPdfButton = new JButton("Create PDF");
		createPdfButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				try {
					RemoteFunctions service;
					service = (RemoteFunctions) Naming.lookup(serviceName);
					service.PDFcreator(feedbackMessage, "ligma.pdf");
					System.out.println("PDF Created Successfully");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		createPdfButton.setForeground(Color.WHITE);
		createPdfButton.setBackground(new Color(149, 189, 250));
		createPdfButton.setContentAreaFilled(false);
		createPdfButton.setOpaque(true);
		createPdfButton.setBorder(null);
		createPdfButton.setFont(new Font("Roboto", Font.BOLD, 17));
		createPdfButton.setBounds(357, 722, 299, 48);
		contentPane.add(createPdfButton);

//		JTextArea areaFeedback = new JTextArea();
//		areaFeedback.setPreferredSize(new Dimension(500,500));
//		feedbackScrollPane.setViewportView(areaFeedback);
//		areaFeedback.setEditable(false);

		for (int s = 0; s < suggestions.size(); s++) {

			textArea_1.setText(textArea_1.getText() + "\n \n  --  " + suggestions.get(s));
		}

	}
}
