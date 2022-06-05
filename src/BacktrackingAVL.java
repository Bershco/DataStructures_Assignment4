import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }

	//You are to implement the function Backtrack.
    public void Backtrack() {
        Object[] information = backtrackingADT.removeFirst();
        if (information[0].equals(info.INSERTION)) {
            Object[] followup1 = backtrackingADT.removeFirst();
            if (followup1[0].equals(info.INSERTION)) {
                backtrackingADT.addFirst(followup1);
            }
            else { //then followup1[0] is info.ROTATION
                Object[] followup2 = backtrackingADT.removeFirst();
                if (followup2[0].equals(info.INSERTION)) {
                    backtrackingADT.addFirst(followup2);
                }
            }
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
