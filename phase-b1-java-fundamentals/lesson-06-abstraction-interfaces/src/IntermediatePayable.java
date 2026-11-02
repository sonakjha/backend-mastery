interface Payable {
    double calculatePay();
}

class Freelancer implements Payable {
    private final double hourlyRate;
    private final int hoursWorked;

    Freelancer(double hourlyRate, int hoursWorked) {
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}

class Employee implements Payable {
    private final double monthlySalary;
    private final double bonus;

    Employee(double monthlySalary, double bonus) {
        this.monthlySalary = monthlySalary;
        this.bonus = bonus;
    }

    @Override
    public double calculatePay() {
        return monthlySalary + bonus;
    }
}

public class IntermediatePayable{
    public static void main(String[] args) {

        Payable freelancer = new Freelancer(800, 100);
        Payable employee = new Employee(5000, 1000);

        System.out.println("Freelancer Pay: $" + freelancer.calculatePay());
        System.out.println("Employee Pay: $" + employee.calculatePay());
        
    }
}