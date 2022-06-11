import java.util.Arrays;
import java.util.List;

public class BacktrackingAVL extends AVLTree {
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }

    /**
     * This method backtracks the insertion method in AVL Trees
     */
    public void Backtrack() {
        if (!backtrackingDeque.isEmpty()) {
            Object info1 = backtrackingDeque.removeFirst();
            Object info2 = backtrackingDeque.removeFirst();
            Object info3 = backtrackingDeque.removeFirst();
            if (info1.equals(ImbalanceCases.NO_IMBALANCE)) {
                if (info2.equals(ImbalanceCases.NO_IMBALANCE)) {
                    root = null;
                    return;
                }
                Node parent = (Node) info2;
                Node inserted = (Node) info3;
                removeInserted(inserted,parent);
            } else if (info2.equals(ImbalanceCases.LEFT_LEFT) || info2.equals(ImbalanceCases.RIGHT_RIGHT)) {
                Node singleRotated = (Node) info1;
                Node parent = (Node) info3;
                Node inserted = (Node) backtrackingDeque.removeFirst();
                Node parent2 = singleRotated.parent;
                char son2 = 'L';
                if (info2.equals(ImbalanceCases.LEFT_LEFT)) {
                    if (parent2 != null) {
                        if (parent2.right.equals(singleRotated)) {
                            son2 = 'R';
                        }
                        singleRotated = rotateLeft(singleRotated);
                        if (son2 == 'L') {
                            parent2.left = singleRotated;
                        } else {
                            parent2.right = singleRotated;
                        }
                    } else { //then singleRotated is root
                        singleRotated = rotateLeft(singleRotated);
                        root = singleRotated;
                    }
                } else {
                    if (parent2 != null) {
                        if (parent2.right.equals(singleRotated)) {
                            son2 = 'R';
                        }
                        singleRotated = rotateRight(singleRotated);
                        if (son2 == 'L') {
                            parent2.left = singleRotated;
                        } else {
                            parent2.right = singleRotated;
                        }
                    } else { //then singleRotated is root
                        singleRotated = rotateRight(singleRotated);
                        root = singleRotated;
                    }
                }
                removeInserted(inserted,parent);
                fixRoot();
            }
            else {
                Node rotatedSecond = (Node) info1;
                Node rotatedFirst = (Node) info2;
                Node parent = (Node) backtrackingDeque.removeFirst();
                Node inserted = (Node) backtrackingDeque.removeFirst();
                if (info3.equals(ImbalanceCases.RIGHT_LEFT)) {
                    Node needed = rotatedSecond.left;
                    if (rotatedSecond.parent != null) {
                        if (rotatedSecond.parent.left != null && rotatedSecond.parent.left.equals(rotatedSecond)) {
                            rotatedSecond.parent.left = needed;
                        } else if (rotatedSecond.parent.right != null && rotatedSecond.parent.right.equals(rotatedSecond)) {
                            rotatedSecond.parent.right = needed;
                        }
                    }
                    if (needed != null) {
                        needed.parent = rotatedSecond.parent;
                        rotatedSecond.left = needed.right;
                        if (needed.right != null) {
                            needed.right.parent = rotatedSecond;
                        }
                        needed.right = rotatedFirst;
                        rotatedFirst.parent = needed;
                        rotatedSecond.right = rotatedFirst.left;
                        if (rotatedFirst.left != null) {
                            rotatedFirst.left.parent = rotatedSecond;
                        }
                        rotatedFirst.left = rotatedSecond;
                        rotatedSecond.parent = rotatedFirst;
                    }

                } else {
                    Node needed = rotatedSecond.right;
                    if (rotatedSecond.parent != null) {
                        if (rotatedSecond.parent.left != null && rotatedSecond.parent.left.equals(rotatedSecond)) {
                            rotatedSecond.parent.left = needed;
                        } else if (rotatedSecond.parent.right != null && rotatedSecond.parent.right.equals(rotatedSecond)) {
                            rotatedSecond.parent.right = needed;
                        }
                    }
                    if (needed != null) {
                        needed.parent = rotatedSecond.parent;
                        rotatedSecond.right = needed.left;
                        if (needed.left != null) {
                            needed.left.parent = rotatedSecond;
                        }
                        needed.left = rotatedFirst;
                        rotatedFirst.parent = needed;
                        rotatedSecond.left = rotatedFirst.right;
                        if (rotatedFirst.right != null) {
                            rotatedFirst.right.parent = rotatedSecond;
                        }
                        rotatedFirst.right = rotatedSecond;
                        rotatedSecond.parent = rotatedFirst;
                    }
                }
                fixRoot();
                removeInserted(inserted, parent);
            }
        }
    }

    /**
     * This method removes a Node from an AVL tree
     * @param child the Node to be removed
     * @param _parent the parent Node of the Node to be removed
     */
    private void removeInserted(Node child, Node _parent) {
        child.parent = null;
        if (_parent.right != null && _parent.right.equals(child)) {
            _parent.right = null;
        } else {
            _parent.left = null;
        }
        _parent.size = 2;
        while (_parent.parent != null) {
            _parent.size--;
            _parent = _parent.parent;
        }
        root.decreaseSize(child.value);
    }

    /**
     * This method fixes the root field if it isn't correct
     */
    private void fixRoot() {
        if (root.parent != null) {
            root = root.parent;
            fixRoot();
        }
    }

    /**
     * This method is an example of an AVL tree that after backtracking insertion is different from deleting the inserted Node
     * @return a list of Integers that is representing the tree
     */
    public static List<Integer> AVLTreeBacktrackingCou1nterExample() {
        return Arrays.asList(2,1,5,3,6,4);
    }

    /**
     * This method returns the value of the <code>index</code> smallest Node within the tree
     * @param index the index of the Node, given that the tree is in-order
     * @return the value within the node in the <code>index</code> place
     */
    public int Select(int index) {
        return (root != null) ? root.helpSelect(index) : 0;
    }

    /**
     * This method returns the amount of Nodes that their value is lower than 'value'
     * @param value the value to check
     * @return the amount of Nodes that their value is lower than 'value'
     */
    public int Rank(int value) {
        return (root != null) ? root.helpRank(value) : 0;
    }
}
