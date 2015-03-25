public class Trees {
  
  public static void main(String[] args) {
    int[] A = {20, 10, 30, 5, 15, 40, 18, 35, 50, 45};
    BST T = new BST(A);
    
    T.printNode(T.next(10));
    T.printNode(T.previous(10));
    T.delete(30); T.delete(20); T.delete(10);
    
    System.out.println("\n~Deleting: 30");
    System.out.println("~Deleting: 20");
    System.out.println("~Deleting: 10\n");
    
//    T.delete(18); T.delete(14);
//    System.out.println("\n~Deleting: 18, 14\n");
    
    T.printTree();
    
    BST T_ = new BST(5);
    T_.insert(2);
    T_.printTree();
    T_.delete(T_.root());
    System.out.println("~Deleting: root\n");
    T_.printTree();
    T_.delete(T_.root());
    T_.delete(T_.root());
  }
}