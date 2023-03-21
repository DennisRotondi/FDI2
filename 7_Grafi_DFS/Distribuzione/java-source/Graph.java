import java.util.LinkedList;
import java.util.List;
import java.io.File;

public class Graph<V> {

    public Graph () {
      
    }

    public List<Node<V>> getNodes() {
        return null;
    }

    public List<Node<V>> getNeighbors(Node<V> n) {
        return null;
    }

    public Node addNode(V value) {
        return null;
    }

    public void addEdge(Node<V> s, Node<V> t) {
        return;
    }

    public V getNodeValue(Node<V> n) {
        return null;
    }

    public void removeEdge(Node<V> v1, Node<V> v2) {

    }

    public void removeNode(Node<V> v) {

    }

    public static <V> Graph<V> readFF(File input) {
        return null;
    }

    public String printAdj() {
        return "";
    }

    @Override
    public String toString() {
        return null;
    }

    public int nConComp() {
        return 0;
    }

    public List<Graph<V>> getConComp() {
        return null;
    }

    /* Classe interna che descrive il generico nodo del grafo */
    public static class Node<V> implements Cloneable {
        public enum Status {UNEXPLORED, EXPLORED, EXPLORING}

        protected V value;
        protected LinkedList<Node<V>> outEdges;

        protected Status state; // tiene traccia dello stato di esplorazione

        @Override
        public String toString() {
            return "Node [value=" + value + ", state=" + state + "]";
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
