import java.util.Arrays;
import java.util.List;

public class BacktrackingAVL extends AVLTree {
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }

	//You are to implement the function Backtrack.
    public void Backtrack() {
        Object[] info = backtrackingADT.removeFirst();
        switch ((ImbalanceCases)info[4]) {
            case NO_IMBALANCE:
                removeInserted((Node) info[0], (Node) info[1]);
                break;
            case LEFT_LEFT:
                removeInserted((Node) info[0], (Node) info[1]);
                rotateLeft((Node)info[3]);
                break;

            case LEFT_RIGHT:
                removeInserted((Node) info[0], (Node) info[1]);
                rotateLeft((Node)info[3]);
                rotateRight((Node)info[2]);
                break;
            case RIGHT_RIGHT:
                removeInserted((Node) info[0], (Node) info[1]);
                rotateRight((Node)info[3]);
                break;
            case RIGHT_LEFT:
                removeInserted((Node) info[0], (Node) info[1]);
                rotateRight((Node)info[3]);
                rotateLeft((Node)info[3]);
                break;
        }
    }
    private void removeInserted(Node child, Node parent) {
        child.parent = null;
        if (parent.right.equals(child)) {
            parent.right = null;
        } else {
            parent.left = null;
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
