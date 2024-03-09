package card;

public class CardSetOption {
	
	public static boolean[] rbox=new boolean[52];
	public static int useall;
	public Player banker,guest;

	
	public static void main(String[] args) {
	        String[] color = {"Spades", "Heart", "Diamonds", "Club"};
	        String[] number = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	        
	        for(int i=0;i<52;i++) {
	        	rbox[i]=false;
	        }
	     }

	public void FirstSetPlayer() {
		Player banker=new Player();
		this.banker=banker;
		Player guest=new Player();
		this.guest=guest;
	}
	
	public void SecondSetPlayer() {
		Player banker=new Player();
		this.banker=banker;
		Player guest=new Player();
		this.guest=guest;
	}
	
	public void newGameSet(){
		for(int i=0;i<52;i++) {
        	rbox[i]=false;
        	}
		useall=0;
		}
}
