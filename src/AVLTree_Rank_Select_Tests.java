import java.util.*;

/**
 *&nbsp;&nbsp;&nbsp;&nbsp;    Instructions:                                       <br/><br/>
 *
 * mainTests() will run a set of tests of random instances of insertions
 * and Rank() and select() calls                                                            <br/><br/>
 * The tests will halt if the algorithm finds a mistake and explain why it failed.
 *
 * At the end of the failed instance presentation, the last line will have
 * a function call printed out which you can copy and paste to main
 * and run it to debug the failed test                                            <br/><br/>
 *
 * <b>IMPORTANT</b> : If the tests get stuck on something or something doesn't run correctly,
 * that's likely an indicator that there's an issue in your code somewhere.
 * if your code don't have any issues, the test should run smoothly.               <br/><br/>
 *
 * You can edit SelectDebugging() and RankDebugging() as much as you like for debugging but don't
 * edit anything else.
 *
 * @author Yuval Roth
 */
public class AVLTree_Rank_Select_Tests {

    public static final int version = 8;

    public static void main(String[] args){

        boolean Read_The_Instructions = true;
        if(!Read_The_Instructions) throw new RuntimeException("READ THE INSTRUCTIONS FIRST!");


        // main tests
            main_tests();

        /*   example call from end of failed instance presentation  */
        //SelectDebugging(new int[]{-432, 434, -102, -218, 251});
    }
    public static void SelectDebugging(int[] nums){
        BacktrackingAVL tree = new BacktrackingAVL();
        for (int i = 0; i < nums.length; i++) {
                tree.insert(nums[i]);
            }

        /*   Add your code here    */

    }
    public static void RankDebugging(int[] nums){
        BacktrackingAVL tree = new BacktrackingAVL();
        for (int i = 0; i < nums.length; i++) {
            tree.insert(nums[i]);
        }
        System.out.println(tree.Rank(67));
        /*   Add your code here    */
    }


    //==========================================
    // DO NOT EDIT THE FUNCTIONS BELOW THIS LINE
    //==========================================


