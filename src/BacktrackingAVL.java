import java.util.LinkedList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }

	//You are to implement the function Backtrack.
    public void Backtrack() {
        Object[] inserted = backtrackingADT.removeFirst();
        if (inserted[0].equals(info.insertion)) {
            Object[] possibleRotation1 = !backtrackingADT.isEmpty() ? backtrackingADT.removeFirst() : null;
            Object[] possibleRotation2 = !backtrackingADT.isEmpty() ? backtrackingADT.removeFirst() : null;
            if (possibleRotation1 != null && possibleRotation1[0].equals(info.insertion)) {
                if (possibleRotation2 != null) {
                    backtrackingADT.add(possibleRotation2);
                }
                backtrackingADT.addFirst(possibleRotation1);
                //starting next line, is the code to remove the node from the AVL tree efficiently
                Node insertedNode = (Node) inserted[1];
                Node insertedNodeParent = (Node) inserted[2];
                insertedNode.parent = null;
                if (insertedNode.value < insertedNodeParent.value) {
                    insertedNodeParent.left = null;
                }
                else if (insertedNode.value > insertedNodeParent.value) {
                    insertedNodeParent.right = null;
                }
                insertedNodeParent.updateHeight();
                //Reset the parent field of the node inserted, and the proper child field (left\right) of the inserted node's parent
            }
            else { //then possibleRotation1 is information about a rotation
                if (possibleRotation2 != null && possibleRotation2[0].equals(info.insertion)) { //the rotation was either right-right or left-left
                    backtrackingADT.addFirst(possibleRotation2);
                    //starting next line, is the code to remove the inserted node from the AVL tree efficiently whilst undoing the single rotation
                    if (possibleRotation1 != null) {
                        Node x = (Node) possibleRotation1[1];
                        Node y = (Node) possibleRotation1[2];
                        Node T2 = (Node) possibleRotation1[3];
                        if (possibleRotation1[0].equals(info.leftRotation)) {
                            if (T2 != null) {
                                 T2.parent = y;
                            }
                            x.right = y;
                            x.parent = y.parent;
                            if (x.parent.right == y) {
                                x.parent.right = x;
                            } else {
                                x.parent.left = x;
                            }
                            y.left = T2;
                            y.parent = x;
                        }
                        else if (possibleRotation1[0].equals(info.rightRotation)) {
                            if (T2 != null) {
                                T2.parent = y;
                            }
                            x.left = y;
                            x.parent = y.parent;
                            if (x.parent.right == y) {
                                x.parent.right = x;
                            } else {
                                x.parent.left = x;
                            }
                            y.right = T2;
                            y.parent = x;
                        }
                        else {
                            throw new IllegalArgumentException("The first element of the object array should be information about the action performed");
                        }
                    }
                }
                else {
                    /*  then both possibleRotation1 and possibleRotation2 are information about rotations (either left&right or right&left)
                        also - the rotation was either right-left or left-right
                        starting next line, is the code to remove the inserted node from the AVL tree efficiently whilst undoing the double rotation */

                }
            }
        }
        else {
            System.out.println("For some reason, the next object array in the backtracking deque is information about a rotation, and not about insertion, as needed.");
        }
    }
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() {
        // You should remove the next two lines, after double-checking that the signature is valid!
        List<Integer> difference = new LinkedList<>();
        difference.add(2);
        difference.add(1);
        difference.add(5);
        difference.add(3);
        difference.add(6);
        difference.add(4);
        return difference;
    }
    
    public int Select(int index) {
        // You should remove the next two lines, after double-checking that the signature is valid!
        IntegrityStatement.signature(); // Reminder!
        throw new UnsupportedOperationException("You should implement this");
    }
    
    public int Rank(int value) {
        // You should remove the next two lines, after double-checking that the signature is valid!
        IntegrityStatement.signature(); // Reminder!
        throw new UnsupportedOperationException("You should implement this");
    }
}
