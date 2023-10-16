/**
 * Define profile object/class
 * @author Kenrick Eagar, Zachary Derish
 */

public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    /**
    * Profile Constructor
    *@param fname, profile first name
    *@param lname, profile last name
    *@param dob date object representing date of birth
    */
    public Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     *Getter method to retrieve first name of a profile
     * @return String representation of first name
     */
    public String getFname(){
        return this.fname;
    }

    /**
     *Getter method to retrieve last name of a profile
     * @return String representation of last name
     */
    public String getLname(){
        return this.lname;
    }

    /**
     *Getter method to retrieve date of birth of a profile
     * @return Date object representing date of birth of profile
     */
    public Date getDob(){
        return this.dob;
    }

    /**
     * compareTo method for Profile objects by comparing last name, first name and date of birth.
     * @param profile the object to be compared.
     * @return 1 if this.profile is greater, -1 if this.profile is less than profile, 0 otherwise.
     */
    public int compareTo(Profile profile) {
        if (this.lname.compareToIgnoreCase(profile.lname) > 0) {
            return 1;
        }
        if (this.lname.compareToIgnoreCase(profile.lname) < 0) {
            return -1;
        }
        if (this.fname.compareToIgnoreCase(profile.fname) > 0) {
            return 1;
        }
        if (this.fname.compareToIgnoreCase(profile.fname) < 0) {
            return -1;
        }
        if (this.dob.compareTo(profile.dob) > 0) {
            return 1;
        }
        if (this.dob.compareTo(profile.dob) < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }

    /**
     * Compares two profiles to see if they are equal or not
     * @param The profile object we want to compare to
     * @return returns true if profiles are equivalent, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Profile){
            Profile profile = (Profile) obj;
            if (this.compareTo(profile) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * String showing first name, last name, and date of birth of profile
     * @return String representation of profile
     */
    @Override
    public String toString() {
        String name = this.fname + " " + this.lname + " ";
        String dob = this.getDob().toString();
        return name + dob;
    }

    public static void main(String[] args) {
        Date date = new Date("1/22/2002");
        Profile profile1 = new Profile("Zach", "D", date);
        Profile profile2 = new Profile("Zach", "D", date);
        System.out.println(profile1.equals(profile2));
    }


}
