import java.util.ArrayList;

public class Player_B0544243 extends Player {
	// dealer機制：若小於17或等於17又有1則再要牌

	public Player_B0544243(int chips) {
		super("鄒涵如_B0544243", chips);
		// TODO Auto-generated constructor stub
	}
	private int PreChip =1000;
	private int winGame=0;
	private int b=200;
	private int OtherChip=0;
	private ArrayList<Card> MyUsedCard=new ArrayList<Card>();
	
	private boolean LastGame;
	private int lostGame=0;
	private int Pset=0;
	
	private int RunningCount=0;
	private double TrueCount=0;
	public int make_bet(){
		b=200;
		if(get_current_chips()>=PreChip){
			winGame++;//一進場就=1
			lostGame=0;
		}
		else{
			lostGame++;
			winGame=0;
		}
		
		if(winGame<=1){
			setBet(b);
		}
		else if(winGame==2){//贏第一場
			setBet(b*2);
		}
		else if(winGame==3){//贏第二場
			setBet((int)(b*2.5));
			winGame=1;//若繼續贏則重置
		}
		if(OtherChip>(get_current_chips()+2500)){
			b=(OtherChip-get_current_chips());
			while(b>get_current_chips()){
				b=b/2;
			}
			setBet(b);
		}
		else{
			b=200;
			}
		
		if(get_current_chips()<400||getBet()==get_current_chips()||getBet()>get_current_chips()){
			setBet((int)(get_current_chips()*0.1));
			return getBet();
		}
		else{
			return getBet();
		}
	}

	
	public boolean hit_me(Table table){
		Player[] MyPlayers = table.get_player();
		for(int i=0;i<2;i++){
			if(MyPlayers[i].get_name()!="鄒涵如_B0544243"){
				Pset=i;
			}
		}
		OtherChip=MyPlayers[Pset].get_current_chips();
		
		
		MyUsedCard=table.getOpenedCards();
		count();
		TrueCount=RunningCount/((208-table.getOpenedCards().size())/52);
		
		/*if (table.getPercentofUsedCard() >= 0.72115385){//用到0.75時會停一場會有6張牌
			 LastGame= true;}*/
		
		if(hasAce()==false){
			/*if(getTotalValue()==12&&table.get_face_up_card_of_dealer().getRank()>=4&&table.get_face_up_card_of_dealer().getRank()<=6){
				return false;
			}
			 if(getTotalValue()==13&&table.get_face_up_card_of_dealer().getRank()>=2&&table.get_face_up_card_of_dealer().getRank()<=6){
				return false;
			}
			else if(getTotalValue()==14&&table.get_face_up_card_of_dealer().getRank()>=2&&table.get_face_up_card_of_dealer().getRank()<=6){
				return false;
			}*/
			 if(getTotalValue()==15&&table.get_face_up_card_of_dealer().getRank()>=2&&table.get_face_up_card_of_dealer().getRank()<=6){
				return false;
			}
			else if(getTotalValue()==16&&table.get_face_up_card_of_dealer().getRank()>=2&&table.get_face_up_card_of_dealer().getRank()<=6){
				return false;
			}
			else if(getTotalValue()>=17){
				return false;
			}
			else{
				return true;
			}
		}
		else {//MyAce_11>0){ //hasAce()==true 有Ａ且Ａ==11
			if(getTotalValue()>=19){
				return false;
			}
			else if(getTotalValue()==18&&(table.get_face_up_card_of_dealer().getRank()<=8)&&(table.get_face_up_card_of_dealer().getRank()>=2)){
				return false;
			}
			else
				return true;
			}
			}
		
	
	
	public void count(){
		for(Card card:MyUsedCard){
			if(card.getRank()==1||card.getRank()==10||card.getRank()==11||card.getRank()==12||card.getRank()==13){
				RunningCount--;
			}
			else if(card.getRank()==2||card.getRank()==3||card.getRank()==4||card.getRank()==5||card.getRank()==6){
				RunningCount++;
			}
		}
		
	}
}
