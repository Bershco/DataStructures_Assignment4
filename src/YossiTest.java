import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class YossiTest {
    public static boolean BTreeTest() {
        System.out.println("starting BTree test:");
        int size = (int) (Math.random() * 1100);
        int order = (int) (Math.random() * 20) + 2;
        System.out.println("size - " + size + " order - " + order);
        BacktrackingBTree ElayMatanya = new BacktrackingBTree<>(order);
        BacktrackingBTree Dadon;
        BacktrackingBTree[] Elays = new BacktrackingBTree[size];
        int[] arr = new int[size];
        int m = 0;
        boolean found = false;
        while (m<=arr.length){
            if(found)
                m--;
            if(m < arr.length) {
                int res = (int) (Math.random() * (2000 + size));
                found = false;
                for (int k = 0; k < arr.length; k++) {
                    if (arr[k] == res)
                        found = true;
                }
                if (!found & m >= 0) {
                    arr[m] = res;
                    ElayMatanya.insert(res);
                    Dadon = new BacktrackingBTree(order);
                    for (int j = 0; j <= m; j++) {
                        Dadon.insert(arr[j]);
                    }
                    Elays[m] = Dadon;
                }
            }
            m++;
        }
        System.out.println();
        System.out.println("array being tested - " + Arrays.toString(arr));
        System.out.println();
        System.out.println("starting backtrack BTree:");
        boolean passed = true;
        for (int i = arr.length - 2; i >= 0; i--) {
            ElayMatanya.Backtrack();
            String ElayMatanyaString = ElayMatanya.toString();
            String DadonString = Elays[i].toString();
            if (!ElayMatanyaString.equals(DadonString)) {
                System.out.println("for loop " + (i%(arr.length - 2)) + " failed");
                passed = false;
                break;
            }
        }
        if(passed)
            System.out.println("passed all tests");
        System.out.println();
        return passed;
    }

    public static boolean AVLTest() {
        System.out.println("starting AVL test:");
        BacktrackingAVL ElayMatanya = new BacktrackingAVL();
        BacktrackingAVL Dadon;
        int size = (int) (Math.random() * 1100);
        System.out.println("size - " + size);
        BacktrackingAVL[] Elays = new BacktrackingAVL[size];
        int[] arr = new int[size];
        boolean found = false;
        int m = 0;
        while (m<=arr.length){
            if(found)
                m--;
            if(m < arr.length) {
                int res = (int) (Math.random() * (2000 + size));
                found = false;
                for (int k = 0; k < arr.length; k++)
                    if (arr[k] == res)
                        found = true;
                if (!found & m >= 0) {
                    arr[m] = res;
                    ElayMatanya.insert(res);
                    Dadon = new BacktrackingAVL();
                    for (int j = 0; j <= m; j++) {
                        Dadon.insert(arr[j]);
                    }
                    Elays[m] = Dadon;
                }
            }
            m++;
        }
        System.out.println();
        boolean passed = true;
        System.out.println("array being tested - " + Arrays.toString(arr));
        System.out.println();
        System.out.println("starting backtrack AVL:");
        for (int i = arr.length - 2; i >= 0; i--) {
            ElayMatanya.Backtrack();
            Iterator<Integer> Elay = ElayMatanya.iterator();
            Iterator<Integer> dadon = Elays[i].iterator();

            while (Elay.hasNext()) {
                int temp = Elay.next();
                int temp2 = dadon.next();
                if (temp != temp2) {
                    System.out.println(" for loop " + i + " - temp = " + temp + " temp2 = " + temp2);
                    passed = false;
                    break;
                }
            }
            if (Elay.hasNext() | dadon.hasNext())
                System.out.println("for loop " + i + " AVL trees sizes are not equals");
        }
        if(passed)
            System.out.println("passed all tests");
        System.out.println();
        return passed;
    }

    public static boolean selectTest(){
        System.out.println("starting select tests:");
        BacktrackingAVL ElayMatanya = new BacktrackingAVL();
        int size = (int) (Math.random() * 1000);
        System.out.println("size - " + size);
        int[] arr = new int[size];
        boolean found = false;
        int m = 0;
        while (m<=arr.length){
            if(found)
                m--;
            if (m < arr.length) {
                int res = (int) (Math.random() * 1000);
                found = false;
                for (int k = 0; k < arr.length; k++) {
                    if (arr[k] == res)
                        found = true;
                }
                if (!found & m >= 0) {
                    arr[m] = res;
                    ElayMatanya.insert(res);
                }
            }
            m++;
        }
        Arrays.sort(arr);
        boolean passed = true;
        for(int i = arr.length; i > 0; i--){
            int g = ElayMatanya.Select(i);
            if(g != arr[i - 1]) {
                System.out.println("for loop " + i + " - select(" + i + ") = " + g + ", expected - " + arr[i - 1]);
                passed = false;
            }
        }
        if(passed)
            System.out.println("passed all tests");
        System.out.println();
        return passed;
    }

    public static boolean rankTest(){
        System.out.println("starting Rank tests:");
        BacktrackingAVL ElayMatanya = new BacktrackingAVL();
        int size = (int) (Math.random() * 1000);
        System.out.println("size - " + size);
        int[] arr = new int[size];
        boolean found = false;
        int m = 0;
        while (m<=arr.length){
            if(found)
                m--;
            if (m < arr.length) {
                int res = (int) (Math.random() * 1000);
                found = false;
                for (int k = 0; k < arr.length; k++) {
                    if (arr[k] == res)
                        found = true;
                }
                if (!found & m >= 0) {
                    arr[m] = res;
                    ElayMatanya.insert(res);
                }
            }
            m++;
        }
        Arrays.sort(arr);
        boolean passed = true;
        for(int i = arr.length - 1; i > 0; i--){
            int rank = ElayMatanya.Rank(arr[i]);
            if(rank != i) {
                System.out.println("for loop " + i + " - Rank(" + arr[i] + ") = " + rank + ", expected - " + arr[i - 1]);
                passed = false;
            }
        }

        if(passed){
            for(int i = arr.length; i > 0; i--){
                int val = (int) (Math.random() * 4000);
                int rank = ElayMatanya.Rank(val);
                IntStream stream = Arrays.stream(arr);
                int actual = (int) stream.filter(j -> j < val).count();
                if(rank != actual){
                    System.out.println("for the value - " + val + " , expected rank - " + actual + ", actual - " + rank);
                    passed = false;
                }
            }
        }
        if(passed)
            System.out.println("passed all tests");
        System.out.println();
        return passed;
    }

    public static void mixAVLTesting(){
        System.out.println("starting mixed testing:");
        BacktrackingAVL ElayMatanya = new BacktrackingAVL();
        BacktrackingAVL Dadon;
        int size = (int) (Math.random() * 1100);
        System.out.println("size - " + size);
        BacktrackingAVL[] Elay = new BacktrackingAVL[size];
        int[] arr = new int[size];
        boolean found = false;
        int m = 0;
        while (m<=arr.length){
            if(found)
                m--;
            if(m < arr.length) {
                int res = (int) (Math.random() * (2000 + size));
                found = false;
                for (int k = 0; k < arr.length; k++)
                    if (arr[k] == res)
                        found = true;
                if (!found & m >= 0) {
                    arr[m] = res;
                    ElayMatanya.insert(res);
                    Dadon = new BacktrackingAVL();
                    for (int j = 0; j <= m; j++) {
                        Dadon.insert(arr[j]);
                    }
                    Elay[m] = Dadon;
                }
            }
            m++;
        }
        System.out.println("array being tested - " + Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println();
        boolean passed = true;
        int numOfBack = (int) (Math.random() * (arr.length - 1));
        int j = 0;
        for (int i = numOfBack - 2; i > 0 & passed; i--) {
            ElayMatanya.Backtrack();
            Iterator<Integer> iter = ElayMatanya.iterator();
            Iterator<Integer> iter2 = Elay[arr.length - 2 - j].iterator();

            while (iter.hasNext()) {
                int temp = iter.next();
                int temp2 = iter2.next();
                if (temp != temp2) {
                    System.out.println(" for loop " + i + " - temp(on Backtracked AVL) = " + temp + " temp2(on the original AVL) = " + temp2);
                    passed = false;
                }
            }
            if (iter.hasNext() | iter2.hasNext())
                System.out.println("for loop " + i + " AVL trees sizes are not equals");
            if(!passed)
                System.out.println("failed on Backtracking test, for the " + (i%(numOfBack - 2)) +" loop");
            if(passed) {
                int ElaysRank = (int) (Math.random() * 4000);
                int BacktrackRank = ElayMatanya.Rank(ElaysRank);
                int expectedRank = Elay[arr.length - 2 - j].Rank(ElaysRank);
                if (BacktrackRank != expectedRank) {
                    System.out.println("for the value - " + ElaysRank + " , expected rank - " + expectedRank + ", actual - " + BacktrackRank);
                    passed = false;
                }
            }
            if(!passed)
                System.out.println("failed on Rank test, for the " + (i%(numOfBack - 2)) +" loop");
            if(passed){
                int g = ElayMatanya.Select(i);
                if(g != Elay[arr.length - 2 - j].Select(i)) {
                    System.out.println("for loop " + i + " - select(" + i + ") = " + g + ", expected - " + arr[i - 1]);
                    passed = false;
                }
            }
            if(!passed)
                System.out.println("failed on select test, for the " + (i%(numOfBack - 2)) +" loop");
            j++;
        }
        if(passed)
            System.out.println("passed all tests");
    }



    public static void main(String[] args) {
        System.out.println("thanks for choosing Elay Matanya Dadon's Tests\nhave fun!!!\n");
        boolean passed = AVLTest() & selectTest() & rankTest();
        if(passed)
            mixAVLTesting();
    }
}


