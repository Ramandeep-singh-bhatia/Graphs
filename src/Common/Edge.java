package Common;

public class Edge {
    private char source;
    private char destination;
    private double weight;

    public Edge(char source, char destination, double weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public char getSource() {
        return source;
    }

    public char getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}
