class Worker {
    String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

    // Afiseaza hello world
    void printHello() {
        System.out.println("Hello world");
    }

    // genereaza un numar random intre 0 si 1.000.000
    int generate() {
        return (int)(Math.random() * 1000000);
    }

    // se efectueaza urmatoarele operatii pe numarul n
    int compute(int n) {
        return (n * 3 + 0b10101 + 0xFF) * 6;
    }


    // insumeaza cifrele lui n pana cand rezultatul este un numar de o singura cifra
    int addDigits(int n) {
        while(n > 9)
        {
            int newResult = 0;
            while(n > 0)
            {
                newResult = newResult + n % 10;
                n = n / 10;
            }

            n = newResult;
        }

        return n;
    }

    // se afiseaza mesajul urmat de valoarea de la index-ul n din vectorul languages
    void display(int n) {
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }

    void run()
    {
        int random = generate();
        int computed = compute(random);
        int result = addDigits(computed);
        display(result);
    }
}


class MatrixHandler {
    private final int rows;
    private final boolean [][] graphMatrix;
    private boolean [][] partialTree;

    private MatrixHandler(int n) {
        rows = n;
        graphMatrix = new boolean[n][n];
        partialTree = null;
    }

    public void generateRandomGraph() {
        partialTree = null;
        for(int i = 0; i < rows - 1; i++)
            for(int j = i + 1; j < rows; j++) {
                graphMatrix[i][j] = graphMatrix[j][i] = (Math.random() >= 0.5);
            }
    }

    public void printGraph() {
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print("[ " + (graphMatrix[i][j] ? 1 : 0) + " ]");
            }
            System.out.println();
        }
    }

    public void printPartialTree() {

        if(partialTree == null)
            return;

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print("[ " + (partialTree[i][j] ? 1 : 0) + " ]");
            }
            System.out.println();
        }
    }

    static void bfs(boolean[][] graph, boolean[] verified, int rows, int node, boolean [][] Tree) {
        verified[node] = true;

        for(int i = 0; i < rows; i++)
        {
            if(!verified[i] && graph[node][i])
            {
                if(Tree != null){
                    Tree[node][i] = Tree[i][node] = true;
                }

                bfs(graph, verified, rows, i, Tree);
            }
        }
    }

    public boolean isConnected() {
        boolean[] verified = new boolean[rows];

        bfs(graphMatrix, verified, rows, 0, null);

        int j;
        for(j = 0; j < rows; j++) {
            if (!verified[j]) {
                break;
            }
        }

        if(j == rows) {
            return true;
        }

        boolean[] displayed = new boolean[rows];
        int count = 1;

        while(true) {
            System.out.println("Component number " + count + ":");
            int lastUnvisited = 0;

            for (int i = 0; i < rows; i++) {
                if (verified[i] && !displayed[i]) {
                    System.out.print(i + " ");
                    displayed[i] = true;
                }
                else if(!verified[i]) {
                    lastUnvisited = i;
                }
            }

            if(lastUnvisited == 0)
                break;

            count++;
            System.out.println();
            bfs(graphMatrix, verified, rows, lastUnvisited, null);
        }

        return false;
    }

    public boolean[][] getPartialTree() {
        partialTree = new boolean[rows][rows];

        bfs(graphMatrix, new boolean[rows], rows, 0, partialTree);

        return partialTree;
    }

    static public void log(String text) {
        System.out.println(text);
    }

    static public MatrixHandler getInstance(String[] args) {
        if (args.length != 1) {
            log("Not enough or too many command line arguments");
            return null;
        }

        int n = Integer.parseInt(args[0]);

        if(n%2 != 0) {
            log("Command line argument is not even");
            return null;
        }

        return new MatrixHandler(n);
    }
}

public class Main {
    public static void main(String[] args){
        long startTime = System.nanoTime();

        MatrixHandler matrix = MatrixHandler.getInstance(args);

        if(matrix == null) {
            MatrixHandler.log("Error at getting instance");
            return;
        }

        matrix.generateRandomGraph();

        if(matrix.isConnected())
        {
            matrix.getPartialTree();
        }

        System.out.println((System.nanoTime() - startTime) / 1000000);
    }
}
