import java.io.*;
import java.util.*;

public class Iterative {
      public static void main(String[] args) throws IOException {
            BinarySearchTree<String> BST = new BinarySearchTree<>();
            ArrayList<Object> aL = new ArrayList<Object>();
            //read from file
            File file = new File("city_data.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            System.out.println("Reading in list of cities...");
            while ((st = br.readLine()) != null){
                  BST.insert(st);
                  //System.out.println(st );
            }
      
            System.out.println("\nPrinting Pre-Order Traversal:");
            Iterator itr1 = BST.preorder_iterator();
            while(itr1.hasNext()) {
                  Object element = itr1.next();
                  System.out.print(element + "\n");
            }
      
            System.out.println("\nPrinting In-Order Traversal:");
            Iterator itr2 = BST.inorder_iterator();
            while(itr2.hasNext()) {
                  Object element = itr2.next();
                  System.out.print(element + " \n");
            }
      
            BST = new BinarySearchTree<>();
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
                  aL.add(element);
            }
            System.out.println("\nPrinting Post-Order Traversal:");
            for(int i=aL.size()-1; i>-1;i--){
                  System.out.println(aL.get(i));
            }
            System.out.println();
            
            String search, city;
            
            Scanner scan = new Scanner(System.in);
            System.out.println("Would you like to search for a city in the list? Enter yes or no");
            search = scan.nextLine();
            while(search.contains("y")){
                  System.out.println("Enter the name of the city to search for in the BST:");
                  city = scan.nextLine();
                  int x=0;
                  itr1 = BST.postorder_iterator();
                  boolean found =false;
                  int mulResults =0;
                  System.out.println("Using Pre-Order traversal to search...");
                  while(itr1.hasNext()) {
                        Object element = itr1.next();
                        String temp = (String)element;
                        x++;
                        if(containsIgnoreCase(temp,city)){
                              mulResults++;
                              System.out.print("Found city " + element + " in position " +(19-x) + "\n");
                              found = true;
                              if (mulResults>1){
                                    System.out.println("multiple results found, please enter the complete city name");
                              }
                        }
                        
                  }
                  if (!found){
                        System.out.println("Could not find city.");
                  }
                  System.out.println("Would you like to search again?");
                  search = scan.nextLine();
            }
            System.out.println("thank you, come again");
      }
      
      private static boolean containsIgnoreCase(String temp, String city) {
            temp = temp.toLowerCase();
            city = city.toLowerCase();
            if(temp.contains(city)){
                  return true;
            }
            return false;
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
      
      private boolean search(Node node, String city) {
            boolean found = false;
            int i = 0;
            char one = node.getData().charAt(i);
            char two = city.charAt(i);
            while ((node != null) && !found) {
                  if (Character.compare(two, one) < 0) // city < node . data
                        node = node.left;
                  else if (Character.compare(two, one) > 0) //
                        node = node.right;
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
      
      private class Preorder implements Iterator<Item> {
            Stack<Node> stack = new Stack<Node>();
            
            public Preorder() {
                  if (root != null) stack.push(root);
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
