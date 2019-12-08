package clientSide;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


import serverSide.RemoteFunctions;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractButton;



public class Questionnaire extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8835932636071951701L;
	private JPanel contentPane;
	private JTextField suggestionTxt;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Questionnaire survey = new Questionnaire();
					survey.setExtendedState(JFrame.MAXIMIZED_BOTH);
					survey.setVisible(true);
					survey.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
	
	/**
	 * Create the frame.
	 */
	public Questionnaire() {
		
		
		
		LandingScreen ls=new LandingScreen();
		
		String questionFood = "";
		String questionAmbience = "";
		String questionCustomerService = "";
		String questionDiningFrequency = "";
		String questionRecommendFrequency= "";
		String questionPromotedWay = "";
		String questionSuggestion = "";
		
		try {
			String serviceName="rmi://0.0.0.0:2000/connect";
			RemoteFunctions service = (RemoteFunctions) Naming.lookup(serviceName);
			service = (RemoteFunctions) Naming.lookup(serviceName);

			Vector<String> que = new Vector<String>(service.getQuestions());
			questionFood = que.get(0).toString();
			questionAmbience = que.get(1).toString();
			questionCustomerService = que.get(2).toString();
			questionDiningFrequency = que.get(3).toString();
			questionRecommendFrequency = que.get(4).toString();
			questionPromotedWay = que.get(5).toString();
			questionSuggestion = que.get(6).toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1829, 1190);
		contentPane = new JPanel();
		contentPane.setLocation(-175, -691);
		contentPane.setBackground(new Color(56, 132, 242));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel foodQuestion = new JLabel("");
		foodQuestion.setText(questionFood);
		foodQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		foodQuestion.setForeground(Color.WHITE);
		foodQuestion.setFont(new Font("Roboto", Font.BOLD, 15));
		foodQuestion.setBounds(259, 192, 675, 35);
		contentPane.add(foodQuestion);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setMinimumSize(new Dimension(5, 0));
		separator_1.setAlignmentY(0.0f);
		separator_1.setAlignmentX(1.0f);
		separator_1.setBounds(259, 482, 676, 25);
		contentPane.add(separator_1);
		
		
		
		JLabel lblAdminLogin = new JLabel("Rate the following aspects of the restaurant on a scale of 1 to 10");
		lblAdminLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLogin.setForeground(Color.WHITE);
		lblAdminLogin.setFont(new Font("Roboto", Font.BOLD, 16));
		lblAdminLogin.setBounds(574, 117, 700, 35);
		contentPane.add(lblAdminLogin);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(264, 713, 670, 14);
		contentPane.add(separator_3);
		
		JLabel lblEnterTheUsername = new JLabel("5-Excellent  4-Very Good  3-Average  2-Below Average  1-Poor");
		lblEnterTheUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterTheUsername.setForeground(Color.WHITE);
		lblEnterTheUsername.setFont(new Font("Roboto", Font.BOLD, 12));
		lblEnterTheUsername.setBounds(739, 142, 398, 14);
		contentPane.add(lblEnterTheUsername);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Questionnaire.class.getResource("/Images/logo.png")));
		lblNewLabel_1.setBounds(845, 11, 158, 109);
		contentPane.add(lblNewLabel_1);
		
		JSlider tasteSlider = new JSlider();
		tasteSlider.setMaximum(5);
		tasteSlider.setPaintLabels(true);
		tasteSlider.setMajorTickSpacing(1);
		tasteSlider.setBounds(374, 238, 560, 35);
		contentPane.add(tasteSlider);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(269, 225, 675, 2);
		contentPane.add(separator_2);
		
		JLabel lblNewLabel_2 = new JLabel("Taste");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Roboto", Font.BOLD, 11));
		lblNewLabel_2.setBounds(269, 238, 127, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblValueForMoney = new JLabel("Value for Money");
		lblValueForMoney.setForeground(Color.WHITE);
		lblValueForMoney.setFont(new Font("Roboto", Font.BOLD, 11));
		lblValueForMoney.setBounds(269, 284, 127, 25);
		contentPane.add(lblValueForMoney);
		
		JSlider valueSlider = new JSlider();
		valueSlider.setPaintLabels(true);
		valueSlider.setMaximum(5);
		valueSlider.setMajorTickSpacing(1);
		valueSlider.setBounds(374, 284, 560, 35);
		contentPane.add(valueSlider);
		
		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setForeground(Color.WHITE);
		lblTemperature.setFont(new Font("Roboto", Font.BOLD, 11));
		lblTemperature.setBounds(269, 324, 127, 25);
		contentPane.add(lblTemperature);
		
		JSlider temperatureSlider = new JSlider();
		temperatureSlider.setPaintLabels(true);
		temperatureSlider.setMaximum(5);
		temperatureSlider.setMajorTickSpacing(1);
		temperatureSlider.setBounds(374, 330, 560, 35);
		contentPane.add(temperatureSlider);
		
		JLabel ambienceQuestion = new JLabel("");
		ambienceQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		ambienceQuestion.setForeground(Color.WHITE);
		ambienceQuestion.setFont(new Font("Roboto", Font.BOLD, 15));
		ambienceQuestion.setBounds(259, 448, 675, 35);
		ambienceQuestion.setText(questionAmbience);
		contentPane.add(ambienceQuestion);
		
		JLabel lblCleanliness = new JLabel("Cleanliness");
		lblCleanliness.setForeground(Color.WHITE);
		lblCleanliness.setFont(new Font("Roboto", Font.BOLD, 11));
		lblCleanliness.setBounds(265, 376, 127, 25);
		contentPane.add(lblCleanliness);
		
		JSlider cleanlinessSlider = new JSlider();
		cleanlinessSlider.setPaintLabels(true);
		cleanlinessSlider.setMaximum(5);
		cleanlinessSlider.setMajorTickSpacing(1);
		cleanlinessSlider.setBounds(374, 376, 560, 35);
		contentPane.add(cleanlinessSlider);
		
		JLabel lblLighting = new JLabel("Lighting");
		lblLighting.setForeground(Color.WHITE);
		lblLighting.setFont(new Font("Roboto", Font.BOLD, 11));
		lblLighting.setBounds(269, 494, 127, 25);
		contentPane.add(lblLighting);
		
		JSlider lightingSlider = new JSlider();
		lightingSlider.setPaintLabels(true);
		lightingSlider.setMaximum(5);
		lightingSlider.setMajorTickSpacing(1);
		lightingSlider.setBounds(374, 494, 560, 35);
		contentPane.add(lightingSlider);
		
		JLabel lblComfort = new JLabel("Comfort");
		lblComfort.setForeground(Color.WHITE);
		lblComfort.setFont(new Font("Roboto", Font.BOLD, 11));
		lblComfort.setBounds(269, 534, 127, 25);
		contentPane.add(lblComfort);
		
		JSlider comfortSlider = new JSlider();
		comfortSlider.setPaintLabels(true);
		comfortSlider.setMaximum(5);
		comfortSlider.setMajorTickSpacing(1);
		comfortSlider.setBounds(374, 540, 560, 35);
		contentPane.add(comfortSlider);
		
		JLabel lblMusic = new JLabel("Music");
		lblMusic.setForeground(Color.WHITE);
		lblMusic.setFont(new Font("Roboto", Font.BOLD, 11));
		lblMusic.setBounds(269, 582, 127, 25);
		contentPane.add(lblMusic);
		
		JSlider musicSlider = new JSlider();
		musicSlider.setPaintLabels(true);
		musicSlider.setMaximum(5);
		musicSlider.setMajorTickSpacing(1);
		musicSlider.setBounds(374, 588, 560, 35);
		contentPane.add(musicSlider);
		
		JLabel lblSpeedOfService = new JLabel("Speed of Service");
		lblSpeedOfService.setForeground(Color.WHITE);
		lblSpeedOfService.setFont(new Font("Roboto", Font.BOLD, 11));
		lblSpeedOfService.setBounds(269, 726, 127, 25);
		contentPane.add(lblSpeedOfService);
		
		JLabel lblQualityOfService = new JLabel("Quality of Service");
		lblQualityOfService.setForeground(Color.WHITE);
		lblQualityOfService.setFont(new Font("Roboto", Font.BOLD, 11));
		lblQualityOfService.setBounds(269, 772, 127, 25);
		contentPane.add(lblQualityOfService);
		
		JLabel lblKnowledge = new JLabel("Knowledge");
		lblKnowledge.setForeground(Color.WHITE);
		lblKnowledge.setFont(new Font("Roboto", Font.BOLD, 11));
		lblKnowledge.setBounds(269, 812, 127, 25);
		contentPane.add(lblKnowledge);
		
		JSlider speedSlider = new JSlider();
		speedSlider.setPaintLabels(true);
		speedSlider.setMaximum(5);
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setBounds(374, 726, 560, 35);
		contentPane.add(speedSlider);
		
		JSlider qualitySlider = new JSlider();
		qualitySlider.setPaintLabels(true);
		qualitySlider.setMaximum(5);
		qualitySlider.setMajorTickSpacing(1);
		qualitySlider.setBounds(374, 772, 560, 35);
		contentPane.add(qualitySlider);
		
		JSlider knowledgeSlider = new JSlider();
		knowledgeSlider.setPaintLabels(true);
		knowledgeSlider.setMaximum(5);
		knowledgeSlider.setMajorTickSpacing(1);
		knowledgeSlider.setBounds(374, 818, 560, 35);
		contentPane.add(knowledgeSlider);
		
		JLabel customerServiceQuestion = new JLabel("Customer Service");
		customerServiceQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		customerServiceQuestion.setForeground(Color.WHITE);
		customerServiceQuestion.setFont(new Font("Roboto", Font.BOLD, 15));
		customerServiceQuestion.setBounds(258, 672, 675, 35);
		customerServiceQuestion.setText(questionCustomerService);
		contentPane.add(customerServiceQuestion);
		
		
		
		Border border2 = BorderFactory.createLineBorder(Color.white, 1);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(border2);
		panel_1.setLayout(null);
		panel_1.setBackground((Color) null);
		panel_1.setBounds(1037, 494, 446, 131);
		contentPane.add(panel_1);
		
		JRadioButton rdbtnLikely = new JRadioButton("Likely");
		rdbtnLikely.setBackground((Color) null);
		rdbtnLikely.setBounds(16, 7, 109, 23);
		panel_1.add(rdbtnLikely);
		
		JRadioButton rdbtnHighlyLikely = new JRadioButton("Highly Likely");
		rdbtnHighlyLikely.setBackground((Color) null);
		rdbtnHighlyLikely.setBounds(16, 39, 109, 23);
		panel_1.add(rdbtnHighlyLikely);
		
		JRadioButton rdbtnUnlikely = new JRadioButton("Unlikely");
		rdbtnUnlikely.setBackground((Color) null);
		rdbtnUnlikely.setBounds(16, 68, 109, 23);
		panel_1.add(rdbtnUnlikely);
		
		JRadioButton rdbtnSomewhatLikely = new JRadioButton("Somewhat Likely");
		rdbtnSomewhatLikely.setBackground((Color) null);
		rdbtnSomewhatLikely.setBounds(16, 98, 170, 23);
		panel_1.add(rdbtnSomewhatLikely);
		
		 ButtonGroup recFrequecncy = new ButtonGroup();
		 recFrequecncy.add(rdbtnHighlyLikely);
		 recFrequecncy.add(rdbtnLikely);
		 recFrequecncy.add(rdbtnSomewhatLikely);
		 recFrequecncy.add(rdbtnUnlikely);
		 
		 
		JLabel recommendFrequencyQuestion = new JLabel("How likely would you be to recommend us to a friend?");
		recommendFrequencyQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		recommendFrequencyQuestion.setForeground(Color.WHITE);
		recommendFrequencyQuestion.setFont(new Font("Roboto", Font.BOLD, 15));
		recommendFrequencyQuestion.setBounds(1037, 465, 663, 18);
		recommendFrequencyQuestion.setText(questionRecommendFrequency);
		contentPane.add(recommendFrequencyQuestion);
		
		JLabel promotedWayQuestion = new JLabel("How did you hear about the restaurant?");
		promotedWayQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		promotedWayQuestion.setForeground(Color.WHITE);
		promotedWayQuestion.setFont(new Font("Roboto", Font.BOLD, 15));
		promotedWayQuestion.setBounds(1037, 698, 530, 18);
		promotedWayQuestion.setText(questionPromotedWay);
		contentPane.add(promotedWayQuestion);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(border2);
		panel_2.setLayout(null);
		panel_2.setBackground((Color) null);
		panel_2.setBounds(1035, 726, 446, 131);
		contentPane.add(panel_2);
		
		JRadioButton rdbtnNewspaper = new JRadioButton("Newspaper");
		rdbtnNewspaper.setBackground((Color) null);
		rdbtnNewspaper.setBounds(16, 7, 109, 23);
		panel_2.add(rdbtnNewspaper);
		
		JRadioButton rdbtnAnotherWebsite = new JRadioButton("Website");
		rdbtnAnotherWebsite.setBackground((Color) null);
		rdbtnAnotherWebsite.setBounds(16, 39, 252, 23);
		panel_2.add(rdbtnAnotherWebsite);
		
		JRadioButton rdbtnSocialMedia = new JRadioButton("Social Media");
		rdbtnSocialMedia.setBackground((Color) null);
		rdbtnSocialMedia.setBounds(16, 68, 109, 23);
		panel_2.add(rdbtnSocialMedia);
		
		JRadioButton rdbtnFriendly = new JRadioButton("Friend");
		rdbtnFriendly.setBackground((Color) null);
		rdbtnFriendly.setBounds(16, 98, 109, 23);
		panel_2.add(rdbtnFriendly);
		
		 ButtonGroup promotedWay = new ButtonGroup();
		 promotedWay.add(rdbtnNewspaper);
		 promotedWay.add(rdbtnAnotherWebsite);
		 promotedWay.add(rdbtnSocialMedia);
		 promotedWay.add(rdbtnFriendly);
		
		
		Border border3 = BorderFactory.createLineBorder(Color.white, 1);	
		JPanel panel = new JPanel();
		panel.setBorder(border3);
		panel.setLayout(null);
		panel.setBackground((Color) null);
		panel.setBounds(1035, 238, 446, 173);
		contentPane.add(panel);
		
		JRadioButton rdbtnWeekly = new JRadioButton("Weekly");
		rdbtnWeekly.setBackground((Color) null);
		rdbtnWeekly.setBounds(16, 7, 109, 23);
		panel.add(rdbtnWeekly);
		
		JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.setBackground((Color) null);
		rdbtnMonthly.setBounds(16, 39, 109, 23);
		panel.add(rdbtnMonthly);
		
		JRadioButton rdbtnDaily = new JRadioButton("Daily");
		rdbtnDaily.setBackground((Color) null);
		rdbtnDaily.setBounds(16, 68, 109, 23);
		panel.add(rdbtnDaily);
		
		JRadioButton rdbtnFirstTime = new JRadioButton("First Time");
		rdbtnFirstTime.setBackground((Color) null);
		rdbtnFirstTime.setBounds(16, 98, 109, 23);
		panel.add(rdbtnFirstTime);
		
		ButtonGroup visitFrequency = new ButtonGroup();
		visitFrequency.add(rdbtnWeekly);
		visitFrequency.add(rdbtnMonthly);
		visitFrequency.add(rdbtnDaily);
		visitFrequency.add(rdbtnFirstTime);
		
		
		JLabel diningFrequencyQuestion = new JLabel("");
		diningFrequencyQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		diningFrequencyQuestion.setForeground(Color.WHITE);
		diningFrequencyQuestion.setFont(new Font("Roboto", Font.BOLD, 15));
		diningFrequencyQuestion.setBounds(1037, 209, 649, 18);
		diningFrequencyQuestion.setText(questionDiningFrequency);
		contentPane.add(diningFrequencyQuestion);
		
		suggestionTxt = new JTextField();
		suggestionTxt.setBounds(266, 891, 1217, 64);
		contentPane.add(suggestionTxt);
		suggestionTxt.setColumns(10);
		
		JLabel suggestionsQuestion = new JLabel("");
		suggestionsQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		suggestionsQuestion.setForeground(Color.WHITE);
		suggestionsQuestion.setFont(new Font("Roboto", Font.BOLD, 15));
		suggestionsQuestion.setBounds(259, 868, 1217, 25);
		suggestionsQuestion.setText(questionSuggestion);
		contentPane.add(suggestionsQuestion);
		
		
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int taste = tasteSlider.getValue();
				int	value =  valueSlider.getValue();
				int temperature = temperatureSlider.getValue();
				int cleanliness = cleanlinessSlider.getValue();
				int lighting = lightingSlider.getValue();
				int comfort = comfortSlider.getValue();
				int music = musicSlider.getValue();
				int speed = speedSlider.getValue();
				int quality = qualitySlider.getValue();
				int knowledge = knowledgeSlider.getValue();
				String suggestion = suggestionTxt.getText();
				String frequencyOfVisiting=getSelectedButtonText(visitFrequency);
				String frequencyOfRecommendation=getSelectedButtonText(recFrequecncy);
				String promotionMethod=getSelectedButtonText(promotedWay);
					
					try {
						String serviceName="rmi://0.0.0.0:2000/connect";
						RemoteFunctions service = (RemoteFunctions) Naming.lookup(serviceName);
						service.userFeedback(1, taste, value, temperature, cleanliness, lighting, comfort, music, speed, quality, knowledge, suggestion, frequencyOfVisiting, frequencyOfRecommendation, promotionMethod);
//						LandingScreen ls = new LandingScreen();
//						ls.setVisible(true);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//				
//				
//				try {
//					service.userInput(review.orderNo, taste, plating, portion, serveTime, waitingStaff, cleanliness, lighting,
//							comfort, op, message, date);
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
				
			}
		});
		btnNewButton.setBackground(new Color(149, 189, 250));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(true);
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Roboto", Font.BOLD, 20));
		btnNewButton.setBounds(845, 966, 210, 64);
		contentPane.add(btnNewButton);
	}
}
