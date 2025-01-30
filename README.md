Red-Black Tree (Java Implementation)
Overview
This project provides an efficient Java implementation of a Red-Black Tree, a self-balancing binary search tree that maintains logarithmic time complexity for insertion, deletion, and search operations. The tree automatically balances itself using color properties, rotations, and recoloring to ensure optimal performance.

Features
✅ Insertion: Adds elements while maintaining Red-Black Tree properties.
✅ Deletion: Removes elements and rebalances the tree.
✅ Search: Efficient lookup in O(log n) time complexity.
✅ Tree Traversals: Supports in-order, pre-order, and post-order traversal methods.
✅ Balancing Mechanism: Uses left/right rotations and recoloring to maintain balance.
✅ Visualization Support: Prints the tree structure for better understanding.
How Red-Black Tree Works
A Red-Black Tree follows these key properties:

Every node is either red or black.
The root node is always black.
Red nodes cannot have red children (no two consecutive red nodes).
Every path from a node to its descendant NULL nodes must have the same number of black nodes.
Newly inserted nodes are always red and may require rotations or recoloring to maintain balance.
Balancing is achieved through rotations (left/right) and recoloring based on the following cases:

Case 1: Red uncle → Recoloring
Case 2: Black uncle (zig-zag) → Rotation + recoloring
Case 3: Black uncle (straight line) → Rotation
Installation & Usage
Prerequisites
Java Development Kit (JDK 8 or later)
Any Java IDE (e.g., IntelliJ IDEA, Eclipse, VS Code)
Basic understanding of tree data structures
Cloning the Repository
Clone the repository using:

sh
Copy
Edit
git clone https://github.com/mahajialirezaei/RedBlackTree.git
cd RedBlackTree
Compilation & Execution
Using Command Line:
sh
Copy
Edit
javac RedBlackTree.java
java RedBlackTree
Running in an IDE:
Open the project in your preferred Java IDE.
Compile and run the Main.java file (or the file containing the main method).
The program will execute Red-Black Tree operations.
Example Usage (Java Code)
java
Copy
Edit
public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.delete(20);
        
        tree.inOrderTraversal(); // Displays the tree in sorted order
    }
}
Performance Analysis
Operation	Average Case	Worst Case
Search	O(log n)	O(log n)
Insertion	O(log n)	O(log n)
Deletion	O(log n)	O(log n)
Since the tree remains balanced, all operations run in logarithmic time complexity.

Contributions
Contributions are welcome! If you’d like to improve this project:

Fork the repository.
Create a new branch (feature-branch).
Submit a pull request with improvements.
