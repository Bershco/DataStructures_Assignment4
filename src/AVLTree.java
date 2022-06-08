import java.util.*;

public class AVLTree implements Iterable<Integer> {
    // You may edit the following nested class:
    protected class Node {
    	public Node left = null;
    	public Node right = null;
    	public Node parent = null;
    	public int height = 0;
    	public int value;
        protected int size = 1;

    	public Node(int val) {
            this.value = val;
        }

        public void updateHeight() {
            int leftHeight = (left == null) ? -1 : left.height;
            int rightHeight = (right == null) ? -1 : right.height;

            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public int getBalanceFactor() {
            int leftHeight = (left == null) ? -1 : left.height;
            int rightHeight = (right == null) ? -1 : right.height;

            return leftHeight - rightHeight;
        }
        protected int helpRank(int _value) {
            if (value == _value) {
                return (left != null) ? left.size : 0;
            }
            else if (value > _value) {
                return (left != null) ? left.helpRank(_value) : 0;
            }
            else {
                return (right != null) ? right.helpRank(_value) + ((left != null) ? left.size + 1 : 1) : ((left != null) ? left.size + 1 : 1);
            }
        }
        protected int helpSelect(int index) {
            int rank = (left != null) ? left.size + 1 : 1;
            if (rank == index) {
                return value;
            }
            else if (rank > index) {
                return (left != null) ? left.helpSelect(index) : -1;
            }
            else {
                return (right != null) ? right.helpSelect(index-rank) : -1;
            }
        }
    }
    
    protected Node root;
    
    //You may add fields here.
    protected Deque<Object[]> backtrackingADT = new ArrayDeque<>();
    
    public AVLTree() {
    	this.root = null;
    }
    
    /*
     * IMPORTANT: You may add code to both "insert" and "insertNode" functions.
     */
	public void insert(int value) {
    	root = insertNode(root, value);
    }
	
	protected Node insertNode(Node node, int value) {
        Object[] info = new Object[5];
        // Perform regular BST insertion
        if (node == null) {
        	Node insertedNode = new Node(value);
            info[0] = insertedNode;
            return insertedNode;
        }

        if (value < node.value) {
            node.left  = insertNode(node.left, value);
            node.left.parent = node;
        }
        else {
            node.right = insertNode(node.right, value);
            node.right.parent = node;
        }
        if ((node.right != null & node.right.value == ((Node)info[0]).value) ||
                (node.left != null & node.left.value == ((Node)info[0]).value)) {
            info[1] = node;
        } //this will happen only once - when 'node' is info[0]'s parent
        node.size++;

        node.updateHeight();

        /* 
         * Check For Imbalance, and fix according to the AVL-Tree Definition
         * If (balance > 1) -> Left Cases, (balance < -1) -> Right cases
         */
        
        int balance = node.getBalanceFactor();
        if (info[4] == null) { //only needs to happen in the lowest imbalanced node
            if (balance > 1) {
                if (value > node.left.value) {
                    node.left = rotateLeft(node.left);
                    info[2] = node.left;
                }

                node = rotateRight(node);
                info[3] = node;
                if (info[2] != null) {
                    info[4] = ImbalanceCases.LEFT_RIGHT;
                } else {
                    info[4] = ImbalanceCases.LEFT_LEFT;
                }
                backtrackingADT.addFirst(info);
            } else if (balance < -1) {
                if (value < node.right.value) {
                    node.right = rotateRight(node.right);
                    info[2] = node.right;
                }

                node = rotateLeft(node);
                info[3] = node;
                if (info[2] != null) {
                    info[4] = ImbalanceCases.RIGHT_LEFT;
                } else {
                    info[4] = ImbalanceCases.RIGHT_RIGHT;
                }
                backtrackingADT.addFirst(info);
            }
            if (info[4] == null && node.equals(root)) {
                info[4] = ImbalanceCases.NO_IMBALANCE;
                backtrackingADT.addFirst(info);
            }
        }

        return node;
    }
    
	// You may add additional code to the next two functions.
    protected Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;
        
        //Update parents
        if (T2 != null) {
        	T2.parent = y;
        }

        x.parent = y.parent;
        y.parent = x;
        
        y.updateHeight();
        x.updateHeight();

        /*
            Now properly adjusting sizes - x is exactly where y was before, and now y is x's right child -
            meaning its size is as x minus the left child (if exists) minus 1.
         */
        x.size = y.size;
        y.size = (x.left != null) ? x.size - x.left.size -1 : x.size - 1;

