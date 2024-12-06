package dsa.src.code;
public class BinaryTreeExample {

    // Student class holds student details and rank logic
    static class Student {
        int studentId;
        String name;
        double marks;
        String rank;

        // Constructor
        public Student(int studentId, String name, double marks) {
            this.studentId = studentId;
            this.name = name;
            this.marks = marks;
            this.rank = assignRank(marks);
        }

        // Method to assign rank based on marks
        public String assignRank(double marks) {
            if (marks >= 9.0) return "Excellent";
            if (marks >= 7.5) return "Very Good";
            if (marks >= 6.5) return "Good";
            if (marks >= 5.0) return "Medium";
            return "Fail";
        }

        @Override
        public String toString() {
            return "Student ID: " + studentId + ", Name: " + name + ", Marks: " + marks + ", Rank: " + rank;
        }
    }

    // BinaryTree class handles all binary tree operations
    static class BinaryTree {
        class Node {
            Student student;
            Node left, right;

            public Node(Student student) {
                this.student = student;
                left = right = null;
            }
        }

        private Node root;

        public BinaryTree() {
            root = null;
        }

        // Insert a new student into the binary tree
        public void insert(Student student) {
            root = insertRec(root, student);
        }

        private Node insertRec(Node root, Student student) {
            if (root == null) {
                root = new Node(student);
                return root;
            }

            if (student.marks < root.student.marks) {
                root.left = insertRec(root.left, student);
            } else if (student.marks > root.student.marks) {
                root.right = insertRec(root.right, student);
            }

            return root;
        }

        // Search for a student by marks
        public Student search(double marks) {
            Node resultNode = searchRec(root, marks);
            return resultNode != null ? resultNode.student : null;
        }

        private Node searchRec(Node root, double marks) {
            if (root == null || root.student.marks == marks) {
                return root;
            }

            if (marks < root.student.marks) {
                return searchRec(root.left, marks);
            } else {
                return searchRec(root.right, marks);
            }
        }

        // Delete a student by marks
        public void delete(double marks) {
            root = deleteRec(root, marks);
        }

        private Node deleteRec(Node root, double marks) {
            if (root == null) return root;

            if (marks < root.student.marks) {
                root.left = deleteRec(root.left, marks);
            } else if (marks > root.student.marks) {
                root.right = deleteRec(root.right, marks);
            } else {
                if (root.left == null) return root.right;
                else if (root.right == null) return root.left;

                root.student = minValue(root.right);
                root.right = deleteRec(root.right, root.student.marks);
            }

            return root;
        }

        private Student minValue(Node root) {
            Student minValue = root.student;
            while (root.left != null) {
                minValue = root.left.student;
                root = root.left;
            }
            return minValue;
        }

        // Update a student's name by marks
        public boolean update(double marks, String newName) {
            Student student = search(marks);
            if (student != null) {
                student.name = newName;
                return true;
            }
            return false;
        }

        // In-order traversal (sorting by marks)
        public void inOrder() {
            inOrderRec(root);
        }

        private void inOrderRec(Node root) {
            if (root != null) {
                inOrderRec(root.left);
                System.out.println(root.student);
                inOrderRec(root.right);
            }
        }
    }

    // Main class to test BinaryTree operations
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Insert students into the tree
        tree.insert(new Student(1, "Alice", 8.0));
        tree.insert(new Student(2, "Bob", 5.5));
        tree.insert(new Student(3, "Charlie", 7.8));
        tree.insert(new Student(4, "David", 9.5));
        tree.insert(new Student(5, "Eva", 6.2));

        // In-order traversal (Sorted by marks)
        System.out.println("In-order traversal:");
        tree.inOrder();

        // Search for a student by marks
        System.out.println("\nSearching for student with marks 7.8:");
        Student student = tree.search(7.8);
        System.out.println(student != null ? student : "Student not found");

        // Update a student's name
        System.out.println("\nUpdating name of student with marks 7.8:");
        boolean updated = tree.update(7.8, "Charles");
        System.out.println(updated ? "Name updated successfully" : "Student not found");

        // In-order traversal after update
        System.out.println("\nIn-order traversal after update:");
        tree.inOrder();

        // Delete a student by marks
        System.out.println("\nDeleting student with marks 5.5:");
        tree.delete(5.5);

        // In-order traversal after deletion
        System.out.println("\nIn-order traversal after deletion:");
        tree.inOrder();
    }
}
