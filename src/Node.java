public class Node {
  private Node left, right, parent;
  private int key;
  
  public Node(int key) {
    this.key = key;
    this.left = null;
    this.right = null;
    this.parent = null;
    
  }
  
  public Node(int key, Node left, Node right, Node parent) {
    this.key = key;
    this.left = left;
    this.right = right;
    this.parent = parent;
    
  }
  
  public Node left() {return this.left;}
  public Node right() {return this.right;}
  public Node parent() {return this.parent;}
  public int key() {return this.key;}
  
  public void setLeft(Node left) {this.left = left;}
  public void setRight(Node right) {this.right = right;}
  public void setParent(Node parent) {this.parent = parent;}
  public void setKey(int key) {this.key = key;}
  
}