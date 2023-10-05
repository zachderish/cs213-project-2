public class Savings extends Account {

    private static final double INTEREST_RATE = 0.04;
    private static final double MONTHLY_FEE = 25.00;

    protected boolean isLoyal; //loyal customer status
    public Savings(Profile holder,double balance, boolean isLoyal){
        super(holder, balance);
        this.isLoyal = loyal;
    }
    /**
     * Get the INTEREST_RATE for a Savings account.
     * @return INTEREST_RATE constant if not a loyal customer, 0.0425 otherwise.
     */
    @Override
    public double monthlyInterest(){
        double loyalRate = 0.0425;
        if (isLoyal) {
            return loyalRate;
        }
        return INTEREST_RATE;
    }

    /**
     * Get the MONTHLY_FEE for a Savings account.
     * @return MONTHLY_FEE constant if balance < 500, 0.0 otherwise.
     */
    @Override
    public double monthlyFee(){
        int feeWaivedLimit = 500;
        double waivedFee = 0.0;

        if (balance >= feeWaivedLimit) {
            return waivedFee;
        }
        return MONTHLY_FEE;
    }
}
