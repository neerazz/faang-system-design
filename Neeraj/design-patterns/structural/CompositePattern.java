package structural;

import java.util.*;
import java.io.*;

/**
 * @implNote <br>
 * Assume that you have an Employee object, that has below fields:<br>
 * • name, <br>
 * • dept and <br>
 * • Subordinates, list of Employees (that reports to the current emp).<br>
 * ○ If there are no any employees (leaf), then the list will be empty.<br>
 * With this pattern any change, like adding or deleting can be done on that level of employee object.<br>
 */
public class CompositePattern {

    public static void main(String[] args) {
        Employee CEO = new Employee("John", "CEO");

        Employee headSales = new Employee("Robert", "Head Sales");

        Employee headMarketing = new Employee("Michel", "Head Marketing");

        Employee clerk1 = new Employee("Laura", "Marketing");
        Employee clerk2 = new Employee("Bob", "Marketing");

        Employee salesExecutive1 = new Employee("Richard", "Sales");
        Employee salesExecutive2 = new Employee("Rob", "Sales");

        CEO.addEmployee(headSales);
        CEO.addEmployee(headMarketing);

        headSales.addEmployee(salesExecutive1);
        headSales.addEmployee(salesExecutive2);

        headMarketing.addEmployee(clerk1);
        headMarketing.addEmployee(clerk2);

        //print all employees of the organization
        System.out.println(CEO);

        for (Employee headEmployee : CEO.subordinates) {
            System.out.println(headEmployee);

            for (Employee employee : headEmployee.subordinates) {
                System.out.println(employee);
            }
        }
    }
}

class Employee {
    String name, dept;
    List<Employee> subordinates;

    public Employee(String name, String dept) {
        this.name = name;
        this.dept = dept;
        subordinates = new ArrayList<>();
    }

    public void addEmployee(Employee newEmployee) {
        subordinates.add(newEmployee);
    }

    public void deleteEmployee(Employee newEmployee) {
        subordinates.remove(newEmployee);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", subordinates=" + subordinates +
                '}';
    }
}