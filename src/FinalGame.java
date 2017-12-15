

public class FinalGame {
	public static void main(String[] args) {
		int TotalWinsNeeded = 200;
		int[] NumberOfWins = new int[] { 0, 0 };
		String[] PlayerNames = new String[] { "", "" };
		boolean isFinal = false;
		int totlaGames=0;
		while (!isFinal) {
			Table tbl = new Table(4);
			tbl.set_player(0, new PlayerEx (1000)); 
			tbl.set_player(1, new PlayerEx (1000)); 
			
			tbl.set_dealer(new Dealer());
			boolean isGameOver = false;
			while (!isGameOver) {
				tbl.play();
				Player[] Players = tbl.get_player();
				PlayerNames[0] = Players[0].get_name();
				PlayerNames[1] = Players[1].get_name();
				if (Players[0].get_current_chips() <= 0 || tbl.get_palyers_bet()[0] == 0) {
					isGameOver = true;
					System.out.println(Players[0].get_name() + " has no chips.");
				} else if (Players[1].get_current_chips() <= 0 || tbl.get_palyers_bet()[1] == 0) {
					isGameOver = true;
					System.out.println(Players[1].get_name() + " has no chips.");
				} else if (tbl.getPercentofUsedCard() > 0.75) {
					isGameOver = true;
					System.out.println("The game is finished (no card).");
				}
			}
			Player[] Players = tbl.get_player();
			System.out.print("The winner is: ");
			if (Players[0].get_current_chips() > Players[1].get_current_chips()) {
				NumberOfWins[0]++;
				System.out.println(Players[0].get_name() + ", chips: " + Players[0].get_current_chips());
			} else if (Players[1].get_current_chips() > Players[0].get_current_chips()) {
				System.out.println(Players[1].get_name() + ", chips: " + Players[1].get_current_chips());
				NumberOfWins[1]++;
			} else {
				System.out.println("Dealer! We need another game!");
			}
			if((NumberOfWins[0]>=TotalWinsNeeded||NumberOfWins[1]>=TotalWinsNeeded)&&
					NumberOfWins[0]!=NumberOfWins[1]){
				isFinal=true;
			}
			totlaGames++;
		}
		if (NumberOfWins[0] > NumberOfWins[1]) {
			System.out.println("After "+totlaGames+" games, "+PlayerNames[0] + " wins the competition! "+NumberOfWins[0]+ " vs. "+NumberOfWins[1]);
		} else {
			System.out.println("After "+totlaGames+" games, "+PlayerNames[1] + " wins the competition! "+NumberOfWins[1]+ " vs. "+NumberOfWins[0]);
		}
	}
}
