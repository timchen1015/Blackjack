package application;

public class Money {
	private static int playerMoney=1000;
	private static int playerBet;
	
	public static int getPlayerMoney() {
		return playerMoney;
	}
	public static  void storePlayerBet(int input) {
		Money.playerBet=input;
		Money.playerMoney-=input;
		}

	public static void finalPlayerMoney(String result) {
		if(result=="WIN") {
			Money.playerMoney+=(playerBet*2);
				}
		else if(result=="TIE") {
			Money.playerMoney+=playerBet;
		}
		else if(result=="LOSE") {
			Money.playerMoney+=0;
		}
	}
}