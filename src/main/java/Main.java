import compulsory.*;
import optional.*;
import bonus.*;

public class Main {
    static void testCompulsory() {
        Worker worker = new Worker();
        worker.run();
    }

    static void testOptional(String[] args) {
        long startTime = System.nanoTime();

        MatrixHandler matrix = MatrixHandler.getInstance(args);

        if(matrix == null) {
            MatrixHandler.log("Error at getting instance");
            return;
        }

        matrix.generateRandomGraph();

        if(matrix.isConnected()) {
            matrix.getPartialTree();
        }

        System.out.println((System.nanoTime() - startTime) / 1000000);
    }

    static void testBonus() {
        MyTree tree = new MyTree(0, 3);
        tree.generateRandomTree();
        tree.displayTree();
    }

    public static void main(String[] args) {
        // testCompulsory();
        testOptional(args);
        //testBonus();
    }
}
