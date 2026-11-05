// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.time.LocalDate;

public class SharingRecord {
    // Attributes
    private String recordID;
    private String memberID;
    private String equipmentID;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;

    //Constuctor
    public SharingRecord(String recordID, String memberID, String equipmentID, LocalDate borrowDate) {
        setRecordID(recordID);
        setMemberID(memberID);
        setEquipmentID(equipmentID);
        setBorrowDate(borrowDate);

        this.returnDate = null;
        this.status = "BORROWED";
    }

    // Accessors (Getters)
    public String getRecordID() {
        return recordID;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public String getStatus() {
        return status;
    }

    // Mutators (Setters)
    public void setRecordID(String recordID) {
        // Validation: Record ID cannot be empty
        if(recordID == null || recordID.trim().isEmpty()) {
            throw new IllegalArgumentException("Record ID cannot be empty.");
        }
        this.recordID = recordID;
    }
    
    public void setMemberID(String memberID) {
        // Validation: Member ID cannot be empty
        if(memberID == null || memberID.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }
        this.memberID = memberID;
    }

    public void setEquipmentID(String equipmentID) {
        // Validation: Equipment ID cannot be empty
        if(equipmentID == null || equipmentID.trim().isEmpty()) {
            throw new IllegalArgumentException("Equipment ID cannot be empty.");
        }
        this.equipmentID = equipmentID;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        // Validation: Borrow date cannot be empty
        if(borrowDate == null) {
            throw new IllegalArgumentException("Borrow date cannot be empty.");
        }
        // Validation: Borrow date cannot be in the future
        if(borrowDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Borrow date cannot be in the future.");
        }
        this.borrowDate = borrowDate;
    }

    // Return Equipment
    public void returnEquipment() {
        // Validation: Equipment can only be returned if it is currently borrowed
        if("RETURNED".equals(status)) {
            throw new IllegalStateException("Equipment has already been returned.");
        }
        this.returnDate = LocalDate.now();
        this.status = "RETURNED";
    }

    // Display transaction details
    public void displayDetails() {
        System.out.println("--------------------------------");
        System.out.println("\t\tSHARING RECORD DETAILS");
        System.out.println("--------------------------------");
        System.out.println("Record ID       : " + recordID);
        System.out.println("Member ID       : " + memberID);
        System.out.println("Equipment ID    : " + equipmentID);
        System.out.println("Borrow Date     : " + borrowDate);
        System.out.println("Return Date     : " + (returnDate != null ? "Not returned yet" : returnDate));
        System.out.println("Status          : " + status);
        System.out.println("--------------------------------");
    }
}