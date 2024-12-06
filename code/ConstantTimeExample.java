package dsa.src.code;

public class ConstantTimeExample {
    public static int getFirstElement(int[] arr) {
        return arr[0];  // Accessing the first element takes constant time
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        System.out.println(getFirstElement(arr));  // The result is always 10
    }
}

