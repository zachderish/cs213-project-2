public class CollegeChecking extends Checking {

    public static final double MONTHLY_FEE = 0.00;
    public static final double INTEREST_RATE = 0.01;
    private Campus campus; //campus code

    @Override
    public double monthlyInterest(){
        return INTEREST_RATE;
    }
    
    @Override
    public double monthlyFee(){
        return MONTHLY_FEE;
    }

    
}
