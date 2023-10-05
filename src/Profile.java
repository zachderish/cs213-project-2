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
     * compareTo method for Profile objects by comparing last name, first name and date of birth.
     * @param profile the object to be compared.
     * @return 1 if this.profile is greater, -1 if this.profile is less than profile, 0 otherwise.
     */
    public int compareTo(Profile profile) {
        if (this.lname.compareTo(profile.lname) > 0) {
            return 1;
        }
        if (this.lname.compareTo(profile.lname) < 0) {
            return -1;
        }
        if (this.fname.compareTo(profile.fname) > 0) {
            return 1;
        }
        if (this.fname.compareTo(profile.fname) < 0) {
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

}
