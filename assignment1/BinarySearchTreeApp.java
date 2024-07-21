import java.util.Scanner;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}

class BinarySearchTree {
    TreeNode root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int value) {
        root = insertRec(root, value);
    }

    TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    void delete(int value) {
        root = deleteRec(root, value);
    }

    TreeNode deleteRec(TreeNode root, int value) {
        if (root == null) return root;

        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }

        return root;
    }

    int minValue(TreeNode root) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    void inOrderRec(TreeNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.value + " ");
            inOrderRec(root.right);
        }
    }

    void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    void preOrderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    void postOrderRec(TreeNode root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.value + " ");
        }
    }
}

public class BinarySearchTreeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create a binary search tree");
            System.out.println("2. Add a node");
            System.out.println("3. Delete a node");
            System.out.println("4. Print nodes by InOrder");
            System.out.println("5. Print nodes by PreOrder");
            System.out.println("6. Print nodes by PostOrder");
            System.out.println("7. Exit program");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    int[] initialValues = {1001, 1003, 1005, 1007, 1009, 1011, 1013, 1015, 1017, 1019};
                    for (int value : initialValues) {
                        bst.insert(value);
                    }
                    System.out.println("Binary search tree created.");
                    break;
                case 2:
                    System.out.print("Enter the value to add: ");
                    int addValue = scanner.nextInt();
                    bst.insert(addValue);
                    System.out.println("Node added.");
                    break;
                case 3:
                    System.out.print("Enter the value to delete: ");
                    int deleteValue = scanner.nextInt();
                    bst.delete(deleteValue);
                    System.out.println("Node deleted.");
                    break;
                case 4:
                    System.out.println("InOrder traversal:");
                    bst.inOrder();
                    break;
                case 5:
                    System.out.println("PreOrder traversal:");
                    bst.preOrder();
                    break;
                case 6:
                    System.out.println("PostOrder traversal:");
                    bst.postOrder();
                    break;
                case 7:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
