package application;
import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import card.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
public class SceneController {
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	public ImageView myview1;
	public ImageView myview2;
	public ImageView myview3;
	public ImageView myview6;
	public ImageView myview5;
	public ImageView bankerview1;
	public ImageView bankerview2;
	public ImageView bankerview3;
	public ImageView bankerview4;
	public ImageView bankerview5;
	public ImageView bankerview6;
	public CardSetOption cardset;
	public Image back=new Image(getClass().getResourceAsStream("pokerback.jpg"));
	public static int time=0,timebank=0;
	public Text myscore;
	public Text mymoney;
	
		public void switchToScene1(ActionEvent event)throws IOException{
			Parent root=FXMLLoader.load(getClass().getResource("Scene1.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

		CardSetOption cardsetoption=new CardSetOption();
		
		public void getStart(ActionEvent event)throws IOException{
			Parent root=FXMLLoader.load(getClass().getResource("Scene2.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
			cardsetoption.FirstSetPlayer();
			Money.finalPlayerMoney(Player.result);
			TextInputDialog textInput=new TextInputDialog();
			textInput.setTitle("BET");
			textInput.setHeaderText("Now you have"+Money.getPlayerMoney()+"\nenter bet");
			Optional<String> opt=textInput.showAndWait();
			String input;
			for(;;) {
				if(opt.isPresent()==false||opt.get()==""||!opt.get().matches("[+-]?\\d*(\\.\\d+)?")|| Integer.parseInt(opt.get())>Money.getPlayerMoney()||Integer.parseInt(opt.get())<=0) {
					textInput.setTitle("BET");
					textInput.setHeaderText("Now you have"+Money.getPlayerMoney()+"\nEnter the correct money again");
					opt = textInput.showAndWait();
				}
				else {
						input=opt.get();
					break;
				}
			}
			Money.storePlayerBet(Integer.parseInt(input));
		}
		
		public void displayImage(ImageView myview, Image pic) {
			myview.setImage(pic);
			}
		
		
		public void getGameStart(ActionEvent event)throws IOException{
			Parent root=FXMLLoader.load(getClass().getResource("Scene2.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			scene=new Scene(root);
			stage.show();
			cardsetoption.SecondSetPlayer();
			time=0;timebank=0;
			card.Card bankerCard1 = new card.Card();
			bankerCard1.getCard();
			cardsetoption.banker.calculateSum(cardsetoption.banker, bankerCard1);
			displayImage(bankerview1,bankerCard1.img);
			displayImage(bankerview2,back);
			displayImage(bankerview3,null);
			displayImage(bankerview4,null);
			displayImage(bankerview5,null);
			card.Card card1 = new card.Card();
			card1.getCard();
			cardsetoption.guest.calculateSum(cardsetoption.guest, card1);
			displayImage(this.myview1 ,card1.img);
			card.Card card2 = new card.Card();
			card2.getCard();
			cardsetoption.guest.calculateSum(cardsetoption.guest, card2);
			displayImage(myview2,card2.img);
			displayImage(myview3,null);
			displayImage(myview5,null);
			displayImage(myview6,null);
			myscore.setText("My point  "+cardsetoption.guest.sum);
			mymoney.setText("My money  "+Money.getPlayerMoney());
			}
		
		
		public void getCardInController(ActionEvent e) {
			time+=1;
			if(time==1) {
				card.Card card3 = new card.Card();
				card3.getCard();
				Player.calculateSum(cardsetoption.guest, card3);
				displayImage(myview3,card3.img);
				myscore.setText("My point  "+cardsetoption.guest.sum);
				mymoney.setText("My money  "+Money.getPlayerMoney());
				Player.ToBig(cardsetoption.guest,cardsetoption.banker);
			}
			
			else if(time==2) {
				card.Card card4 = new card.Card();
				card4.getCard();
				Player.calculateSum(cardsetoption.guest, card4);
				displayImage(myview6,card4.img);
				mymoney.setText("My point  "+cardsetoption.guest.sum);
				myscore.setText("My money  "+Money.getPlayerMoney());
				Player.ToBig(cardsetoption.guest,cardsetoption.banker);
			
				
			}
			else if(time==3) {
				card.Card card5 = new card.Card();
				card5.getCard();
				Player.calculateSum(cardsetoption.guest, card5);
				displayImage(myview5,card5.img);
				if(cardsetoption.guest.sum>21) {
					myscore.setText("My point  "+cardsetoption.guest.sum);
					mymoney.setText("My money  "+Money.getPlayerMoney());
				Player.ToBig(cardsetoption.guest,cardsetoption.banker);
			
							}
				else if(cardsetoption.guest.sum<=21) {
					myscore.setText("my point now is "+cardsetoption.guest.sum);
					System.out.println("You Win");
		        	System.out.println("Game End");
		        	PopBox.display("RESULT", "YOU WIN");
		        	}
				}
			}
		
		
		
		public void StopGame(ActionEvent e) {
			card.Card bankerCard2 = new card.Card();
			bankerCard2.getCard();
			Player.calculateSum(cardsetoption.banker, bankerCard2);
			displayImage(bankerview2,bankerCard2.img);
			
		if(cardsetoption.banker.sum<17&&timebank==0) {
				card.Card bankerCard3 = new card.Card();
				bankerCard3.getCard();
				Player.calculateSum(cardsetoption.banker, bankerCard3);
				bankerCard3.setImage();
				displayImage(bankerview3,bankerCard3.img);
				timebank+=1;
				Player.ToBig(cardsetoption.guest,cardsetoption.banker);
			}
		if(cardsetoption.banker.sum<17&&timebank==1) {
				card.Card bankerCard4 = new card.Card();
				bankerCard4.getCard();
				Player.calculateSum(cardsetoption.banker, bankerCard4);
				displayImage(bankerview4,bankerCard4.img);
				timebank+=1;
				Player.ToBig(cardsetoption.guest,cardsetoption.banker);
			}
		if(cardsetoption.banker.sum<17&&timebank==2) {
				card.Card bankerCard5 = new card.Card();
				bankerCard5.getCard();
				Player.calculateSum(cardsetoption.banker, bankerCard5);
				displayImage(bankerview5,bankerCard5.img);
				timebank+=1;
				Player.ToBig(cardsetoption.guest,cardsetoption.banker);
			}
		if(cardsetoption.banker.sum<17&&timebank==3) {
			card.Card bankerCard6 = new card.Card();
			bankerCard6.getCard();
			Player.calculateSum(cardsetoption.banker, bankerCard6);
			bankerCard6.setImage();
			displayImage(bankerview3,bankerCard6.img);
			timebank+=1;
			Player.ToBig(cardsetoption.guest,cardsetoption.banker);
		}
		else if((cardsetoption.banker.sum>=17&&cardsetoption.banker.sum<=21)||timebank>=5) {
				Player.compare(cardsetoption.guest,cardsetoption.banker);
			
			}
		}

	}
