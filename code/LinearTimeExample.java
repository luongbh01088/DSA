package dsa.src.code;

public class LinearTimeExample {
    public static void printElements(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);  // Iterate through and print each element
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        printElements(arr);  // Prints each element
    }
}


