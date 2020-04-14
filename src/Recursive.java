import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Recursive {
      public static void main(String[] args) throws IOException {
            BST<String> BST = new BST<>();
            //read from file
            File file = new File("city_data.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            System.out.println("Recursive program");
            System.out.println("Reading in list of cities...");
            while ((st = br.readLine()) != null) {
                  BST.insert(st);
                  //System.out.println(st );
            }
            
            System.out.println("\nPrinting Pre-Order Traversal:");
            BST.preorder();
          
            System.out.println("\nPrinting In-Order Traversal:");
            BST.inorder();
            
            System.out.println("\nPrinting Post-Order Traversal:");
            BST.postorder();
            
            String search, city;
            Scanner scan = new Scanner(System.in);
            System.out.println("\nWould you like to search for a city in the list? Enter yes or no");
            search = scan.nextLine();
            while (search.contains("y")) {
                  System.out.println("Enter the name of the city to search for in the BST:");
                  city = scan.nextLine();
                  System.out.println("Using Pre-Order traversal to search..." );
                  BST.search(city);
                  boolean found = BST.getFound();
                  if(!found){
                        System.out.println("No, could not find that city in the BST");
                  }
                  System.out.println("Would you like to search again?");
                  search = scan.nextLine();
            }
            System.out.println("thank you, come again");
      }
}
 class BST<Item> {
      private Node root;
      public boolean found = false;
      
       public void setFound(boolean found) {
             this.found = found;
       }
       
       public boolean getFound(){
             return this.found;
       }
      
       public boolean isEmpty() {
            return root == null;
      }

      public class Node {
            Item item;
            //boolean visited = false;
            //String s = (String) this.item;
            Node left, right;
            
            public Node(String s) {
                  this.item = (Item) s;
            }
      
            public String getData() {
                  return (String) item;
            }
      }
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
      
       /* Function for inorder traversal */
       public void inorder()
       {
             inorder(root);
       }
       private void inorder(Node r)
       {
             if (r != null)
             {
                   inorder(r.left);
                   System.out.print(r.getData() +"\n ");
                   inorder(r.right);
             }
       }
       /* Function for preorder traversal */
       public void preorder()
       {
             preorder(root);
       }
       private void preorder(Node r)
       {
             if (r != null)
             {
                   System.out.print(r.getData() +"\n ");
                   preorder(r.left);
                   preorder(r.right);
             }
       }
       /* Function for postorder traversal */
       public void postorder()
       {
             postorder(root);
       }
       private void postorder(Node r)
       {
             if (r != null)
             {
                   postorder(r.left);
                   postorder(r.right);
                   System.out.print(r.getData() +"\n ");
             }
       }
      
       public boolean search(String s){
             boolean ret =false;
             //System.out.println("search"+postorderModified(root, s));
             ret = postorderModified(root, s);
             return ret;
       }
       public boolean postorderModified(Node r, String s)
       {
             boolean b = false;
             if (r != null)
             {
                   String temp = r.getData();
                   //System.out.println(containsIgnoreCase(temp,s));
                   postorderModified(r.left, s);
                   postorderModified(r.right, s);
                   if(containsIgnoreCase(temp,s)){
                         System.out.println("Yes, " + r.getData() + " exists inside this BST");
                         setFound(true);
                   }
             }
             return b;
       }
      
       
       /*private boolean postorderSearch(Node r, String s){
             if (r != null)
             {
                   String temp = r.getData();
                   System.out.println("r=" + r.getData());
                   if(containsIgnoreCase(temp,s)){
                         //System.out.println("r=" + r.getData() + true);
                         return true;
                   }
                   //System.out.println("searching");
                   postorderModified(r.left, s);
                   //System.out.println("searching2");
                   postorderModified(r.right, s);
                   
                   
                   //System.out.println("searching3");
             }
             return false;
       } */
      
       public boolean containsIgnoreCase(String temp, String city) {
             temp = temp.toLowerCase();
             city = city.toLowerCase();
             if(temp.contains(city)){
                   return true;
             }
             return false;
       }

}
