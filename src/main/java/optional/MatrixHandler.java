package optional;

public class MatrixHandler {
    private final int rows;
    private final boolean [][] graphMatrix;
    private boolean [][] partialTree;

    private MatrixHandler(int n) {
        rows = n;
        graphMatrix = new boolean[n][n];
        partialTree = null;
    }

    // genereaza matricea random a unui graf neorientat
    public void generateRandomGraph() {
        partialTree = null;
        for(int i = 0; i < rows - 1; i++)
            for(int j = i + 1; j < rows; j++) {
                graphMatrix[i][j] = graphMatrix[j][i] = (Math.random() >= 0.5);
            }
    }

    // afiseaza graful
    public void printGraph() {
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print("[ " + (graphMatrix[i][j] ? 1 : 0) + " ]");
            }
            System.out.println();
        }
    }

    // afiseaza arborele partial generat de getPartialTree()
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

    // executa dfs pe graful dat ca parametru si intoarca arborele partial
    static void dfs(boolean[][] graph, boolean[] verified, int rows, int node, boolean [][] Tree) {
        verified[node] = true;

        for(int i = 0; i < rows; i++)
        {
            if(!verified[i] && graph[node][i])
            {
                if(Tree != null){
                    Tree[node][i] = Tree[i][node] = true;
                }

                dfs(graph, verified, rows, i, Tree);
            }
        }
    }

    // verifica daca graful este conect si in caz negativ, afiseaza componentele conexe
    public boolean isConnected() {
        boolean[] verified = new boolean[rows];

        dfs(graphMatrix, verified, rows, 0, null);

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
            dfs(graphMatrix, verified, rows, lastUnvisited, null);
        }

        return false;
    }

    // se genereaza arborele partial dfs corespunzator grafului
    public void getPartialTree() {
        partialTree = new boolean[rows][rows];
        dfs(graphMatrix, new boolean[rows], rows, 0, partialTree);
    }

    static public void log(String text) {
        System.out.println(text);
    }

    // Se face validarea argumentelor
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
