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
            System.out.println("Reading in list of cities...");
            while ((st = br.readLine()) != null){
                  BST.insert(st);
                  System.out.println(st );
            }
            // Use iterator to display contents of al
            Iterator itr = BST.preorder_iterator();
      
            while(itr.hasNext()) {
                  Object element = itr.next();
                  System.out.print(element + "*** ");
            }
            System.out.println();
      
      }
}
