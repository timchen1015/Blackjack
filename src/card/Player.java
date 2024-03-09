package card;

import application.PopBox;

public class Player {
	public String name;
    public int money;
	public int sum=0;
	public int checkA=0;
	public static String result;
	



	public static void compare(Player p,Player d ){
	        if(p.sum>d.sum) {
	        	result="WIN";
	        	PopBox.display("RESULT", "YOU WIN");
	        }
	        else if(p.sum<d.sum) {
	        	result="LOSE";
	        	PopBox.display("RESULT", "YOU Lose");
	        }
	        else if(p.sum==d.sum) {
	        	result="EVEN";
	        	PopBox.display("RESULT", "TIE");
	        	}
	       }
	  
	    
		public static void ToBig(Player p,Player d) {
			if(d.sum>21) {
				result="WIN";
				PopBox.display("RESULT", "YOU WIN");
				
			}
			else if(p.sum>21) {
				result="LOSE";
				PopBox.display("RESULT", "YOU LOSE");
			}
		}
	
		public static int calculateSum(Player a,Card cardNum) {
			String Num=cardNum.getNum(cardNum);
			if(Num=="J"||Num=="Q"||Num=="K") {
				a.sum+=10;
			}
			else if(Num=="A") {
				a.sum+=11;
				a.checkA+=1;
			}
			else {
				a.sum+=Integer.parseInt(Num);
			}
			if(a.sum>21&&a.checkA>0) {
				switch(a.checkA) {
					case 1:{
						a.sum-=10;
						a.checkA-=1;
						break;
					}
					case 2:{
						a.sum-=10;
						a.checkA-=1;
						if(a.sum>21) {
							a.sum-=10;a.checkA-=1;
						}
						break;
					}
					case 3:{
						a.sum-=10;a.checkA-=1;
						if(a.sum>21) {
							a.sum-=10;a.checkA-=1;
						}
						if(a.sum>21) {
							a.sum-=10;a.checkA-=1;
						}
						break;
					}
					case 4:{
						a.sum-=10;a.checkA-=1;
						if(a.sum>21) {
							a.sum-=10;a.checkA-=1;
						}
						if(a.sum>21) {
							a.sum-=10;a.checkA-=1;
						}
						if(a.sum>21) {
							a.sum-=10;a.checkA-=1;
						}
						break;
					}
				}
			}			
		
			return a.sum;
		}
	}
