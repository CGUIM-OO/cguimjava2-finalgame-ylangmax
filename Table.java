import java.util.ArrayList;

public class Table {
	public static final int MAXPLAYER = 4;

	private Deck deck;
	private Player[] players;
	private Dealer dealer;
	private int[] pos_betArray;
	private int nDecks;
	public Table(int nDecks) {
		this.nDecks=nDecks;
		deck = new Deck(nDecks);
		players = new Player[MAXPLAYER];
	}

	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}

	/**
	 *  Let player p seat at seat pos
	 */
	public void set_player(int pos, Player p){
		if (pos >= 0 && pos <= players.length)
			players[pos] = p;
	}

	public Player[] get_player(){
		return players;
	}

	/**
	 *  Set the dealer
	 */
	public void set_dealer(Dealer d){
		dealer = d;
	}


	public Card get_face_up_card_of_dealer(){
		//TODO: Modify the following line and insert the necessary code
		if (dealer!=null){
			Card dealersFaceUpCard=dealer.getOneRoundCard().get(0);
			return dealersFaceUpCard;
		}
		else{
			System.out.println("Sorry!! There is no Dealer!");
			return null;
		}

	}

	public ArrayList<Card> getOpenedCards(){
		return deck.getOpenedCard();
	}
	public int getNumberOfDeck(){
		return nDecks;
	}
	private void ask_each_player_about_bets(){
		pos_betArray=new int[players.length];
		for(int i = 0; i < players.length; i++){
			if (players[i] != null){
				players[i].say_hello();
				int bet=players[i].make_bet();
				if(bet>players[i].get_current_chips()){
					if(players[i].get_current_chips()==0){
						players[i].setBet(0);
						pos_betArray[i]=0;
					}else{
						players[i].setBet(players[i].get_current_chips());
						pos_betArray[i]=players[i].get_current_chips();
					}
				}else{
					pos_betArray[i]=bet;
				}
			}
		}
	}

	private void distribute_cards_to_dealer_and_players(){
		for(int i = 0; i < players.length; i++){
			if (players[i] != null && pos_betArray[i]!=0){
				ArrayList<Card> playersCard=new ArrayList<Card>();
				playersCard.add(deck.getOneCard(true));
				playersCard.add(deck.getOneCard(true));
				players[i].setOneRoundCard(playersCard);

			}
		}
		if (dealer!=null){
			ArrayList<Card> dealersCard=new ArrayList<Card>();
			dealersCard.add(deck.getOneCard(true));
			dealersCard.add(deck.getOneCard(false));
			dealer.setOneRoundCard(dealersCard);
			System.out.print("Dealer's face up card is ");
			Card dealers_face_up_card=get_face_up_card_of_dealer();
			dealers_face_up_card.printCard();
		}

	}

	private void ask_each_player_about_hits(){
		for(int i = 0; i < players.length; i++){
			if (players[i] != null && pos_betArray[i]!=0){
				System.out.print(players[i].get_name()+"'s Cards now:");
				players[i].printAllCard();
				hit_process(i,players[i].getOneRoundCard());
				System.out.println(players[i].get_name()+"'s hit is over!");
			}
		}
	}
	private void hit_process(int pos,ArrayList<Card> cards){
		boolean hit;
		do{
			hit=players[pos].hit_me(this); //this
			if(hit){
				cards.add(deck.getOneCard(true));
				players[pos].setOneRoundCard(cards);
				System.out.print("Hit! ");
				System.out.print(players[pos].get_name()+"'s Cards now:");
				players[pos].printAllCard();
				if (players[pos].getTotalValue()>21){
					hit=false;
				}
			}
			else{
				System.out.println("Pass hit!");
			}

		}while(hit);
	}
	private void ask_dealer_about_hits(){
		ArrayList<Card> cards=dealer.getOneRoundCard();
		boolean hit;
		do{
			hit=dealer.hit_me(this);
			if (hit){
				cards.add(deck.getOneCard(true));
				dealer.setOneRoundCard(cards);
			}
			if (dealer.getTotalValue()>21){
				hit=false;
			}
		}
		while(hit);
		System.out.println("Dealer's hit is over!");
	}

	private void calculate_chips(){
		int dealersCradValue=dealer.getTotalValue();
		System.out.print("Dealer's card value is "+dealersCradValue+" ,Cards:");
		dealer.printAllCard();
		for(int i=0;i<players.length;i++){
			if (players[i]==null || pos_betArray[i]==0){
			}
			else{
				System.out.print(players[i].get_name()+"'s Cards: ");
				players[i].printAllCard();
				caculate_process(i);

			}
		}	
	}

	private void caculate_process(int pos){

		System.out.print(players[pos].get_name()+" card value is "+players[pos].getTotalValue());
		if (players[pos].getTotalValue()>21){
			if(dealer.getTotalValue()>21){
				System.out.println(", chips have no change!, the Chips now is: "+players[pos].get_current_chips());
			}
			else{
				players[pos].increase_chips(-pos_betArray[pos]);
				System.out.println(", Loss "+pos_betArray[pos]+" Chips, the Chips now is: "+players[pos].get_current_chips());
			}
		}
		else if (players[pos].getTotalValue()==21){
			if(players[pos].getOneRoundCard().size()==2 && players[pos].hasAce()){
				if(dealer.getTotalValue()!=21){
					players[pos].increase_chips(pos_betArray[pos]*2);
					System.out.println(",Black jack!!! Get "+pos_betArray[pos]+" Chips, the Chips now is: "+players[pos].get_current_chips());
				}
				else{
					if(dealer.getOneRoundCard().size()==2 && dealer.hasAce()){
						System.out.println(",Black Jack!!!! But chips have no change!, the Chips now is: "+players[pos].get_current_chips());
					}
					else{
						players[pos].increase_chips(pos_betArray[pos]*2);
						System.out.println(",Black jack!!! Get "+pos_betArray[pos]+" Chips, the Chips now is: "+players[pos].get_current_chips());
					}
				}
			}
			else{
				if(dealer.getTotalValue()!=21){
					players[pos].increase_chips(pos_betArray[pos]*2);
					System.out.println(",Get "+pos_betArray[pos]+" Chips, the Chips now is: "+players[pos].get_current_chips());
				}
				else{
					System.out.println(",chips have no change!The Chips now is: "+players[pos].get_current_chips());
				}
			}
		}
		else{
			if(dealer.getTotalValue()>21){
				players[pos].increase_chips(pos_betArray[pos]);
				System.out.println(", Get "+pos_betArray[pos]+" Chips, the Chips now is: "+players[pos].get_current_chips());
			}
			else if (dealer.getTotalValue()<players[pos].getTotalValue()){
				players[pos].increase_chips(pos_betArray[pos]);
				System.out.println(", Get "+pos_betArray[pos]+" Chips, the Chips now is: "+players[pos].get_current_chips());
			}
			else if(dealer.getTotalValue()>players[pos].getTotalValue()){
				players[pos].increase_chips(-pos_betArray[pos]);
				System.out.println(", Loss "+pos_betArray[pos]+" Chips, the Chips now is: "+players[pos].get_current_chips());
			}
			else{
				System.out.println(", chips have no change! The Chips now is: "+players[pos].get_current_chips());
			}
		}
	}


	public int[] get_palyers_bet(){
		return pos_betArray;
	}
	public double getPercentofUsedCard(){
		return ((double)deck.nUsed)/((double)deck.getAllCards().size());
	}

} 
