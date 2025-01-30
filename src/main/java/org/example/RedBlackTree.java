package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RedBlackTree {
    private final GraphicsContext gc;
    private Node root;


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private void preOrderHelper(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    private void fixInsert(Node k) throws InterruptedException {
        updateUI();
        Node u;
        if (k == null || k.parent == null) {
            return;
        }
        while (k.parent.color == NodeColor.RED) {
            if (k.parent == k.parent.parent.left) {
                u = k.parent.parent.right;
                if (u != null && u.color == NodeColor.RED) {
                    k.parent.color = NodeColor.BLACK;
                    u.color = NodeColor.BLACK;
                    k.parent.parent.color = NodeColor.RED;
                    k = k.parent.parent;
                    updateUI();
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = NodeColor.BLACK;
                    k.parent.parent.color = NodeColor.RED;
                    rightRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.left;
                if (u != null && u.color == NodeColor.RED) {
                    k.parent.color = NodeColor.BLACK;
                    u.color = NodeColor.BLACK;
                    k.parent.parent.color = NodeColor.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = NodeColor.BLACK;
                    k.parent.parent.color = NodeColor.RED;
                    leftRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        updateUI();
        root.color = NodeColor.BLACK;
    }

    private void leftRotate(Node x) throws InterruptedException {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
        updateUI();

    }

    private void rightRotate(Node x) throws InterruptedException {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
        updateUI();

    }

    void deleteByVal(int n) throws InterruptedException {
        if (root == null)
            return;

        Node v = search(n);

        if (v.value != n) {
            System.out.println("No node found to delete with value: " + n);
            return;
        }
        updateUI();
        deleteNode(v);
        updateUI();
    }
    void deleteNode(Node v) throws InterruptedException {
        Node u = BSTreplace(v);
        boolean areBothBlack = ((u == null || u.color == NodeColor.BLACK) && (v.color == NodeColor.BLACK));
        Node parent = v.parent;

        if (u == null) {
            if (v == root) {
                root = null;
            } else {
                if (areBothBlack) {
                    fixDoubleBlack(v);
                } else if (v.brother() != null) {
                    v.brother().color = NodeColor.RED;
                }
                if (v.isOnLeft())
                    parent.left = null;
                else
                    parent.right = null;
            }
            printTreeWithColors();
            return;
        }

        if (v.left == null || v.right == null) {
            if (v == root) {
                v.value = u.value;
                v.left = v.right = null;
            } else {
                if (v.isOnLeft())
                    parent.left = u;
                else
                    parent.right = u;
                u.parent = parent;

                if (areBothBlack)
                    fixDoubleBlack(u);
                else
                    u.color = NodeColor.BLACK;
            }
            printTreeWithColors();
            return;
        }

        swapValues(u, v);
        updateUI();
        printTreeWithColors();
        System.out.println("after delete the replace node");
        deleteNode(u);
        printTreeWithColors();
    }

    private void swapValues(Node u, Node v) {
        int tempValue = v.value;
        v.value = u.value;
        u.value = tempValue;
    }

    public void fixDoubleBlack(Node x) throws InterruptedException {
        if (x == root) return;

        Node brother = x.brother(), parent = x.parent;

        if (brother == null) {
            fixDoubleBlack(parent);
        } else {
            if (brother.color == NodeColor.RED) {
                parent.color = NodeColor.RED;
                brother.color = NodeColor.BLACK;

                if (brother.isOnLeft()) {
                    rightRotate(parent);
                } else {
                    leftRotate(parent);
                }
                fixDoubleBlack(x);
            } else {
                if (brother.hasRedChild()) {
                    if (brother.left != null && brother.left.color == NodeColor.RED) {
                        if (brother.isOnLeft()) {
                            brother.left.color = brother.color;
                            brother.color = parent.color;
                            rightRotate(parent);
                        } else {
                            brother.left.color = parent.color;
                            rightRotate(brother);
                            leftRotate(parent);
                        }
                    } else {
                        if (brother.isOnLeft()) {
                            brother.right.color = parent.color;
                            leftRotate(brother);
                            rightRotate(parent);
                        } else {
                            brother.right.color = brother.color;
                            brother.color = parent.color;
                            leftRotate(parent);
                        }
                    }
                    parent.color = NodeColor.BLACK;
                } else {
                    brother.color = NodeColor.RED;
                    if (parent.color == NodeColor.BLACK) {
                        fixDoubleBlack(parent);
                    } else {
                        parent.color = NodeColor.BLACK;
                    }
                }
            }
        }
    }


    Node BSTreplace(Node x) {
        if (x.left != null && x.right != null)
            return mostRight(x.left);

        if (x.left == null && x.right == null)
            return null;

        if (x.left != null)
            return x.left;
        else
            return x.right;
    }

    private Node mostRight(Node x) {
        Node temp = x;
        while (temp.right != null)
            temp = temp.right;
        return temp;
    }

    private Node mostLeft(Node x) {
        Node temp = x;
        while (temp.left != null)
            temp = temp.left;
        return temp;
    }

    Node search(int n) {
        Node temp = root;
        while (temp != null) {
            if (n < temp.value) {
                if (temp.left == null)
                    break;
                else
                    temp = temp.left;
            } else if (n == temp.value) {
                break;
            } else {
                if (temp.right == null)
                    break;
                else
                    temp = temp.right;
            }
        }

        return temp;
    }

    public void insert(int key) throws InterruptedException {
        Node node = new Node(key);
        node.color = NodeColor.RED;
        node.parent = null;

        if (root == null) {
            node.color = NodeColor.BLACK;
            root = node;
        } else {
            Node temp = root;
            Node parent = null;

            while (temp != null) {
                parent = temp;
                if (node.value < temp.value) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
            node.parent = parent;
            updateUI();
            if (node.parent == null) {
                node.parent = node;
                node.parent.left = node.parent.right = null;
            } else if (node.value < parent.value) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
        node.left = null;
        node.right = null;

        System.out.println(node);
        printTreeWithColors();
        updateUI();
        printTree();
//        Thread.sleep(2000);
        System.out.println("after Fixing");
        fixInsert(node);
        printTreeWithColors();
        System.out.println("-------------------------------");

    }

    public void printTreeWithColorsHelper(Node node, String prefix, boolean isTail) {
        if (node != null) {
            String cur = (prefix + (isTail ? "└── " : "├── ") + node.value + " (" + node.color + ")\n");
            System.out.print(cur);
            printTreeWithColorsHelper(node.left, prefix + (isTail ? "    " : "│   "), false);
            printTreeWithColorsHelper(node.right, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    private String getColor(Node node) {
        String black = "\033[30m";
        String red = "\033[31m";
        return node.color == NodeColor.RED ? red : black;
    }

    public void printTreeWithColors() {
        printTreeWithColorsHelper(root, "", true);
    }

    public void printTree() {
        drawNode(root, 400, 50, 200);
    }


    public RedBlackTree(GraphicsContext gc) {
        this.gc = gc;
        root = null;
    }

    private void updateUI() {
        gc.clearRect(0, 0, 800, 600);
        printTree();
    }


    private void drawNode(Node node, double x, double y, double hGap) {
        if (node != null) {
            gc.setFill(node.color == NodeColor.RED ? Color.RED : Color.BLACK);
            gc.fillOval(x, y, 30, 30);
            gc.setFill(Color.WHITE);
            gc.fillText(String.valueOf(node.value), x + 10, y + 20);
            if (node.left != null) {
                gc.strokeLine(x + 15, y + 30, x - hGap + 15, y + 80);
                drawNode(node.left, x - hGap, y + 80, hGap / 2);
            }
            if (node.right != null) {
                gc.strokeLine(x + 15, y + 30, x + hGap + 15, y + 80);
                drawNode(node.right, x + hGap, y + 80, hGap / 2);
            }
        }
    }

}
