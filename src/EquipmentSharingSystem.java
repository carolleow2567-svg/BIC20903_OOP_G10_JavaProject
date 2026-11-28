import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class EquipmentSharingSystem {

    private ArrayList<Equipment> equipmentList = new ArrayList<>();
    private ArrayList<Member> memberList = new ArrayList<>();
    private ArrayList<SharingRecord> sharingRecords = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        EquipmentSharingSystem system = new EquipmentSharingSystem();
        system.loadData();
        system.runMenu();
    }

    // MAIN MENU
    public void runMenu() {
        String choice;

        do {
            System.out.println("\n===== EQUIPMENT SHARING SYSTEM =====");
            System.out.println("1. Equipment Management");
            System.out.println("2. Member Management");
            System.out.println("3. Borrow Equipment");
            System.out.println("4. Return Equipment");
            System.out.println("5. View Sharing Records");
            System.out.println("6. Search Records");
            System.out.println("7. Environmental Summary");
            System.out.println("8. Save Data");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            choice = input.nextLine();

            switch (choice) {
                case "1": equipmentMenu(); break;
                case "2": memberMenu(); break;
                case "3": borrowEquipment(); break;
                case "4": returnEquipment(); break;
                case "5": displayRecords(); break;
                case "6": searchRecords(); break;
                case "7": environmentalSummary(); break;
                case "8": saveData(); break;
                case "0": saveData(); break;
                default: System.out.println("Invalid choice!");
            }
        } while (!choice.equals("0"));
    }

    // EQUIPMENT MENU
    public void equipmentMenu() {
        String choice;

        do {
            System.out.println("\n===== EQUIPMENT MENU =====");
            System.out.println("1. Add");
            System.out.println("2. Display");
            System.out.println("3. Search");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            choice = input.nextLine();

            switch (choice) {
                case "1": addEquipment(); break;
                case "2": displayEquipment(); break;
                case "3": searchEquipment(); break;
                case "4": updateEquipment(); break;
                case "5": deleteEquipment(); break;
            }
        } while (!choice.equals("0"));
    }

    private Equipment findEquipment(String id) {
        for (Equipment e : equipmentList)
            if (e.getEquipmentID().equalsIgnoreCase(id))
                return e;
        return null;
    }

    public void addEquipment() {
        try {
            System.out.println("1. Laptop  2. Tablet");
            System.out.print("Type: ");
            String type = input.nextLine();

            System.out.print("Equipment ID: ");
            String id = input.nextLine();

            if (findEquipment(id) != null || id.trim().isEmpty()) {
                System.out.println("Invalid or duplicate ID!");
                return;
            }

            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Brand: ");
            String brand = input.nextLine();
            System.out.print("Condition: ");
            String condition = input.nextLine();
            System.out.print("Weight (kg): ");
            double weight = Double.parseDouble(input.nextLine());

            Equipment e;

            if (type.equals("1")) {
                System.out.print("RAM (GB): ");
                int ram = Integer.parseInt(input.nextLine());
                System.out.print("Storage (GB): ");
                int storage = Integer.parseInt(input.nextLine());

                e = new Laptop(id, name, brand, condition,
                        "AVAILABLE", weight, ram, storage);

            } else if (type.equals("2")) {
                System.out.print("Screen size: ");
                double size = Double.parseDouble(input.nextLine());
                System.out.print("Storage (GB): ");
                int storage = Integer.parseInt(input.nextLine());
                System.out.print("Stylus support (true/false): ");
                boolean stylus = Boolean.parseBoolean(input.nextLine());

                e = new Tablet(id, name, brand, condition,
                        "AVAILABLE", weight, size, storage, stylus);
            } else {
                System.out.println("Invalid type!");
                return;
            }

            equipmentList.add(e);
            System.out.println("Equipment added!");

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    public void displayEquipment() {
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment available.");
            return;
        }

        for (Equipment e : equipmentList) {
            e.displayDetails();
            System.out.println("----------------");
        }
    }

    public void searchEquipment() {
        System.out.print("Equipment ID: ");
        Equipment e = findEquipment(input.nextLine());

        if (e == null)
            System.out.println("Equipment not found.");
        else
            e.displayDetails();
    }

    public void updateEquipment() {
        System.out.print("Equipment ID: ");
        Equipment e = findEquipment(input.nextLine());

        if (e == null) {
            System.out.println("Equipment not found.");
            return;
        }

        if (e.getStatus().equalsIgnoreCase("BORROWED")) {
            System.out.println("Cannot update borrowed equipment.");
            return;
        }

        try {
            System.out.print("New name: ");
            String name = input.nextLine();
            System.out.print("New brand: ");
            String brand = input.nextLine();
            System.out.print("New condition: ");
            String condition = input.nextLine();
            System.out.print("New weight: ");
            double weight = Double.parseDouble(input.nextLine());

            if (name.trim().isEmpty() || brand.trim().isEmpty()
                    || condition.trim().isEmpty() || weight <= 0) {
                System.out.println("Invalid information!");
                return;
            }

            e.setName(name);
            e.setBrand(brand);
            e.setCondition(condition);
            e.setEstimatedWeightKg(weight);
            System.out.println("Equipment updated!");

        } catch (NumberFormatException ex) {
            System.out.println("Invalid weight!");
        }
    }

    public void deleteEquipment() {
        System.out.print("Equipment ID: ");
        Equipment e = findEquipment(input.nextLine());

        if (e == null) {
            System.out.println("Equipment not found.");
            return;
        }

        if (e.getStatus().equalsIgnoreCase("BORROWED")) {
            System.out.println("Cannot delete borrowed equipment.");
            return;
        }

        equipmentList.remove(e);
        System.out.println("Equipment deleted!");
    }

    // MEMBER MENU
    public void memberMenu() {
        String choice;

        do {
            System.out.println("\n===== MEMBER MENU =====");
            System.out.println("1. Add Member");
            System.out.println("2. Display Members");
            System.out.println("3. Search Member");
            System.out.println("4. Update Member");
            System.out.println("5. Delete Member");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            choice = input.nextLine();

            switch (choice) {
                case "1": addMember(); break;
                case "2": displayMembers(); break;
                case "3": searchMember(); break;
                case "4": updateMember(); break;
                case "5": deleteMember(); break;
            }
        } while (!choice.equals("0"));
    }

    private Member findMember(String id) {
        for (Member m : memberList)
            if (m.getMemberID().equalsIgnoreCase(id))
                return m;
        return null;
    }

    public void addMember() {
        try {
            System.out.print("Member ID: ");
            String id = input.nextLine();

            if (id.trim().isEmpty() || findMember(id) != null) {
                System.out.println("Invalid or duplicate ID!");
                return;
            }

            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Contact: ");
            String contact = input.nextLine();
            System.out.print("Email: ");
            String email = input.nextLine();

            memberList.add(new Member(id, name, contact, email));
            System.out.println("Member added!");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayMembers() {
        if (memberList.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        for (Member m : memberList)
            m.displayDetails();
    }

    public void searchMember() {
        System.out.print("Member ID: ");
        Member m = findMember(input.nextLine());

        if (m == null)
            System.out.println("Member not found.");
        else
            m.displayDetails();
    }

    public void updateMember() {
        System.out.print("Member ID: ");
        Member m = findMember(input.nextLine());

        if (m == null) {
            System.out.println("Member not found.");
            return;
        }

        try {
            System.out.print("New name: ");
            m.setName(input.nextLine());
            System.out.print("New contact: ");
            m.setContactNumber(input.nextLine());
            System.out.print("New email: ");
            m.setEmail(input.nextLine());

            System.out.println("Member updated!");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteMember() {
        System.out.print("Member ID: ");
        Member m = findMember(input.nextLine());

        if (m == null) {
            System.out.println("Member not found.");
            return;
        }

        for (SharingRecord r : sharingRecords) {
            if (r.getMemberID().equalsIgnoreCase(m.getMemberID())
                    && r.getReturnDate() == null) {
                System.out.println("Member has an active borrowing record.");
                return;
            }
        }

        memberList.remove(m);
        System.out.println("Member deleted!");
    }

    // BORROW EQUIPMENT
    public void borrowEquipment() {
        System.out.print("Member ID: ");
        String memberID = input.nextLine();

        if (findMember(memberID) == null) {
            System.out.println("Member not found!");
            return;
        }

        System.out.print("Equipment ID: ");
        String equipmentID = input.nextLine();
        Equipment e = findEquipment(equipmentID);

        if (e == null || !e.getStatus().equalsIgnoreCase("AVAILABLE")) {
            System.out.println("Equipment unavailable!");
            return;
        }

        System.out.print("Record ID: ");
        String recordID = input.nextLine();

        for (SharingRecord r : sharingRecords) {
            if (r.getRecordID().equalsIgnoreCase(recordID)) {
                System.out.println("Record ID already exists!");
                return;
            }
        }

        try {
            SharingRecord record = new SharingRecord(
                    recordID, memberID, equipmentID, LocalDate.now());

            sharingRecords.add(record);
            e.setStatus("BORROWED");
            System.out.println("Equipment borrowed successfully!");

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // RETURN EQUIPMENT
    public void returnEquipment() {
        System.out.print("Record ID: ");
        String id = input.nextLine();

        for (SharingRecord r : sharingRecords) {
            if (r.getRecordID().equalsIgnoreCase(id)) {
                if (r.getReturnDate() != null) {
                    System.out.println("Already returned!");
                    return;
                }

                r.returnEquipment();

                Equipment e = findEquipment(r.getEquipmentID());
                if (e != null)
                    e.setStatus("AVAILABLE");

                System.out.println("Equipment returned!");
                return;
            }
        }

        System.out.println("Record not found!");
    }

    // SHARING RECORDS
    public void displayRecords() {
        if (sharingRecords.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (SharingRecord r : sharingRecords)
            r.displayDetails();
    }

    public void searchRecords() {
        System.out.print("Enter Record, Member or Equipment ID: ");
        String keyword = input.nextLine();
        boolean found = false;

        for (SharingRecord r : sharingRecords) {
            if (r.getRecordID().equalsIgnoreCase(keyword)
                    || r.getMemberID().equalsIgnoreCase(keyword)
                    || r.getEquipmentID().equalsIgnoreCase(keyword)) {
                r.displayDetails();
                found = true;
            }
        }

        if (!found)
            System.out.println("No matching records.");
    }

    // ENVIRONMENTAL SUMMARY
    public void environmentalSummary() {
        int reused = 0;
        double totalWeight = 0;

        for (Equipment e : equipmentList) {
            totalWeight += e.getEstimatedWeightKg();

            for (SharingRecord r : sharingRecords) {
                if (r.getEquipmentID().equalsIgnoreCase(e.getEquipmentID())
                        && r.getReturnDate() != null) {
                    reused++;
                    break;
                }
            }
        }

        double rate = equipmentList.isEmpty() ? 0
                : (double) reused / equipmentList.size() * 100;

        System.out.println("\n===== ENVIRONMENTAL SUMMARY =====");
        System.out.println("Total Equipment: " + equipmentList.size());
        System.out.println("Reused Equipment: " + reused);
        System.out.printf("Reuse Rate: %.2f%%\n", rate);
        System.out.printf("Total Weight: %.2f kg\n", totalWeight);
    }

    // SAVE DATA
    public void saveData() {
        try {
            PrintWriter eq = new PrintWriter("equipment.txt");
            for (Equipment e : equipmentList) {
                if (e instanceof Laptop) {
                    Laptop l = (Laptop) e;
                    eq.println("Laptop;" + e.getEquipmentID() + ";"
                            + e.getName() + ";" + e.getBrand() + ";"
                            + e.getCondition() + ";" + e.getStatus() + ";"
                            + e.getEstimatedWeightKg() + ";"
                            + l.getRam() + ";" + l.getStorageCapacity());

                } else if (e instanceof Tablet) {
                    Tablet t = (Tablet) e;
                    eq.println("Tablet;" + e.getEquipmentID() + ";"
                            + e.getName() + ";" + e.getBrand() + ";"
                            + e.getCondition() + ";" + e.getStatus() + ";"
                            + e.getEstimatedWeightKg() + ";"
                            + t.getScreenSize() + ";" + t.getStorageCapacity()
                            + ";" + t.getStylusSupport());
                }
            }
            eq.close();

            PrintWriter mem = new PrintWriter("members.txt");
            for (Member m : memberList)
                mem.println(m.getMemberID() + ";" + m.getName() + ";"
                        + m.getContactNumber() + ";" + m.getEmail());
            mem.close();

            PrintWriter rec = new PrintWriter("records.txt");
            for (SharingRecord r : sharingRecords)
                rec.println(r.getRecordID() + ";" + r.getMemberID() + ";"
                        + r.getEquipmentID() + ";" + r.getBorrowDate() + ";"
                        + (r.getReturnDate() == null ? "null" : r.getReturnDate()));
            rec.close();

            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // LOAD DATA
    public void loadData() {
        try {
            File eq = new File("equipment.txt");

            if (eq.exists()) {
                Scanner reader = new Scanner(eq);

                while (reader.hasNextLine()) {
                    String[] d = reader.nextLine().split(";", -1);

                    if (d.length == 9 && d[0].equals("Laptop")) {
                        equipmentList.add(new Laptop(d[1], d[2], d[3],
                                d[4], d[5], Double.parseDouble(d[6]),
                                Integer.parseInt(d[7]), Integer.parseInt(d[8])));

                    } else if (d.length == 10 && d[0].equals("Tablet")) {
                        equipmentList.add(new Tablet(d[1], d[2], d[3],
                                d[4], d[5], Double.parseDouble(d[6]),
                                Double.parseDouble(d[7]), Integer.parseInt(d[8]),
                                Boolean.parseBoolean(d[9])));
                    }
                }
                reader.close();
            }

            File mem = new File("members.txt");

            if (mem.exists()) {
                Scanner reader = new Scanner(mem);

                while (reader.hasNextLine()) {
                    String[] d = reader.nextLine().split(";", -1);

                    if (d.length == 4)
                        memberList.add(new Member(d[0], d[1], d[2], d[3]));
                }
                reader.close();
            }

            File rec = new File("records.txt");

            if (rec.exists()) {
                Scanner reader = new Scanner(rec);

                while (reader.hasNextLine()) {
                    String[] d = reader.nextLine().split(";", -1);

                    if (d.length == 5) {
                        SharingRecord r = new SharingRecord(
                                d[0], d[1], d[2], LocalDate.parse(d[3]));

                        if (!d[4].equals("null"))
                            r.returnEquipment();

                        sharingRecords.add(r);
                    }
                }
                reader.close();
            }

            for (Equipment e : equipmentList) {
                for (SharingRecord r : sharingRecords) {
                    if (r.getEquipmentID().equalsIgnoreCase(e.getEquipmentID())
                            && r.getReturnDate() == null) {
                        e.setStatus("BORROWED");
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