    public static boolean Select_Test(int count){

        /** DON'T EDIT THIS FUNCTION */

        boolean ambiguity = true;
        BacktrackingAVL tree = new BacktrackingAVL();
        try{
            tree.Select(5);
        }
        catch(NullPointerException e){
            System.out.println("Empty tree Select() failed: "+e);
            return false;
        }
        catch(NoSuchElementException e){

        }
        catch(Exception e ){
            if(ambiguity) {
                System.out.println("Empty tree select() test result is ambiguous. the result could be good or bad, but i can't tell.");
                System.out.println("Exception thrown in the empty tree test: " + e);
                System.out.println("Continuing tests.");
                ambiguity = false;
            }
        }
        int successCounter = 0;
        while (successCounter <= 30000000/(count*count)) {
            tree = new BacktrackingAVL();
            Random random = new Random();
            HashSet<Integer> set = new HashSet<>(count*2);
            int[] nums = new int[count];
            for (int i = 0; i < count; ) {
                int num = random.nextInt(500);
                if (set.contains(num) == false) {
                    set.add(num);
                    tree.insert(num);
                    nums[i] = num;
                    i++;
                }
            }
            int[] sortedNums = Arrays.stream(nums).sorted().toArray();
            int selectTracker = 0;
            try{
                for(; selectTracker < count; selectTracker++){
                    if(tree.Select(selectTracker+1) != sortedNums[selectTracker]){
                        throw new InputMismatchException("mismatch at Select("+ (selectTracker+1)+")");
                    }
                }
            }
            catch(InputMismatchException e){
                System.out.println("Select() failed");
                System.out.println("Exception occurred here: "+e);
                System.out.println("input: "+numsArrayToString(nums));
                System.out.println("Failed at index: "+(selectTracker+1));
                System.out.println("Expected: "+ sortedNums[selectTracker]);
                try{
                    System.out.println("Actual: "+ tree.Select(selectTracker+1));
                }
                catch(Exception k){
                    System.out.println("Actual: "+ k);
                }
                System.out.println();
                System.out.println("You can debug your code by calling this from main: |  SelectDebugging(new int[]"+numsArrayToString(nums)+");  |");
                return false;
            }
            catch(Exception e){
                System.out.println("Select() failed");
                System.out.println("Exception occurred here: "+e);
                System.out.println("input: "+numsArrayToString(nums));
                System.out.println("Failed at index: "+(selectTracker+1));
                System.out.println();
                System.out.println("You can debug your code by calling this from main: |  SelectDebugging(new int[]"+numsArrayToString(nums)+");  |");
                return false;
            }
            successCounter++;
        }
        System.out.println("Select() test passed for tree size: "+count);
        System.out.println();
        return true;
    }
    public static boolean Rank_Test(int count){

        /** DON'T EDIT THIS FUNCTION */


        BacktrackingAVL tree = new BacktrackingAVL();
        try{
            if(tree.Rank(5) != 0){
                throw new Exception("Expected and actual are not the same");
            }
        }
        catch(NullPointerException e){
            System.out.println("Empty tree Rank(5) failed: "+e);
            return false;
        }
        catch(Exception e){
            System.out.println("Empty tree Rank(5) failed: "+e);
            System.out.println("Expected: 0");
            try{
                System.out.println("Actual: "+tree.Rank(5));
            }
            catch(Exception k){
                System.out.println("Actual: "+ k.getMessage());
            }
            return false;
        }
        int successCounter = 0;
        while (successCounter <= 30000000/(count*count)) {
            tree = new BacktrackingAVL();
            Random random = new Random();
            HashSet<Integer> set = new HashSet<>(count*2);
            int[] nums = new int[count];
            for (int i = 0; i < count; ) {
                int num = random.nextInt(500);
                if (set.contains(num) == false) {
                    set.add(num);
                    tree.insert(num);
                    nums[i] = num;
                    i++;
                }
            }
            int[] sortedNums = Arrays.stream(nums).sorted().toArray();
            int rankTracker = -501;
            int index = 0;
            int rank = 0;
            try{
                for(; rankTracker < 501; rankTracker++){

                    if(tree.Rank(rankTracker) != rank){
                        throw new InputMismatchException("mismatch at Rank("+ rankTracker+")");
                    }
                    if(rankTracker == sortedNums[index]){
                       if(index != sortedNums.length-1){
                           index++;
                       }
                       rank++;
                    }
                }
            }
            catch(InputMismatchException e){
                System.out.println("Rank() failed");
                System.out.println("Exception occurred here: "+e);
                System.out.println("input: "+numsArrayToString(nums));
                System.out.println("Failed at value: "+rankTracker);
                System.out.println("Expected: "+ rank);
                System.out.println("Actual: "+ tree.Rank(rankTracker));
                System.out.println();
                System.out.println("You can debug your code by calling this from main: |  RankDebugging(new int[]"+numsArrayToString(nums)+");  |");
                return false;
            }
            catch(Exception e){
                System.out.println("Rank() failed");
                System.out.println("Exception occurred here: "+e);
                System.out.println("input: "+numsArrayToString(nums));
                System.out.println("Failed at value: "+rankTracker);
                System.out.println();
                System.out.println("You can debug your code by calling this from main: |  RankDebugging(new int[]"+numsArrayToString(nums)+");  |");
                return false;
            }
            successCounter++;
        }
        System.out.println("Rank() test passed for tree size: "+count);
        System.out.println();
        return true;
    }
    public static String numsArrayToString(int[] nums){

        /** DON'T EDIT THIS FUNCTION */

        String numsArray = "{";
        for (int i = 0; i < nums.length; i++) {
            if (i != nums.length - 1)
                numsArray+=(nums[i] + ", ");
            else numsArray+=(nums[i]);
        }
        numsArray+="}";
        return numsArray;
    }
    public static void main_tests(){

        /** DON'T EDIT THIS FUNCTION */

        System.out.println("Tests version: "+version);
        if(Select_Test(5)){
            if(Select_Test(25)){
                if(Select_Test(50)){
                    if(Select_Test(250)){
                        System.out.println("This part might take a while...");
                        if(Rank_Test(5)){
                            if(Rank_Test(25)){
                                if(Rank_Test(50)){
                                    if(Rank_Test(250)){
                                        System.out.println("LIKE A BAWS");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
