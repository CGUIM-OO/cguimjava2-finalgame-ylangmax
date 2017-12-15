public abstract class Player extends Person {
	private String name;
	private int chips;
	private int bet = 0;
	public Player(String name, int chips) {
		this.chips = chips;
		this.name = name;
	}
	public int getBet(){
		return bet;
	}
	public void setBet(int _bet){
		bet=_bet;
	}
	public abstract int make_bet();

	public int get_current_chips() {
		return chips;
	}

	public void increase_chips(int diff) {
		chips += diff;
	}

	public void say_hello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}

	public String get_name() {
		return name;
	}

	

}
