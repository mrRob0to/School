package Project;

import java.util.ArrayList;

public class BinarySearchTree {

    private Node root;


    static class Node {
        Student data;
        Node left;
        Node right;

        //Constructors
        public Node(Student x) {
            this.data = x;
            this.left = null;
            this.right = null;
        }
    }

    //Constructors
    public BinarySearchTree() {
        root = null;
    }


    //Getters
    public String getRoot() {
        return root.data.getIDNo();
    }
    public Node getLeft() {
        return root.left;
    }
    public Node getRight() {
        return root.right;
    }


    //Functions

    /**
     *Checks if root is null,
     * if it is Tree is empty
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     *Finds the max value in the tree.
     *Since the higher values are located on the right of the tree,
     *it recursively searches the right nodes.
     * Runtime - θ(logn)
     */
    public Student findMax() {
        if (isEmpty()) {
            System.out.println("Tree is Empty");
        }
        return findMax(root);
    }

    private Student findMax(Node t) {
        if (t.right != null) {
            return findMax(t.right);
        }
        return t.data;
    }

    /**
     *Finds the min value in the tree.
     *Since the lower values are located on the left of the tree,
     *it recursively searches the right nodes.
     * Runtime - θ(logn)
     */
    public Student findMin() {
        if (isEmpty()) {
            System.out.println("Tree is Empty");
        }
        return findMin(root);
    }

    private Student findMin(Node t) {
        if (t.left != null) {
            return findMin(t.left);
        }
        return t.data;
    }

    /**
     *Inserts a Student into tree recursively
     * if Student node is lower than current node
     * traverse through the left tree till node is higher than node on right or null.
     * Runtime - θ(logn)
     */
    public void insert(Student x) {
        root = insert(x, root);
    }

    public Node insert(Student x, Node t) {

        if (t != null) {
            int i = x.compareTo(t.data);

            if (i < 0) {
                t.left = insert(x, t.left);
            } else if (i > 0) {
                t.right = insert(x, t.right);
            }
            return t;
        }
        return new Node(x);
    }

    /**
     *Deletes student from tree.
     * If root node is to be deleted, it will take the min of the right tree
     * to replace as root as that is the next inorder value.
     * Runtime - θ(logn)
     */
    public void delete(Student x) {
        if (isEmpty()) {
            System.out.println("Tree is Empty");
        }
        root = delete(x, root);
    }

    private Node delete(Student x, Node t) {
        if (t == null) {
            return null;
        }

        if (x.compareTo(t.data) < 0) {
            t.left = delete(x, t.left);
        } else if (x.compareTo(t.data) > 0) {
            t.right = delete(x, t.right);
        } else {
            System.out.println("USER DELETED");
            if (t.left == null) {
                return t.right;
            } else if (t.right == null) {
                return t.left;
            }
            System.out.println("USER DELETED");
            t.data = findMin(t.right);
            t.right = delete(t.data, t.right);
        }
        return t;
    }

    /**
     *Searches student in tree recursively.
     * Runtime - θ(logn)
     */
    public Student search(Student x) {
        if (isEmpty()) {
            System.out.println("Tree is Empty");
        }
        return search(x, root);
    }

    private Student search(Student x, Node t) {
        if (t == null) {
            return null;
        }
        if (x.compareTo(t.data) < 0) {
            return search(x, t.left);
        }
        if (x.compareTo(t.data) > 0) {
            return search(x, t.right);
        }
        return t.data;
    }

    /**
     *Saves the BST inOrder into an Arraylist
     * Runtime - θ(n)
     */
    public void saveInOrder(ArrayList<StudentRecord> arr) {
        saveInOrder(root, arr);
    }

    private void saveInOrder(Node t, ArrayList<StudentRecord> arr) {
        if (t != null) {
            saveInOrder(t.left, arr);
            arr.add((StudentRecord) t.data);
            System.out.println(t.data);
            saveInOrder(t.right, arr);
        }
    }


    /**
     *Prints the BST In-Order
     * Runtime - θ(logn)
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node t) {
        if (t != null) {
            inOrder(t.left);
            System.out.println(t.data);
            inOrder(t.right);
        }
    }

    /**
     *Gets the size of the tree
     * Runtime - O(n)
     */
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        else
            return (size(node.left) + 1 + size(node.right));
    }
}
