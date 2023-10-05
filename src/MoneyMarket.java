public class MoneyMarket extends Savings {

    private static final double INTEREST_RATE = 0.045;
    private static final double MONTHLY_FEE = 25.00;

    private int withdrawal; //number of withdrawals

    public MoneyMarket(){
        super();

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
}
