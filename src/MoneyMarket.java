public class MoneyMarket extends Savings {

    private static final double INTEREST_RATE = 0.045;
    private static final double MONTHLY_FEE = 25.00;

    private int withdrawal; //number of withdrawals

    /**
     * Constructor for Money Market object.
     * @param holder Profile object of account holder.
     * @param balance integer for account.
     * @param isLoyal boolean for loyal customer status.
     * @param withdrawal integer for number of withdrawals.
     */
    public MoneyMarket(Profile holder, int balance, boolean isLoyal, int withdrawal){
        super(holder, balance, isLoyal);
        this.withdrawal = withdrawal;

    }
    /**
     * Get the INTEREST_RATE for a MoneyMarket account.
     * @return INTEREST_RATE constant if not a loyal customer, 0.0475 otherwise.
     */
    @Override
    public double monthlyInterest(){
        double loyalRate = 0.0475;
        if (isLoyal) {
            return loyalRate;
        }
        return INTEREST_RATE;
    }

    /**
     * Get the MONTHLY_FEE for a MoneyMarket account.
     * @return 0.0 if balance > 2000, 25.0 otherwise, and add 10.0 if withdrawals > 3.
     */
    @Override
    public double monthlyFee(){
        int withdrawalFeeLimit = 3;
        double withdrawalFee = 10.0;

        int feeWaiverLimit = 2000;
        double feeWaiver = 0.0;

        if (balance >= feeWaiverLimit) {
            if (withdrawal > withdrawalFeeLimit) {
                return feeWaiver + withdrawalFee;
            }
            return feeWaiver;
        }
        else {
            if (withdrawal > withdrawalFeeLimit) {
                return withdrawalFee + MONTHLY_FEE;
            }
            return MONTHLY_FEE;
        }

    }

    @Override
    public String accountType(){
        return "MONEY_MARKET";
    }

    //will be revised
    @Override
    public boolean equals(Object obj){
        if(obj instanceof MoneyMarket){
            MoneyMarket account = (MoneyMarket)obj;
            if(compareTo(account) == 0) {
                return true;
            }
        }
        return false;
    }
}