        // Return new root
        return x;
    }

    protected Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;
        
        //Update parents
        if (T2 != null) {
        	T2.parent = x;
        }
        
        y.parent = x.parent;
        x.parent = y;
        
        x.updateHeight();
        y.updateHeight();

         /*
            Now properly adjusting sizes - y is exactly where x was before, and now x is y's left child -
            meaning its size is as y minus the right child (if exists) minus 1.
         */
        y.size = x.size;
        x.size = (y.right != null) ? y.size - y.right.size -1 : y.size - 1;

        // Return new root
        return y;
    }
    
    public void printTree() {
    	TreePrinter.print(this.root);
    }

    /***
     * A Printer for the AVL-Tree. Helper Class for the method printTree().
     * Not relevant to the assignment.
     */
    private static class TreePrinter {
        private static void print(Node root) {
            if(root == null) {
                System.out.println("(XXXXXX)");
            } else {    
                final int height = root.height + 1;
                final int halfValueWidth = 4;
                int elements = 1;
                
                List<Node> currentLevel = new ArrayList<Node>(1);
                List<Node> nextLevel    = new ArrayList<Node>(2);
                currentLevel.add(root);
                
                // Iterating through the tree by level
                for(int i = 0; i < height; i++) {
                    String textBuffer = createSpaceBuffer(halfValueWidth * ((int)Math.pow(2, height-1-i) - 1));
        
                    // Print tree node elements
                    for(Node n : currentLevel) {
                        System.out.print(textBuffer);
        
                        if(n == null) {
                            System.out.print("        ");
                            nextLevel.add(null);
                            nextLevel.add(null);
                        } else {
                            System.out.printf("(%6d)", n.value);
                            nextLevel.add(n.left);
                            nextLevel.add(n.right);
                        }
                        
                        System.out.print(textBuffer);
                    }
        
                    System.out.println();
                    
                    if(i < height - 1) {
                        printNodeConnectors(currentLevel, textBuffer);
                    }
        
                    elements *= 2;
                    currentLevel = nextLevel;
                    nextLevel = new ArrayList<Node>(elements);
                }
            }
        }
        
        private static String createSpaceBuffer(int size) {
            char[] buff = new char[size];
            Arrays.fill(buff, ' ');
            
            return new String(buff);
        }
        
        private static void printNodeConnectors(List<Node> current, String textBuffer) {
            for(Node n : current) {
                System.out.print(textBuffer);
                if(n == null) {
                    System.out.print("        ");
                } else {
                    System.out.printf("%s      %s",
                            n.left == null ? " " : "/", n.right == null ? " " : "\\");
                }
    
                System.out.print(textBuffer);
            }
    
            System.out.println();
        }
    }

    /***
     * A base class for any Iterator over Binary-Search Tree.
     * Not relevant to the assignment, but may be interesting to read!
     * DO NOT WRITE CODE IN THE ITERATORS, THIS MAY FAIL THE AUTOMATIC TESTS!!!
     */
    private abstract class BaseBSTIterator implements Iterator<Integer> {
        private List<Integer> values;
        private int index;
        public BaseBSTIterator(Node root) {
            values = new ArrayList<>();
            addValues(root);
            
            index = 0;
        }
        
        @Override
        public boolean hasNext() {
            return index < values.size();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            return values.get(index++);
        }
        
        protected void addNode(Node node) {
            values.add(node.value);
        }
        
        abstract protected void addValues(Node node);
    }
    
    public class InorderIterator extends BaseBSTIterator {
        public InorderIterator(Node root) {
            super(root);
        }

        @Override
        protected void addValues(Node node) {
            if (node != null) {
                addValues(node.left);
                addNode(node);
                addValues(node.right);
            }
        }    
      
    }
    
    public class PreorderIterator extends BaseBSTIterator {

        public PreorderIterator(Node root) {
            super(root);
        }

        @Override
        protected void addValues(AVLTree.Node node) {
            if (node != null) {
                addNode(node);
                addValues(node.left);
                addValues(node.right);
            }
        }        
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return getInorderIterator();
    }
    
    public Iterator<Integer> getInorderIterator() {
        return new InorderIterator(this.root);
    }
    
    public Iterator<Integer> getPreorderIterator() {
        return new PreorderIterator(this.root);
    }
}
