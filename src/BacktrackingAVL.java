import java.util.List;

public class BacktrackingAVL extends AVLTree {
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }

	//You are to implement the function Backtrack.
    public void Backtrack() {
        // You should remove the next two lines, after double-checking that the signature is valid!
        IntegrityStatement.signature(); // Reminder!
        throw new UnsupportedOperationException("You should implement this");
    }
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() {
        // You should remove the next two lines, after double-checking that the signature is valid!
        IntegrityStatement.signature(); // Reminder!
        throw new UnsupportedOperationException("You should implement this");
    }
    
    public int Select(int index) {
        return root.helpSelect(index);
    }
    
    public int Rank(int value) {
        return root.helpRank(value);
    }
}
