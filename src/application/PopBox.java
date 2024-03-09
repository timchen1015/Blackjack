package application;

import javafx.scene.control.Label;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

import card.CardSetOption;
import card.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PopBox {
	public static void display(String title,String message) {
		Stage window=new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinHeight(250);
		
		Label label=new Label();
		label.setText(message);
		Button closeButton=new Button("Continue");
		if(Money.getPlayerMoney()==0) {
			closeButton.setOnAction(e->System.exit(0));
		}
		else {
	    closeButton.setOnAction(e->window.close());
		}
		VBox layout=new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene=new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		
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
	}
