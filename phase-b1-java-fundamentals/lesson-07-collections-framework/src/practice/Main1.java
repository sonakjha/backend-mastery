import java.util.*;

class Employee {
    int id;
    String name;
    Double salary;

    Employee(int id, String name, Double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Salary: " + salary;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Employee)) return false;

        Employee emp=(Employee) obj;
        return this.id == emp.id;
    }
}

public class Main1 {

    public static void main(String[] args) {
        
        ArrayList<Employee> employees = new ArrayList<>();

        //add(value)
        employees.add(new Employee(101, "John", 50000.0));
        employees.add(new Employee(102, "Jane", 60000.0));
        employees.add(new Employee(103, "Mike", 55000.0));

        System.out.println("1. Initial Employees:");
        System.out.println(employees);

        //add(index,value)
        employees.add(2, new Employee(104, "Alice", 70000.0));

        System.out.println("\n2. After inserting at index 2:");
        System.out.println(employees);

        //get(index)
        Employee emp = employees.get(1);
        System.out.println("\n3. Employee at index 1:");
        System.out.println(emp);

        //set(index,value)
        employees.set(1, new Employee(107, "Vikas", 42000.0));
        System.out.println("\n4. After updating index 1:");
        System.out.println(employees);

        //remove(index)
        employees.remove(2);

        System.out.println("\n5. After removing index 2:");
        System.out.println(employees);

        //remove(value)
        employees.remove(new Employee(101, "John", 50000.0));
        System.out.println("\n6. After removing employee with ID 101:");
        System.out.println(employees);

        //contains(value)
        boolean exists = employees.contains(new Employee(103, "Priya", 40000.0));
        System.out.println("\n7. Does employee with ID 103 exist? " + exists);

        //size()
        System.out.println("\n8. Total number of employees: " + employees.size());

        //isEmpty()
        System.out.println("\n9. Is the employee list empty? " + employees.isEmpty());

        //indexOf(value)
        int firstIndex = employees.indexOf(new Employee(103, "Mike", 55000.0));

        //lastIndexOf(value)
        employees.add(new Employee(101, "Rahul", 30000.0));
        int lastIndex = employees.lastIndexOf(
            new Employee(101, "Rahul", 30000.0)
        );

        System.out.println("\n11. Last index of employee ID 101: "+ lastIndex);

        //clear()
        ArrayList<Employee> copy = new ArrayList<>(employees);
        copy.clear();

        System.out.println("\n12. After clear(): " + copy);

        //isEmpty() after clear
        System.out.println("\n13. Is copied list empty? "+ copy.isEmpty());


    }
}
