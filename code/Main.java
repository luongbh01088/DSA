package dsa.src.code;

class StudentNode {
    double marks;
    String studentId;
    String name;
    String rank;
    StudentNode left, right;

    // Constructor to create a new node
    public StudentNode(double marks, String studentId, String name) {
        this.marks = marks;
        this.studentId = studentId;
        this.name = name;
        this.rank = assignRank(marks);
        this.left = null;
        this.right = null;
    }

    // Method to assign a rank based on marks
    public String assignRank(double marks) {
        if (marks >= 0 && marks < 5.0) {
            return "Fail";
        } else if (marks >= 5.0 && marks < 6.5) {
            return "Medium";
        } else if (marks >= 6.5 && marks < 7.5) {
            return "Good";
        } else if (marks >= 7.5 && marks < 9.0) {
            return "Very Good";
        } else if (marks >= 9.0 && marks <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid Marks";
        }
    }
}

class StudentBinaryTree {
    private StudentNode root;

    // Constructor to initialize the tree
    public StudentBinaryTree() {
        this.root = null;
    }

    // Method to insert a new student node into the tree
    public void insert(double marks, String studentId, String name) {
        StudentNode newNode = new StudentNode(marks, studentId, name);
        if (root == null) {
            root = newNode;
        } else {
            insertRecursive(root, newNode);
        }
    }

    // Recursive helper method for insertion
    private void insertRecursive(StudentNode current, StudentNode newNode) {
        if (newNode.marks < current.marks) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                insertRecursive(current.left, newNode);
            }
        } else {
            if (current.right == null) {
                current.right = newNode;
            } else {
                insertRecursive(current.right, newNode);
            }
        }
    }

    // Method to search for a student by their marks
    public StudentNode search(double marks) {
        return searchRecursive(root, marks);
    }

    // Recursive helper method for search
    private StudentNode searchRecursive(StudentNode current, double marks) {
        if (current == null || current.marks == marks) {
            return current;
        }
        if (marks < current.marks) {
            return searchRecursive(current.left, marks);
        }
        return searchRecursive(current.right, marks);
    }

    // Method to delete a student node by their marks
    public void delete(double marks) {
        root = deleteRecursive(root, marks);
    }

    // Recursive helper method for delete
    private StudentNode deleteRecursive(StudentNode current, double marks) {
        if (current == null) {
            return null;
        }

        if (marks < current.marks) {
            current.left = deleteRecursive(current.left, marks);
        } else if (marks > current.marks) {
            current.right = deleteRecursive(current.right, marks);
        } else {
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }
            current.marks = findMin(current.right).marks;
            current.studentId = findMin(current.right).studentId;
            current.name = findMin(current.right).name;
            current.rank = findMin(current.right).rank;
            current.right = deleteRecursive(current.right, current.marks);
        }
        return current;
    }

    // Method to find the minimum value node (for deletion)
    private StudentNode findMin(StudentNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Method to traverse the tree in-order and display students
    public void traverseInOrder() {
        traverseInOrderRecursive(root);
    }

    // Recursive helper method for in-order traversal
    private void traverseInOrderRecursive(StudentNode current) {
        if (current != null) {
            traverseInOrderRecursive(current.left);
            System.out.println("Student ID: " + current.studentId + ", Name: " + current.name + ", Marks: " + current.marks + ", Rank: " + current.rank);
            traverseInOrderRecursive(current.right);
        }
    }

    // Method to update the name of a student by marks
    public boolean update(double marks, String newName) {
        StudentNode student = search(marks);
        if (student != null) {
            student.name = newName;
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        StudentBinaryTree tree = new StudentBinaryTree();

        // Insert students into the tree
        tree.insert(8.5, "S001", "Alice");
        tree.insert(5.4, "S002", "Bob");
        tree.insert(7.0, "S003", "Charlie");

        // Display all students sorted by marks (In-order traversal)
        System.out.println("Students sorted by marks:");
        tree.traverseInOrder();

        // Search for a student by marks
        double searchMarks = 7.0;
        StudentNode student = tree.search(searchMarks);
        if (student != null) {
            System.out.println("\nStudent found: " + student.name + " with marks " + student.marks + " and rank " + student.rank);
        } else {
            System.out.println("\nStudent not found.");
        }

        // Update a student's name
        tree.update(7.0, "Charlie Brown");
        System.out.println("\nAfter updating Charlie's name:");
        tree.traverseInOrder();

        // Delete a student by marks
        tree.delete(5.4);
        System.out.println("\nAfter deleting Bob:");
        tree.traverseInOrder();
    }
}
