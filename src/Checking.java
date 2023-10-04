public class Checking extends Account {

  private static final double INTEREST_RATE = 0.01;
  private static final double MONTHLY_FEE = 12.00;

  /**
   * Get the INTEREST_RATE for a Checking account.
   * @return INTEREST_RATE constant.
   */
  @Override
  public double monthlyInterest(){
    return INTEREST_RATE;
  }

  /**
   * Get the MONTHLY_FEE for a Checking account.
   * @return MONTHLY_FEE constant if not a loyal customer, 0.0 otherwise.
   */
  @Override
  public double monthlyFee(){
    int accountBalanceWaiver = 1000;
    double waivedFee = 0.0;
    if (balance >= accountBalanceWaiver) {
      return waivedFee;
    }
    return MONTHLY_FEE;
  }
  
}
