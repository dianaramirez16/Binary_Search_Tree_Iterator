import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Iterative {
      public static void main(String[] args) throws IOException {
            BinarySearchTree<String> BST = new BinarySearchTree<>();
         
            //read from file
            File file = new File("city_data.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            System.out.println("Reading in list of cities...");
            while ((st = br.readLine()) != null){
                  BST.insert(st);
                  //System.out.println(st );
            }
            
            Iterator itr1 = BST.preorder_iterator();
            while(itr1.hasNext()) {
                  Object element = itr1.next();
                  System.out.print(element + "\n");
            }
            
            Iterator itr2 = BST.inorder_iterator();
            while(itr2.hasNext()) {
                  Object element = itr2.next();
                  System.out.print(element + " \n");
            }
      
            BST = new BinarySearchTree<>();
      
            //read from file
            File file1 = new File("city_data.txt");
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            String st1;
            //System.out.println("Reading in list of cities...");
            while ((st1 = br1.readLine()) != null){
                  BST.insert(st1);
                  //System.out.println(st );
            }
            Iterator itr3 = BST.postorder_iterator();
            while(itr3.hasNext()) {
                  Object element = itr3.next();
                  System.out.print(element + " \n");
            }
            System.out.println();
      }
}

class BinarySearchTree<Item> {
      private Node root;
      
      public boolean isEmpty() {
            return root == null;
      }
      public class Node {
            Item item;
            //boolean visited = false;
            //String s = (String) this.item;
            Node left, right;
            
            public Node(String s){
                  this.item = (Item) s;
            }
            public String getData() {
                  return (String)item;
            }
      }
      
      public Iterator<Item> preorder_iterator() {
            return new Preorder();
      }
      public Iterator<Item> inorder_iterator() { return new Inorder(); }
      public Iterator<Item> postorder_iterator() { return new Postorder(); }
      //Functions to insert data
      public void insert(String data) {
            root = insert(root, data);
      }
      // Function to insert data recursively
      private Node insert(Node node, String data) {
            
            if (node == null)
                  node = new Node(data);
            else {
                  int i = 0;
                  char one = node.getData().charAt(i);
                  char two = data.charAt(i);
                  //first characters are the same
                  while (Character.compare(one, two) == 0) {
                        i++;
                        //System.out.println("dup");
                        one = node.getData().charAt(i);
                        two = data.charAt(i);
                  }
                  one = node.getData().charAt(i);
                  two = data.charAt(i);
                  //ascii values increase along the alphabet
                  if (Character.compare(one, two) > 0) { // x < y
                        node.left = insert(node.left, data);
                        //System.out.println("x < y");
                  } else {
                        node.right = insert(node.right, data);
                        //System.out.println("y>x");
                  }
                  
            }
            return node;
      }
      
      /*
      private boolean search(Node node, String city) {
            boolean found = false;
            int i = 0;
            char one = node.getData().charAt(i);
            char two = city.charAt(i);
            while ((node != null) && !found) {
                  if (Character.compare(two, one) < 0) // city < node . data
                        node = node.getLeft();
                  else if (Character.compare(two, one) > 0) //
                        node = node.getRight();
                  else {
                        i++;
                        one = node.getData().charAt(i);
                        two = city.charAt(i);
                        if (Character.compare(two, one) == 0) {
                              found = true;
                              break;
                        }
                  }
                  found = search(node, city);
            }
            return found;
      }
      */
      
      private class Preorder implements Iterator<Item> {
            Stack<Node> stack = new Stack<Node>();
            
            public Preorder() {
                  if (root != null) stack.push(root);
                  System.out.println("\nPrinting Pre-Order Traversal:");
            }
            public boolean hasNext() {
                  return !stack.isEmpty();
            }
            public Item next() {
                  if (!hasNext()) throw new NoSuchElementException();
                  Node x = stack.pop();
                  Item item = x.item;
                  //System.out.println("x.item=" + x.item);
                  if (x.right != null) {
                        stack.push(x.right);
                        // System.out.println("x.right=" + x.right.getData());
                  }
                  if (x.left != null) {
                        stack.push(x.left);
                        // System.out.println("x.left=" + x.left.getData());
                  }
                  //System.out.println("pop2=" + stack.pop().getData()+"\n");
                  return item;
            }
      }
      private class Inorder implements Iterator<Item> {
            Stack<Node> stack = new Stack<Node>();
            Stack<Node> printStack = new Stack<Node>();
            
            public Inorder() {
                  while (root != null) {
                        stack.push(root);
                        root = root.left;
                  }
                  System.out.println("\nPrinting In-Order Traversal:");
            }
            public boolean hasNext() {
                  return !stack.isEmpty();
            }
            public Item next() {
                  if (!hasNext()) throw new NoSuchElementException();
                  Node x = stack.pop();
                  Item item = x.item;
                  //System.out.println("from FIRST pop x=" + x.getData());
                  if (x.right != null) {
                        x = x.right;
                        while (x != null) {
                              stack.push(x);
                              x = x.left;
                              //item = x.item;
                        }
                  }
                  return item;
            }
      }
      private class Postorder implements Iterator<Item> {
            Stack<Node> stack = new Stack<Node>();
            Stack<Node> printStack = new Stack<Node>();
            Stack<Node> finalStack = new Stack<Node>();
            
            public Postorder() {
                  if (root != null) stack.push(root);
                  System.out.println("\nPrinting Post-Order Traversal:");
            }
            public boolean hasNext() {
                  return !stack.isEmpty();
            }
            
            public Item next() {
                  Node x = stack.pop();
                  Item item = x.item;
                  if (x.left != null) {
                        stack.push(x.left);
                  }
                  if (x.right != null) {
                        stack.push(x.right);
                  }
                  return item;
            }
      }
}
