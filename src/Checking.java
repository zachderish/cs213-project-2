public class Checking extends Account {

  public static final double INTEREST_RATE = 0.01;
  public static final double MONTHLY_FEE = 12.00;

  
@Override
public double monthlyInterest(){
  return INTEREST_RATE;
}
  
@Override
public double monthlyFee(){
  return MONTHLY_FEE;
}
  
}
