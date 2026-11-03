import java.util.*;

class Employee {
    String id;
    String name;

    Employee(String id, String name) {
        this.id  = id;
        this.name = name;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee other  = (Employee) o;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Employee{id='" + id + "', name='" + name + "'}";
    }
}
public class SetDemo {

    public static void main (String[] args) {
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee("E001", "Alice"));
        employees.add(new Employee("E001", "Alice Updated"));

        System.out.println(employees.size());
    }
    
}
