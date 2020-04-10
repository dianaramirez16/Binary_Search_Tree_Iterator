This project was completed for a graduate studies course: Adv Programming Languages.

Binary Search Tree Traversal and Search Using Iterators In Java/C++

In this assignment you are required to implement a binary search tree ADT with insertion and preorder, postorder and inorder traversal operations. Your program must read a list of strings from an input file and insert them into the binary search tree.
Then it must traverse the tree in preorder, inorder and postorder manner and print the data in the tree on the screen, using the iterators in Java/C++.
Note this project can be written in either JAVA or C++.

First, please review the two attached files: java_iterator.pdf and c++_iterator.pdf, about how iterators in Java and C++ are implemented. The program has one input file called “data.txt” that contains a string on each line. There is no repetitive string in the file. Below is an (simplified) version of the input file:
NewYork Cairo Toronto Barcelona London Paris Rome Athens Venice

Output:
Task 1: Your program must read each string from the input file and insert it into a binary search tree ADT, then it has to print the contents of the tree in three ways: preorder, inorder and postorder (notice the order of traversals is important). The output of your program for the above input must be:

Preorder Traversal Using Iterator:
NewYork Cairo Barcelona Athens London Toronto Paris Rome Venice

Inorder Traversal Using Iterator:
Athens Barcelona Cairo London NewYork Paris Rome Toronto Venice

Postorder Traversal Using Iterator:
Athens Barcelona London Cairo Rome Paris Venice Toronto NewYork

Then your program asks the user to type a city’s name to search if the city is on the list, and then uses one of the iterative traversal to return “yes” or “no”.

Please maintain proper programming style which includes proper indenting and having enough comments.
Name your program “iterative.cpp” or “iterative.java”.

Task 2: Make preorder, postorder and inorder traversal operations using recursion and printout the results accordingly.

Then your program asks the user to type a city’s name to search if the city is on the list, and then uses one of the recursive traversal to return “yes” or “no”.

Name your program “recursive.cpp” or “recursive.java”.