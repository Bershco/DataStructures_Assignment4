import java.util.Arrays;
import java.util.List;

public class BacktrackingAVL extends AVLTree {
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }

	//You are to implement the function Backtrack.
    public void Backtrack() {
        Object[] firstOut = !backtrackingADT.isEmpty() ? backtrackingADT.removeFirst() : new Object[]{info.empty};
        Object[] secondOut = !backtrackingADT.isEmpty() ? backtrackingADT.removeFirst() : new Object[]{info.empty};
        Object[] thirdOut = !backtrackingADT.isEmpty() ? backtrackingADT.removeFirst() : new Object[]{info.empty};
        Object[] inserted,firstRotation,secondRotation;
        /*
            The next if & else if & else clauses determine the proper objects inserted,firstRotation,secondRotation - if they're properly defined.
            also puts back information about backtracking unrelated to the current backtrack.
         */
        if (firstOut[0].equals(info.insertion)) {
            inserted = firstOut;
            firstRotation = new Object[]{info.empty};
            secondRotation = new Object[]{info.empty};
            if (!thirdOut[0].equals(info.empty)) {
                backtrackingADT.addFirst(thirdOut);
            }
            if (!secondOut[0].equals(info.empty)) {
                backtrackingADT.addFirst(secondOut);
            }
        } else if (secondOut[0].equals(info.insertion)) {
            inserted = secondOut;
            firstRotation = firstOut;
            secondRotation = new Object[]{info.empty};
            if (!thirdOut[0].equals(info.empty)) {
                backtrackingADT.addFirst(thirdOut);
            }
        } else {
            inserted = thirdOut;
            firstRotation = secondOut;
            secondRotation = firstOut;
        }
        if (inserted[0].equals(info.insertion)) {
            /*  The conditional operator above sets both variables as a proper array of rotations
                Or, if the backtracking deque is empty (either by the first or by the second), it sets
                the variable(s) as an array containing ONLY info.empty - as this is an option
                the rest of the code uses to stop itself from canceling a non-existing rotation.
             */
            if (firstRotation[0].equals(info.rightRotation) || firstRotation[0].equals(info.leftRotation)) { //then possibleRotation1 is information about a rotation
                Node x = (Node) firstRotation[1];
                Node y = (Node) firstRotation[2];
                Node T2 = (Node) firstRotation[3];
                if (secondRotation[0].equals(info.insertion) || secondRotation[0].equals(info.empty)) { //the rotation was either right-right or left-left (i.e a single rotation)
                    //starting next line, is the code to remove the inserted node from the AVL tree efficiently whilst undoing the single rotation
                    if (firstRotation[0].equals(info.leftRotation)) {
                        if (T2 != null) {
                             T2.parent = y;
                        }
                        x.right = y;
                        x.parent = y.parent;
                        if (x.parent != null) {
                            if (x.parent.right == y) {
                                x.parent.right = x;
                            } else {
                                x.parent.left = x;
                            }
                        }
                        y.left = T2;
                        y.parent = x;
                    }
                    else if (firstRotation[0].equals(info.rightRotation)) {
                        if (T2 != null) {
                            T2.parent = y;
                        }
                        x.left = y;
                        x.parent = y.parent;
                        if (x.parent != null) {
                            if (x.parent.right == y) {
                                x.parent.right = x;
                            } else {
                                x.parent.left = x;
                            }
                        }
                        y.right = T2;
                        y.parent = x;
                    }
                    else {
                        throw new IllegalArgumentException("The first element of the object array should be information about the action performed");
                    }
                } else {
                    /*  then both possibleRotation1 and possibleRotation2 are information about rotations (either left & right or right & left)
                        starting next line, is the code to remove the inserted node from the AVL tree efficiently whilst undoing the double rotation */
                    if (firstRotation[0].equals(info.rightRotation) && secondRotation[0].equals(info.leftRotation)) {
                        if (T2 != null) {
                            x.parent = T2.parent;
                            T2.parent = y;
                            T2.right = y.left;
                            T2.left = x.right;
                        }
                        y.parent = x;
                        x.right = y;
                        y.left = T2;
                    } else if (firstRotation[0].equals(info.leftRotation) && secondRotation[0].equals(info.rightRotation)) {
                        if (T2 != null) {
                            x.parent = T2.parent;
                            T2.parent = y;
                            T2.left = y.right;
                            T2.right = x.left;
                        }
                        y.parent = x;
                        x.left = y;
                        y.right = T2;
                    } else throw new IllegalArgumentException("How did you even get here?");
                }
            }
            //starting next line, is the code to remove the actual node from the AVL tree efficiently now that the rotations have been undone
            Node insertedNode = (Node) inserted[1];
            Node insertedNodeParent = (Node) inserted[2];
            insertedNode.parent = null;
            if (insertedNodeParent != null) {
                if (insertedNode.value < insertedNodeParent.value) {
                    insertedNodeParent.left = null;
                } else if (insertedNode.value > insertedNodeParent.value) {
                    insertedNodeParent.right = null;
                }
            }
            Node recurringParent = insertedNodeParent;
            while (recurringParent != null) {
                recurringParent.updateHeight();
                recurringParent = recurringParent.parent;
            }   //Reset the parent field of the node inserted, and the proper child field (left\right) of the inserted node's parent
            root.updateSizeDownwardsRecursively(insertedNode.value);
        }
    }
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() {
        return Arrays.asList(2,1,5,3,6,4);
    }
    
    public int Select(int index) {
        return root.helpSelect(index);
    }
    
    public int Rank(int value) {
        return root.helpRank(value);
    }
}
