import java.io.*;
import java.util.Iterator;
import java.util.ListIterator;

public class Driver {
      public static void main(String[] args) throws IOException {
            BinarySearchTree<String> BST = new BinarySearchTree<>();
            
            //create BST
            
            //read from file
            File file = new File("city_data.txt");
      
            BufferedReader br = new BufferedReader(new FileReader(file));
      
            String st;
            System.out.println("hellow world");
            while ((st = br.readLine()) != null){
                  BST.insert(st);
                  System.out.println(st );
            }
           
            // add elements to the array list
           /*
            BST.insert("A");
            BST.insert("E");
            BST.insert("B");
            BST.insert("D");
            BST.insert("F");
      */
            // Use iterator to display contents of al
            System.out.print("Contents of BST: ");
            Iterator itr = BST.iterator();
      
            while(itr.hasNext()) {
                  Object element = itr.next();
                  System.out.print(element + " ");
            }
            System.out.println();
      
      }
      
      
      private static void preorder(BinarySearchTree.Node x) {
            if (x == null) return;
            System.out.println(x.item);
            preorder(x.left);
            preorder(x.right); }
}
