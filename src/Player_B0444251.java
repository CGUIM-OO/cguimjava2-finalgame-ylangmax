import java.util.ArrayList;

public class Player_B0444251 extends Player {
	
	
	
    private  int  calculate8 =0;
    private  int  calculate5 =0;
    private  int  calculate3=0;//記牌的數字
 
	
	 int nDeck;//有几副牌
	 int curcle =1;//makebet所用变数


	
	public Player_B0444251( int chips) {
		super("Tianshuo B0444251", chips);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int make_bet() {//採用122下注策略
		if (curcle ==1){
			setBet(100);
			curcle ++;
		}
		if(curcle ==2)
		{
			setBet(200);
			curcle ++;
		}
		if(curcle ==3)
		{
			setBet(200);
			curcle -=2;//无意外情况三局一循环
		}
		if (getBet() <= get_current_chips()) {//输掉则从头开始
			return getBet();
		} else {
			setBet(0);
			return getBet();
		}
	

		// TODO Auto-generated method stub
	}

	@Override
	public boolean hit_me(Table table) {
		boolean anshan =false;
		nDeck=table.getNumberOfDeck();//提取套牌数量
		ArrayList<Card> openCard;
		openCard =table.getOpenedCards();
		for(Card c: openCard)//场面所有明牌进行运算
		{
			if(c.getRank()>=9)
				calculate8 --;
			else if(c.getRank()<=8 &&c.getRank()>=2)
				calculate8 ++;
			if(c.getRank()>=6)
				calculate5 --;
			else if(c.getRank()<=5 &&c.getRank()>=2)
				calculate5 ++;
			if(c.getRank()>=4)
				calculate3 --;
			else if(c.getRank()<=3 &&c.getRank()>=2)
				calculate3 ++;
		}
		if(table.getPercentofUsedCard()>0.5)//策略在50%牌发出后开始执行，因为这时caculate开始变得准确
		{
		
		
		int [] a=table.get_palyers_bet();
		
		int TotalValue = getTotalValue();
		
		if(TotalValue <=11)//無論何時11以下都要牌
			{
			anshan = true;
			}
	     if(TotalValue <= 14 &&TotalValue>=12)
		{
			anshan = situationbig(calculate8);
		}
	     if(TotalValue <= 16 &&TotalValue>=15)
			{
				anshan = situationmid(calculate5);
			}
		
		
		 if(TotalValue <= 18 &&TotalValue>=17)
		{
			anshan = situationsmall(calculate3);
			
		}if(TotalValue >18)
		{
			anshan = false;
		}
		}
		else{
			int total_value = getTotalValue();

			if (total_value >= 17) {
				anshan = false;
			} else {
				anshan = true;
			}

			
		}
		return anshan;
		
			 
			
			
			
			
					
			
		
		
		
		// TODO Auto-generated method stub

	}
	public  boolean situationbig(int caculation)//x需要大牌时评估
	{
		if (caculation <-5*nDeck)
			return false;
		else 
			return true;
			
		
	}
	public  boolean situationmid(int caculation)//需要中等牌时评估
	{
		if (caculation <-5*nDeck)
			return false;
		else 
			return true;
			
		
	}
	
	public  boolean situationsmall(int caculation)//需要小牌时评估
	{
		if (caculation <-5*nDeck)
			return false;
		else 
			return true;
			
		
	}

	

}
