import java.util.*;

class Employee {
    int id ;
    String name;

    Employee(int id , String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(!(obj instanceof Employee)) return false;

        Employee other = (Employee) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
public class EqualsHashCode {
    public static void main(String[] args) {

        Set<Employee> employees = new HashSet<>();

        employees.add(new Employee(1, "Sonak"));
        employees.add(new Employee(1, "Rahul"));

        System.out.println(employees.size());
    }

}
