# Red-Black Tree Implementation

## Overview

This project provides an implementation of a **Red-Black Tree**, a self-balancing binary search tree. Red-Black Trees ensure that the tree remains balanced, resulting in efficient operations for insertion, deletion, and search, with a time complexity of **O(log n)**.

## Features

- Insertion of elements while maintaining the Red-Black Tree properties
- Deletion of elements with rebalancing
- Searching for a specific element
- Traversal operations (in-order, pre-order, post-order)
- Visualization of the tree structure

## Technologies Used

- **Language:** C++
- **Development Tools:** GCC, Clang, or any C++ compiler
- **Data Structures:** Binary Search Trees, Red-Black Tree balancing techniques

## Getting Started

### Prerequisites

Ensure you have a C++ compiler installed. You can use:

- GCC: `sudo apt install g++` (Linux)
- Clang: `brew install llvm` (macOS)
- MSVC: Installed with Visual Studio (Windows)

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/mahajialirezaei/RedBlackTree.git
   ```
2. Navigate to the project directory:
   ```sh
   cd RedBlackTree
   ```
3. Compile the project:
   ```sh
   g++ -o red_black_tree main.cpp RedBlackTree.cpp
   ```
4. Run the executable:
   ```sh
   ./red_black_tree
   ```

## Usage

Once the program is running, you can:

- Insert elements by providing input when prompted.
- Delete elements from the tree.
- Search for a specific element.
- View the tree structure using traversal methods.

## Red-Black Tree Properties

The tree follows these essential properties:

1. Every node is either **red** or **black**.
2. The **root** node is always **black**.
3. **Red** nodes cannot have **red** children (no two consecutive red nodes).
4. Every path from a node to its descendant **NULL** nodes must have the same number of **black** nodes.

## Example

```cpp
RedBlackTree tree;
tree.insert(10);
tree.insert(20);
tree.insert(30);
tree.insert(15);
tree.display();
```

This will insert elements into the tree while maintaining the Red-Black properties and display the tree structure.

## Contributing

Contributions are welcome! Feel free to submit issues or pull requests to improve this implementation.

## License

This project is open-source and available under the MIT License.

## Author

[Mahaji Alirezaei](https://github.com/mahajialirezaei)

