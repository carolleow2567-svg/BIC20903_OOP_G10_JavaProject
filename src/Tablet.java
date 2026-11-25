public class Tablet extends Equipment {

    // Private attributes (Encapsulation)
    private double screenSize;
    private int storageCapacity;
    private boolean stylusSupport;

    // Constructor
    public Tablet(String equipmentID, String name, String brand,
                  String condition, String status, double estimatedWeightKg,
                  double screenSize, int storageCapacity, boolean stylusSupport) {

        super(equipmentID, name, brand, condition, status, estimatedWeightKg);

        setScreenSize(screenSize);
        setStorageCapacity(storageCapacity);
        setStylusSupport(stylusSupport);
    }

    // Getter methods
    public double getScreenSize() {
        return screenSize;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public boolean getStylusSupport() {
        return stylusSupport;
    }

    // Setter methods with validation
    public void setScreenSize(double screenSize) {
        if (screenSize > 0) {
            this.screenSize = screenSize;
        } else {
            throw new IllegalArgumentException(
                "Screen size must be greater than 0!");
        }
    }

    public void setStorageCapacity(int storageCapacity) {
        if (storageCapacity > 0) {
            this.storageCapacity = storageCapacity;
        } else {
            throw new IllegalArgumentException(
                "Storage capacity must be greater than 0!");
        }
    }

    public void setStylusSupport(boolean stylusSupport) {
        this.stylusSupport = stylusSupport;
    }

    // Method Overriding (Polymorphism)
    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Screen Size: " + screenSize + " inches");
        System.out.println("Storage Capacity: " + storageCapacity + " GB");
        System.out.println("Stylus Support: " +
                           (stylusSupport ? "Yes" : "No"));
    }
}
