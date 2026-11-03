import java.util.*;

public class HashMap01 {
    
    public static void main(String[] args){

        HashMap<Integer, String> employees = new HashMap<>();
        
        employees.put(101, "Sonak");
        employees.put(102, "Rohit");
        employees.put(103, "Amit");

        System.out.println(employees);
        System.out.println(employees.get(102));
        employees.put(102,"Rohit kumar");
        System.out.println(employees.get(102));
        employees.remove(102);
        System.out.println(employees);
    }
}
