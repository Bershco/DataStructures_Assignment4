import java.util.Arrays;
import java.util.List;

public class BacktrackingAVL extends AVLTree {
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }

	//You are to implement the function Backtrack.
    public void Backtrack() {
        if (!backtrackingADT.isEmpty()) {
            Object info1 = backtrackingADT.removeFirst();
            Object info2 = backtrackingADT.removeFirst();
            Object info3 = backtrackingADT.removeFirst();
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
                Node inserted = (Node) backtrackingADT.removeFirst();
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
                Node parent = (Node) backtrackingADT.removeFirst();
                Node inserted = (Node) backtrackingADT.removeFirst();
                Node parent3 = rotatedSecond.parent;
                Node parent2 = rotatedFirst.parent;
                char son2 = 'L',son3 = 'L';
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
                        needed.right = rotatedFirst;
                        rotatedFirst.parent = needed;
                        rotatedSecond.right = rotatedFirst.left;
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
                        needed.left = rotatedFirst;
                        rotatedFirst.parent = needed;
                        rotatedSecond.left = rotatedFirst.right;
                        rotatedFirst.right = rotatedSecond;
                        rotatedSecond.parent = rotatedFirst;
                    }
                }
                fixRoot();
                removeInserted(inserted, parent);
            }
        }
    }
    private void removeInserted(Node child, Node parent) {
        child.parent = null;
        if (parent.right != null && parent.right.equals(child)) {
            parent.right = null;
        } else {
            parent.left = null;
        }
    }
    private void fixRoot() {
        if (root.parent != null) {
            root = root.parent;
            fixRoot();
        }
    }
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() {
        return Arrays.asList(2,1,5,3,6,4);
    }
    
    public int Select(int index) {
        if (root != null) {
            return root.helpSelect(index);
        }
        else {
            return 0;
        }
    }
    
    public int Rank(int value) {
        if (root != null) {
            return root.helpRank(value);
        }
        else {
            return 0;
        }
    }
}
