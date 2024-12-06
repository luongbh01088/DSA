package dsa.src.code;

public class QuadraticSpaceExample {
    public static int[][] createMatrix(int n) {
        int[][] matrix = new int[n][n];  // Create an n x n matrix
        return matrix;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] matrix = createMatrix(n);
        System.out.println("Matrix created with size " + n + "x" + n);
    }
}
