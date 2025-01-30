package org.example;

public class Node {
    int value;
    NodeColor color;
    Node parent;
    Node left;
    Node right;

    public Node(int value, NodeColor color, Node parent) {
        this.value = value;
        this.color = color;
        this.parent = parent;
    }

    public Node(int key) {
        this.value = key;
        this.color = NodeColor.RED;
        parent = left = right = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodeColor getColor() {
        return color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getUncle() {
        if (this.parent == null || this.parent.parent == null) {
            return null;
        }
        if (this.getParent() == this.getParent().getParent().getLeft()) {
            return this.getParent().getParent().getRight();
        } else {
            return this.getParent().getParent().getLeft();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", color=" + color +
                '}';
    }

    public boolean isOnLeft() {
        return this.parent.left == this;
    }

    public boolean hasRedChild() {
        return this.left != null;
    }

    public Node brother() {
        if (parent==null){
            return null;
        }
        if (this.isOnLeft()){
            return this.parent.right;
        }
        return this.parent.left;
    }
}
