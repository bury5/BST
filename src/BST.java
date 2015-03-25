public class BST {
  private BST Tree;
  private Node root;
  
  public BST(int[] A) {for (int i = 0; i < A.length; i++) insert(A[i]);}
  public BST(int key) {root = new Node(key);}
  public BST() {}
  
  public Node root() {return root;}
  public void setRoot(Node root) {this.root = root;}
  
  private Node search_(Node x, int key) {return (x == null || x.key() == key) ? x : (key < x.key() ? search_(x.left(), key) : search_(x.right(), key));}
  public Node search(Node x) {return search_(root, x.key());}
  public Node search(int key) {return search_(root, key);}
  
  private Node max_(Node x) {return x == null || x.right() == null ? x : max_(x.right());}
  public Node max() {return max_(root);}
  
  private Node min_(Node x) {return x == null || x.left() == null ? x : min_(x.left());}
  public Node min() {return min_(root);}

  private int maxHeight_(Node n) {
    if (n == null) return 0;
    else return Math.max(maxHeight_(n.left()), maxHeight_(n.right())) + 1;

  }

  public int maxHeight() {return maxHeight_(root);}

  private Node find(int key, String id) {
    Node x = search(key);
    
    if (id.equals("next") ? x.right() != null : x.left() != null) {
      return id.equals("next") ? min_(x.right()) : max_(x.left());
      
    } else {
      Node y = x.parent();
      
      while (y != null && (id.equals("next") ? x != y.left() : x != y.right())) {
        x = y; x.setParent(y);
        
      }
      
      return y;
      
    }
  }
  
  public Node next(Node x) {return find(x.key(), "next");}
  public Node previous(Node x) {return find(x.key(), "previous");}
  public Node next(int key) {return find(key, "next");}
  public Node previous(int key) {return find(key, "previous");}
  
  public void insert(Node x) {
    Node y = null, z = root;
    boolean STOP = false;
    
    while (z != null && STOP == false) {
      if (z.key() == x.key()) {
        System.out.println("~Chiave già inserita: " + x.key() + "\n");
        STOP = true;
        
      }
      
      y = z;
      z = x.key() < y.key() ? y.left() : y.right();
      
    }
    
    if (STOP == false) {
      
      if (y == null) {
        root = x;
        
      } else {
        x.setParent(y);
        
        if (x.key() < y.key()) {
          y.setLeft(x);
          
        } else {
          y.setRight(x);
          
        }
      }
    }
  }
  
  public void insert(int key) {insert(new Node(key));}
  
  private int children(Node x) {return (x.left() == null ? 0 : 1) + (x.right() == null ? 0 : 1);}
  
  private void setSon(Node x, Node y) {
    if (y != null) {
      y.setParent(x);
      
      if (x != null) {
        
        if (y.key() < x.key()) {
          x.setLeft(y);
          
        } else {
          x.setRight(y);
          
        }
      }
    }
  }
  
  public void delete(Node x) {
    if (x != null) {
      
      if (children(x) == 0) {
        
        if (x != root) {
          if (x.parent().left() == x) x.parent().setLeft(null);
          else if (x.parent().right() == x) x.parent().setRight(null);
          
        } else root = null;
        
      } else if (children(x) == 1) {
        
        if (x != root) setSon(x.parent(), (x.left() == null ? x.right() : x.left()));
        
        else {
          root = x.left() == null ? x.right() : x.left();
          root.setParent(null);
          
        }
        
      } else {
        int k = next(x.key()).key();
        delete(next(x.key()));
        x.setKey(k);
        
      }
      
    } else System.out.println("Impossibile eliminare null");
  }
  
  public void delete(int key) {delete(search(key));}
  
  public void printNode(Node x) {
    if (x != null) {
      System.out.println("~ key: " + x.key());
      
      if (x.parent() != null) System.out.println("  - parent: " + x.parent().key());
      if (x.parent() == null) {System.out.println("  - parent: null");}
      
      if (x.left() != null) System.out.println("  - left: " + x.left().key());
      if (x.left() == null) System.out.println("  - left: null");
      
      if (x.right() != null) System.out.println("  - right: " + x.right().key());
      if (x.right() == null)System.out.println("  - right: null");
      
      if (x.left() == null && x.right() == null) System.out.println(x.parent() == null ? "  _ROOT_&_LEAVE_\n" : "  _LEAVE_\n");
      else if (x.parent() == null) System.out.println("  _ROOT_\n");
      else System.out.println("  _NODE_\n");
      
    } else System.out.println("null");
  }
  
  private void print_(Node x) {
    printNode(x);
    if (x != null && x.left() != null) print_(x.left());
    if (x != null && x.right() != null) print_(x.right());
    
  }
  
  public void printTree() {print_(root);}
  
}