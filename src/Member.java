public class Member {

    // Private attributes (Encapsulation)
    private String memberID;
    private String name;
    private String contactNumber;
    private String email;

    // Constructor
    public Member(String memberID, String name, String contactNumber, String email) {
        setMemberID(memberID);
        setName(name);
        setContactNumber(contactNumber);
        setEmail(email);
    }

    // Accessors (Getters)
    public String getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    // Mutators (Setters) with basic validation
    public void setMemberID(String memberID) {
        if (memberID == null || memberID.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }
        this.memberID = memberID.trim();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty.");
        }
        this.name = name.trim();
    }

    public void setContactNumber(String contactNumber) {
        if (contactNumber == null || contactNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact number cannot be empty.");
        }

        String cleanedNumber = contactNumber.trim();
        // Allow common international phone-number characters.
        if (!cleanedNumber.matches("[+0-9()\\-\\s]+")) {
            throw new IllegalArgumentException("Contact number contains invalid characters.");
        }

        // Check the number of digits, ignoring spaces and formatting characters.
        String digitsOnly = cleanedNumber.replaceAll("[^0-9]", "");
        if (digitsOnly.length() < 7 || digitsOnly.length() > 15) {
            throw new IllegalArgumentException("Contact number must contain 7 to 15 digits.");
        }

        this.contactNumber = cleanedNumber;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        String cleanedEmail = email.trim();
        // Basic format check; this does not verify that the address exists.
        if (!cleanedEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Please enter a valid email address.");
        }

        this.email = cleanedEmail;
    }

    // Display member information
    public void displayDetails() {
        System.out.println("--------------------------------");
        System.out.println("\t\tMEMBER DETAILS");
        System.out.println("--------------------------------");
        System.out.println("Member ID       : " + memberID);
        System.out.println("Name            : " + name);
        System.out.println("Contact Number  : " + contactNumber);
        System.out.println("Email           : " + email);
        System.out.println("--------------------------------");
    }

    // Alias for callers that prefer the more specific method name.
    public void displayMember() {
        displayDetails();
    }
}
