package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SampleController implements Initializable {

	private static String userChoice;
	private static String computerChoice;
	private static Integer userScor = 0;
	private static Integer computerScor = 0;

	@FXML
	private RadioButton rbRock;

	@FXML
	private RadioButton rbPaper;

	@FXML
	private RadioButton rbScissors;

	@FXML
	private Label lblResult;

	@FXML
	private Label lblUserScor;

	@FXML
	private Label lblComputerScor;

	@FXML
	private Label lblShowComputerChoice;

	@FXML
	private ImageView comChoiceImage;

	@FXML
	private Button btnRestart;

	@FXML
	void playGame(ActionEvent event) throws InterruptedException, IOException {
		// User Choice
		if (rbRock.isSelected()) {
			userChoice = "rock";
		} else if (rbPaper.isSelected()) {
			userChoice = "paper";
		} else {
			userChoice = "scissors";
		}

		// Computer Choice
		computerChoice = computerChoice();
		
		Image imageRock = new Image(getClass().getResourceAsStream("Rr.png"));
		Image imagePaper = new Image(getClass().getResourceAsStream("Pp.png"));
		Image imageScissors = new Image(getClass().getResourceAsStream("Ss.png"));
		

		if (computerChoice == "rock") {
			lblShowComputerChoice.setText("ROCK");
			comChoiceImage.setImage(imageRock);
		} else if (computerChoice == "paper") {
			lblShowComputerChoice.setText("PAPER");
			comChoiceImage.setImage(imagePaper);
		} else {
			lblShowComputerChoice.setText("SCISSORS");
			comChoiceImage.setImage(imageScissors);
		}

		// Evaluation and game result
		Integer result = evaluation(userChoice + "-" + computerChoice);

		switch (result) {
		case 1:
			lblResult.setText("Congratulation! You Won ");
			lblUserScor.setText(Integer.toString(++userScor));
			break;
		case -1:
			lblResult.setText("   Sorry!  I Won ");
			lblComputerScor.setText(Integer.toString(++computerScor));
			break;
		default:
			lblResult.setText("We have same choice!");
			break;
		}

		rbRock.setSelected(false);
		rbPaper.setSelected(false);
		rbScissors.setSelected(false);
		
		btnRestart.setOnAction(e->restart());

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ToggleGroup rbGroup = new ToggleGroup();
		rbRock.setToggleGroup(rbGroup);
		rbPaper.setToggleGroup(rbGroup);
		rbScissors.setToggleGroup(rbGroup);

	}

	private static Integer evaluation(String result) {
		Dictionary<String, Integer> matrix = new Hashtable<String, Integer>();
		matrix.put("rock-rock", 0);
		matrix.put("paper-rock", 1);
		matrix.put("scissors-rock", -1);
		matrix.put("rock-paper", -1);
		matrix.put("paper-paper", 0);
		matrix.put("scissors-paper", 1);
		matrix.put("rock-scissors", 1);
		matrix.put("paper-scissors", -1);
		matrix.put("scissors-scissors", 0);

		return matrix.get(result);
	}

	private static String computerChoice() {
		ArrayList<String> options = new ArrayList<String>();
		options.add("rock");
		options.add("paper");
		options.add("scissors");

		Random random = new Random();
		String randomChoice = options.get(random.nextInt(options.size()));
		return randomChoice;
	}

	@FXML
	private void restart() {
		userScor = 0;
		computerScor = 0;
		lblComputerScor.setText("0");
		lblUserScor.setText("0");
		lblResult.setText("New game has been started!");

	}
	

}
