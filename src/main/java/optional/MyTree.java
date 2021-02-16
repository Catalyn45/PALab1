package optional;

class Node {
    public int info;
    public Node[] neighbours;

    public Node(int info, int maxNeighbours) {
        this.info = info;
        neighbours = new Node[maxNeighbours];
    }
}

public class MyTree {

    private Node root;
    private final int maxNeighbours;
    int maxDepth;

    public MyTree(int rootValue, int maxNeighbours) {
        root = new Node(rootValue, maxNeighbours);
        this.maxNeighbours = maxNeighbours;
        maxDepth = ((int)(Math.random() * 10000)) % 2 + 5;
    }

    private void generateRandom(Node node, int currentDepth) {
        if (currentDepth > maxDepth) {
            return;
        }

        for(int i = 0; i < maxNeighbours; i++) {
            int value = ((int)(Math.random() * 100)) % 10 + 5;
            boolean needNode = Math.random() <= 0.3;

            if(needNode) {
                node.neighbours[i] = new Node(value, this.maxNeighbours);
                generateRandom(node.neighbours[i], currentDepth + 1);
            }
            else {
                node.neighbours[i] = null;
            }
        }
    }

    public void generateRandomTree() {
        generateRandom(this.root, 0);
    }

    private void display(Node node, String spaces) {
        if(node == null)
            return;

        System.out.println(spaces + node.info);

        for(int i = 0; i < this.maxNeighbours; i++)
            display(node.neighbours[i], spaces + "  ");
    }

    public void displayTree() {
        display(this.root, "");
    }
}
