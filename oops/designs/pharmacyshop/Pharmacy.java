package pharmacyshop;

import java.util.*;

public class Pharmacy {

    Map<String, List<Prescription>> prescriptions = new HashMap<>();

    Map<String, Double> cutBalances = new HashMap<>();
    Map<String, Double> prices = new HashMap<>();

    List<Manager> managers = new ArrayList<>();
    List<PharmacistImpl> pharmacists = new ArrayList<>();
    List<Cashier> cashiers = new ArrayList<>();

    public Pharmacy() {
//        By default, create one employ of each type.
        createCashier("abc");
        createManager("def");
        createPharmacist("ghi");
    }

    public List<Prescription> getPrescriptions(String customerName) {
        return prescriptions.get(customerName);
    }

    public List<Prescription> addPrescription(String custName, Prescription pres) {
        prescriptions.putIfAbsent(custName, new ArrayList<>());
        prescriptions.get(custName).add(pres);
        double preBalance = cutBalances.getOrDefault(custName, 0.0);
        double currentPrice = getPrice(pres);
        prices.put(custName, currentPrice + preBalance);
        return prescriptions.get(custName);
    }

    private double getPrice(Prescription pres) {
        double total = 0;
        for (Medicine med : pres.medicines) {
//            Assuming that the medicine is present in the pharmacy. Temporarily setting the defaults to 1.0 per medicine.
            total += prices.getOrDefault(med.name, 1.0);
        }
        return total;
    }

    public Double getBalance(String custName) {
        return cutBalances.get(custName);
    }

    Cashier getCashier(){
        if(cashiers.isEmpty()){
            System.out.println("There are not any staff with Pharmacist Role. Kindly add them first.");
            throw new RuntimeException("There are not any staff with manager Role. Kindly add them first.");
        }else{
            int idx = new Random().nextInt(cashiers.size());
            return cashiers.get(idx);
        }
    }

    PharmacistImpl getPharmacist(){
        if(pharmacists.isEmpty()){
            System.out.println("There are not any staff with Pharmacist Role. Kindly add them first.");
            throw new RuntimeException("There are not any staff with manager Role. Kindly add them first.");
        }else{
            int idx = new Random().nextInt(pharmacists.size());
            return pharmacists.get(idx);
        }
    }

    Manager getManager(){
        if(managers.isEmpty()){
            System.out.println("There are not any staff with manager Role. Kindly add them first.");
            throw new RuntimeException("There are not any staff with manager Role. Kindly add them first.");
        }else{
            int idx = new Random().nextInt(managers.size());
            return managers.get(idx);
        }
    }

    Manager createManager(String name) {
        Manager manager = (Manager) ResourceSupplier.getResource(this, name, Role.MANAGER);
        managers.add(manager);
        return manager;
    }

    PharmacistImpl createPharmacist(String name) {
        PharmacistImpl pharmacist = (PharmacistImpl) ResourceSupplier.getResource(this, name, Role.PHARMACIST);
        pharmacists.add(pharmacist);
        return pharmacist;
    }

    Cashier createCashier(String name) {
        Cashier cashier = (Cashier) ResourceSupplier.getResource(this, name, Role.CASHIER);
        cashiers.add(cashier);
        return cashier;
    }
}

class Employee {
    String name;

    public Employee(String name) {
        this.name = name;
    }
}

interface Cashier {

    default List<Medicine> collectCash(Pharmacy pharmacy, String customerName, Cash cash) {
        Double balance = pharmacy.getBalance(customerName);
        if (balance != null && Objects.equals(cash.amount, balance)) {
            List<Prescription> prescriptions = pharmacy.getPrescriptions(customerName);
            List<Medicine> medicines = new ArrayList<>();
            if (prescriptions != null) {
                prescriptions.stream().map(pres -> pres.medicines).forEach(medicines::addAll);
            }
            return medicines;
        }
        throw new RuntimeException("Kindly provide an exact balance of :" + balance);
    }
}

interface Pharmacist {
    default List<Prescription> writePrescriptions(Pharmacy pharmacy, String custName, String problem) {
        System.out.println("Creating Prescription for customer = " + custName + " for  = " + problem);
        Prescription pres = new Prescription();
        pres.addMedicine("m1", 10);
        return pharmacy.addPrescription(custName, pres);
    }
}

class PharmacistImpl extends Employee implements Pharmacist {
    Pharmacy pharmacy;

    public PharmacistImpl(Pharmacy pharmacy, String name) {
        super(name);
        this.pharmacy = pharmacy;
    }

    public List<Prescription> writePrescriptions(String custName, String problem) {
        System.out.println("Hello " + custName + ",\n This is : " + name);
        return writePrescriptions(pharmacy, custName, problem);
    }
}

class CashierImpl extends Employee implements Cashier {
    Pharmacy pharmacy;

    public CashierImpl(Pharmacy pharmacy, String name) {
        super(name);
        this.pharmacy = pharmacy;
    }

    public List<Medicine> collectCash(String custName, Cash cash) {
        System.out.println("Hello " + custName + ",\n This is : " + name);
        return collectCash(pharmacy, custName, cash);
    }
}

class Manager extends Employee implements Cashier, Pharmacist {
    Pharmacy pharmacy;
    String name;

    public Manager(Pharmacy pharmacy, String name) {
        super(name);
        this.pharmacy = pharmacy;
    }

    public List<Prescription> writePrescriptions(String custName, String problem) {
        System.out.println("Hello " + custName + ",\n This is : " + name);
        return writePrescriptions(pharmacy, custName, problem);
    }

    public List<Medicine> collectCash(String custName, Cash cash) {
        System.out.println("Hello " + custName + ",\n This is : " + name);
        return collectCash(pharmacy, custName, cash);
    }
}

class Prescription {
    List<Medicine> medicines;

    Prescription() {
        medicines = new ArrayList<>();
    }

    void addMedicine(String name, int quantity) {
        medicines.add(new Medicine(name, quantity));
    }
}

class Cash {
    Double amount;
}

class Medicine {
    String name;
    int quantity;

    public Medicine(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

enum Role {
    MANAGER, CASHIER, PHARMACIST
}

class ResourceSupplier {

    public static Employee getResource(Pharmacy pharmacy, String name, Role role) {
        switch (role) {
            case CASHIER:
                return new CashierImpl(pharmacy, name);
            case MANAGER:
                return new Manager(pharmacy, name);
            case PHARMACIST:
                return new PharmacistImpl(pharmacy, name);
        }
        throw new RuntimeException("Enter a valid role. Valid roles are : " + Arrays.toString(Role.values()));
    }
}