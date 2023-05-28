/**
 * @Operation ins
 * A new key is always inserted at the leaf by maintaining the property of the binary search tree. We start searching for
 * a key from the root until we hit a leaf node. Once a leaf node is found, the new node is added as a child of the leaf node.
 * The below steps are followed while we try to insert a node into a binary search tree:
 * -----------------------------------------------------------------------------------------
 * Check the value to be inserted (say X) with the value of the current node (say val) we are in:
 * If X is less than val move to the left subtree.
 * Otherwise, move to the right subtree.
 * Once the leaf node is reached, insert X to its right or left based on the relation between X and the leaf node’s value.
 */

package AaDS.DataStructures.Tree.BST;

import AaDS.DataStructures.Tree.Tree;

import java.util.Objects;

class Node<T extends Comparable<T>>{
    private Node<T> leftChild;
    private Node<T> rightChild;
    private T data;

    public Node(Node<T> leftChild, Node<T> rightChild, T data) {
        if(data == null) throw new NullPointerException("Data is empty");
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        if (!Objects.equals(leftChild, node.leftChild)) return false;
        if (!Objects.equals(rightChild, node.rightChild)) return false;
        return data.equals(node.data);
    }

    @Override
    public int hashCode() {
        int result = leftChild != null ? leftChild.hashCode() : 0;
        result = 31 * result + (rightChild != null ? rightChild.hashCode() : 0);
        result = 31 * result + data.hashCode();
        return result;
    }
}
public class BinarySearchTree <T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;
    @Override
    public T getMin(){
        if(isEmpty()){
            return null;
        }
        Node<T> node = root;
        while (node.getLeftChild() != null){
            node = node.getLeftChild();
        }
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private T getMin(Node<T> node){
        if(node.getLeftChild() != null){
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }
    private T getMax(Node<T> node){
        if(node.getLeftChild() != null){
            return getMin(node.getRightChild());
        }
        return node.getData();
    }
    @Override
    public T getMax(){
        if(isEmpty())return null;
        Node<T> node = root;
        while(node.getRightChild() != null)
            node = node.getRightChild();
        return node.getData();
    }
    @Override
    public void traverse(){
        traverseInOrder(root);
    }
    //From left subtree then the root then the right subtree
    private void traverseInOrder(Node<T> node){
        if(node != null){
            traverseInOrder(node.getLeftChild());
            System.out.println(node);
            traverseInOrder(node.getRightChild());
        }
    }
    @Override
    public Tree<T> insert(T data){
        if(isEmpty()) root = new Node<>(null, null, data);
        else insert(data, root);
        return this;
    }
    private void insert(T data, Node<T> node){
        //hop to the left subtree
        if(data.compareTo(node.getData()) < 0){
            if(node.getLeftChild() == null){
                Node<T> newNode = new Node<>(null, null, data);
                node.setLeftChild(newNode);
            }else{
                insert(data, node.getLeftChild());
            }
        }
        //hop to the right subtree
        if(data.compareTo(node.getData()) > 0){
            if(node.getRightChild() == null){
                Node<T> newNode = new Node<>(null, null, data);
                node.setRightChild(newNode);
            }else{
                insert(data, node.getRightChild());
            }
        }
    }
    @Override
    public void delete(T data){
        root = delete(data, root);
    }
    private Node<T> delete(T data, Node<T> node){
        if(node == null) return null;
        if(data.compareTo(node.getData()) < 0){
            node.setLeftChild(delete(data, node.getLeftChild()));
        }else if(data.compareTo(node.getData()) > 0){
            node.setRightChild(delete(data, node.getRightChild()));
        }else{
            //One child or Leaf node
            if(node.getLeftChild() == null){
                return node.getRightChild();
            }else if(node.getRightChild() == null){
                return node.getLeftChild();
            }
            //Two children
            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(), node.getLeftChild()));
        }
        return node;
    }
}
