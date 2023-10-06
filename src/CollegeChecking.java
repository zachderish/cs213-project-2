public class CollegeChecking extends Checking {

    private static final double MONTHLY_FEE = 0.00;
    private static final double INTEREST_RATE = 0.01;
    private Campus campus; //campus code

    /**
     * Constructor for College Checking object.
     * @param holder Profile object of account holder.
     * @param balance integer for account.
     * @param campus Campus code.
     */
    public CollegeChecking(Profile holder, int balance, Campus campus) {
        super(holder, balance);
        this.campus = campus;
    }

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

    @Override
    public String accountType(){
        return "COLLEGE_CHECKING";
    }

    //will be revised
    @Override
    public boolean equals(Object obj){
        if(obj instanceof CollegeChecking){
            CollegeChecking account = (CollegeChecking)obj;
            if(compareTo(account) == 0) {
                return true;
            }
        }
        return false;
    }
}
