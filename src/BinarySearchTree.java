import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

//reference for creating bst  https://www.sanfoundry.com/java-program-implement-binary-search-tree/


public class BinarySearchTree<Item> {
      private Node root;
      
      public boolean isEmpty() {
            return root == null;
      }
      public class Node {
            Item item;
            //String s = (String) this.item;
            Node left, right;
      
            public Node(String s){
                  this.item = (Item) s;
            }
            public String getData() {
                  return (String)item;
            }
            
      }
      
      public Iterator<Item> iterator() {
            //System.out.println("test");
            return new Preorder();
            //return new Preorder();
      }
      
      
       //Functions to insert data
      
      public void insert(String data) {
            root = insert(root, data);
            //System.out.println("insert1");
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
      
      // Function for inorder traversal
      public void inorder() {
            inorder(root);
      }
      
      private void inorder(Node r) {
            if (r != null) {
                  inorder(r.getLeft());
                  System.out.print(r.getData() + " ");
                  inorder(r.getRight());
            }
      }
      
      // Function for postorder traversal
      public void postorder() {
            postorder(root);
      }
      
      private void postorder(Node r) {
            if (r != null) {
                  postorder(r.getLeft());
                  postorder(r.getRight());
                  System.out.print(r.getData() + " ");
            }
      }*/
      
    
      private class Preorder implements Iterator<Item> {
            Stack<Node> stack = new Stack<Node>();
            
            public Preorder() {
                  if (root != null) stack.push(root);
                  System.out.println("preorder constructor called\n");
            }
            
            public void remove() {
                  // throw exception as before
            }
            
            public boolean hasNext() {
                  return !stack.isEmpty();
            }
            
            public Item next() {
                  if (!hasNext()) throw new NoSuchElementException();
                  Node x = stack.pop();
                  Item item = x.item;
                  if (x.right != null)
                        stack.push(x.right);
                  if (x.left != null)
                        stack.push(x.left);
                  return item;
            }
      }
     
}