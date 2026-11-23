public class Laptop extends Equipment {

  // Private attributes (Encapsulation)
  private int ram;
  private int storageCapacity;

  // Constructor
  public Laptop(String equipmentID, String name, String brand, String condition, String status, double estimatedWeightKg, int ram, int storageCapacity) {
    
    super(equipmentID, name, brand, condition, status, estimatedWeightKg);

    setRam(ram);
    setStorageCapacity(storageCapacity);
  }

  // Getter methods
  public int getRam() {
    return ram;
  }

  public int getStorageCapacity() {
    return storageCapacity;
  }

  // Setter methods with validation
  public void setRam(int ram) {
    if (ram > 0) {
      this.ram = ram;
    } else {
      throw new IllegalArgumentExpection("RAM must be greater than 0!");
    }
  }

  public void setStorageCapacity(int storageCapacity) {
    if (storageCapacity > 0) {
      this.storageCapacity = storageCapacity;
    } else {
        throw new IllegalArgumentExpection("Storage capacity must be greater than 0!");
    }
  }

  // Method Overriding (Polymorphism)
  @Override
  public void displayDetails() {

    super.displayDetails();

    System.out.println("RAM: " + ram + " GB");
    System.out.println("Storage Capacity: " + storageCapacity + " GB");
  }
}
