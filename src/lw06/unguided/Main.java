import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();
        while(read.hasNextInt()){
            int number = read.nextInt();
            bst.insert(number);
        }

        System.out.println("In-order:");
        bst.inOrder();
        System.out.println("Pre-order:");
        bst.preOrder();
        System.out.println("Post-order:");
        bst.postOrder();
        System.out.println("Searching 65: " + bst.search(65));
        System.out.println("Nodes: " + bst.countNodes());
        System.out.println("leaves: " + bst.countLeaves());
        
        read.close();
    }
}