public class SwapDemo {
    
    public static <T> void swap(T[] array, int i, int j){
        T temp = array[i];
        array[i]= array[j];
        array[j]= temp;
    }

    public static void main(String[] args){
        Integer[] numbers = {10,20,30,40};

        System.out.println("Before swap");
        for(Integer num : numbers){
            System.out.print(num + " ");
        }

        swap(numbers, 0,2);

        System.out.println("\nAfter swap:");
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }

        String[] names = {"Java", "Python", "C++"};

        swap(names, 0, 2);

        System.out.println("\nAfter String swap:");
        for (String name : names) {
            System.out.print(name + " ");
        }
    }
}
