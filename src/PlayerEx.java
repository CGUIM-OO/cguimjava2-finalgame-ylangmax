
public class PlayerEx extends Player {

	public PlayerEx(int chips) {
		super("Example", chips); //Please modify Example to Your Name (Your ID), for example: "Yi-Ju Tseng (B0000000)"
	}

	@Override
	public int make_bet() {
		setBet(5);
		if (getBet() <= get_current_chips()) {
			return getBet();
		} else {
			setBet(0);
			return getBet();
		}
	}

	@Override
	public boolean hit_me(Table tbl) {
		int total_value = getTotalValue();

		if (total_value >= 17) {
			return false;
		} else {
			return true;
		}

	}

}
