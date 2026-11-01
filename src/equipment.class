
public class Equipment {

    // Private attributes (Encapsulation)
    private String equipmentID;
    private String name;
    private String brand;
    private String condition;
    private String status;
    private double estimatedWeightKg;

    // Constructor
    public Equipment(String equipmentID, String name, String brand,
                     String condition, String status, double estimatedWeightKg) {

        this.equipmentID = equipmentID;
        this.name = name;
        this.brand = brand;
        this.condition = condition;
        this.status = status;
        this.estimatedWeightKg = estimatedWeightKg;
    }

    // Getter methods
    public String getEquipmentID() {
        return equipmentID;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getCondition() {
        return condition;
    }

    public String getStatus() {
        return status;
    }

    public double getEstimatedWeightKg() {
        return estimatedWeightKg;
    }

    // Setter methods
    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Setter method with validation
    public void setEstimatedWeightKg(double estimatedWeightKg) {
    if (estimatedWeightKg >= 0) {
        this.estimatedWeightKg = estimatedWeightKg;
    } else {
        System.out.println("Weight cannot be negative!");
    }
}

    // Display method
    public void displayDetails() {
        System.out.println("Equipment ID: " + equipmentID);
        System.out.println("Name: " + name);
        System.out.println("Brand: " + brand);
        System.out.println("Condition: " + condition);
        System.out.println("Status: " + status);
        System.out.println("Estimated Weight: " + estimatedWeightKg + " kg");
    }
}
