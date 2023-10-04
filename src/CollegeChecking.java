public class CollegeChecking extends Checking {

    private static final double MONTHLY_FEE = 0.00;
    private static final double INTEREST_RATE = 0.01;
    private Campus campus; //campus code

    /**
     * Get INTEREST_RATE for a CollegeChecking account.
     * @return INTEREST_RATE constant.
     */
    @Override
    public double monthlyInterest(){
        return INTEREST_RATE;
    }

    /**
     * Get MONTHLY_FEE for a CollegeChecking account.
     * @return MONTHLY_FEE constant.
     */
    @Override
    public double monthlyFee(){
        return MONTHLY_FEE;
    }
}
