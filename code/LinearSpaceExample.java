package dsa.src.code;

public class LinearSpaceExample {
    public static int[] duplicateArray(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];  // Copy all elements to the new array
        }
        return newArr;
    }    
    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        int[] duplicatedArr = duplicateArray(arr);
        for (int num : duplicatedArr) {
            System.out.println(num);  // Prints the duplicated array
        }
    }
}

