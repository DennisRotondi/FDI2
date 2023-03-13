import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Graph<V> {

    private LinkedList<Node<V>> nodes;
    private int n_nodes;
    private int n_edges;

    public Graph () {
        this.nodes = new LinkedList<Node<V>>();
    }

    @SuppressWarnings("unchecked")
    public List<Node<V>> getNodes() {
        List<Node<V>> ret = new LinkedList<>();
        
        for(Node<V> n : this.nodes) {
            try {
                ret.add((Node<V>) n.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        
        return (List<Node<V>>) ret;
    }

    @SuppressWarnings("unchecked")
    public List<Node<V>> getNeighbors(Node<V> n) {
        List<Node<V>> ret = new LinkedList<>();
        
        for(Node<V> edge : n.outEdges) {
            try {
                ret.add((Node<V>) edge.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        
        return (List<Node<V>>) ret;
    }

    public Node<V> addNode(V value) {
        Node<V> n = new Node<V>();
        n.value = value;
        n.outEdges = new LinkedList<Node<V>>();
        n.state = Node.Status.UNEXPLORED;
        
        for(Node<V> node : this.nodes) {
            if(node.value.equals(value))
                return node;
        }
        
        this.nodes.add(n);
        this.n_nodes++;
        return n;
    }

    public void addEdge(Node<V> s, Node<V> t) {
        s.outEdges.add(t);
        t.outEdges.add(s);
        this.n_edges++;
    }

    public V getNodeValue(Node<V> n) {
        return n.value;
    }
    
    public void removeEdge(Node<V> v1, Node<V> v2) {
        removeEdgeAux(v1, v2);
        removeEdgeAux(v2, v1);
        this.n_edges--;
    }
    
    private void removeEdgeAux(Node<V> v1, Node<V> v2) {
        Iterator<Node<V>> it = this.nodes.iterator();
        
        while(it.hasNext()) {
            Node<V> n1 = it.next();
            Iterator<Node<V>> it2 = n1.outEdges.iterator();
            
            if(n1.equals(v1))
                while(it2.hasNext()) {
                    Node<V> n2 = it2.next();
                    
                    if(n2.equals(v2)) 
                        it2.remove();
                }
        }
    }

    public void removeNode(Node<V> v) {
        if(this.nodes.contains(v)) {
            for(Node<V> e : v.outEdges) {
                this.removeEdgeAux(e, v);
                this.n_edges--;
            }
        }
        this.nodes.remove(v);
    }

    public static <V> Graph<V> readFF(File input) {
        Graph<V> toRet = new Graph<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String firstLine = br.readLine();
            StringTokenizer tok = new StringTokenizer(firstLine);
            int v = Integer.parseInt(tok.nextToken());
            int e = Integer.parseInt(tok.nextToken());
            
            for(int i = 0; i < e; i++) {
                tok = new StringTokenizer(br.readLine());
                
                while(tok.hasMoreTokens()) {
                    Node<V> v1 = toRet.addNode((V) tok.nextToken());
                    Node<V> v2 = toRet.addNode((V) tok.nextToken());
                    toRet.addEdge(v1, v2);
                }
            }
            br.close();
            
            if(toRet.n_nodes < v) {
                int rem = v - toRet.n_nodes;
                int name = e + 1;
                
                for(int i = 0; i < rem; name++, i++)
                    toRet.addNode((V) (name + ""));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return toRet;
    }

    public String printAdj() {
        StringBuffer toRet = new StringBuffer();
        
        for(Node<V> v : this.nodes) {
            toRet.append(v.value + ": ");

            for(Node<V> e: v.outEdges)
                toRet.append(e.value + " ");
            
            toRet.append("\n");
        }
        
        return toRet.toString();
    }
    
    @Override
    public String toString() {
        StringBuffer toRet = new StringBuffer();
        toRet.append(this.n_nodes + " " + this.n_edges + "\n");
        
        for(Node<V> node : this.nodes) {
            if(node.state == Node.Status.UNEXPLORED)
                DFSprintEdges(node, toRet);
        }
        
        return toRet.toString();
    }

    private void DFSprintEdges(Node<V> node, StringBuffer str) {
        if(node.state != Node.Status.UNEXPLORED)
            return;
        
        node.state = Node.Status.EXPLORING;
        for(Node<V> e : node.outEdges) {
            if(e.state == Node.Status.UNEXPLORED)
                str.append(node.value + " " + e.value + "\n");
        }
        
        for(Node<V> e : node.outEdges) {
            if(e.state == Node.Status.UNEXPLORED)
                DFSprintEdges(e, str);
        }
        node.state = Node.Status.EXPLORED;
    }

    public int nConComp() {
        int ret = 0;
        
        for(Node<V> node : this.nodes) {
            if(node.state == Node.Status.UNEXPLORED) {
                ret++;
                DFS(node);
            }
        }
        
        return ret;
    }

    private void DFS(Node<V> node) {
        if(node.state != Node.Status.UNEXPLORED)
            return;
        
        node.state = Node.Status.EXPLORING;
        for(Node<V> e : node.outEdges) {
            if(e.state == Node.Status.UNEXPLORED)
                DFS(e);
        }
        
        node.state = Node.Status.EXPLORED;
    }

    public List<Graph<V>> getConComp() {
        LinkedList<Graph<V>> toRet = new LinkedList<>();
        
        for(Node<V> node : this.nodes) {
            if(node.state == Node.Status.UNEXPLORED) {
                Graph<V> g = new Graph<>();
                toRet.add(g);
                DFSfillCC(node, g.nodes);
            }
        }
        
        return toRet;
    }

    private void DFSfillCC(Node<V> node, LinkedList<Node<V>> list) {
        if(node.state != Node.Status.UNEXPLORED)
            return;
        
        node.state = Node.Status.EXPLORING;
        list.add(node);
        
        for(Node<V> n : node.outEdges) {
            if(n.state == Node.Status.UNEXPLORED)
                DFSfillCC(n, list);
        }
        
        node.state = Node.Status.EXPLORED;
    }

    /* Classe interna che descrive il generico nodo del grafo, con liste dei vicini uscenti ed entranti */
    public static class Node<V> implements Cloneable {
        public enum Status {UNEXPLORED, EXPLORED, EXPLORING}

        protected V value;
        protected LinkedList<Node<V>> outEdges;

        protected Status state; // tiene traccia dello stato di esplorazione
        protected int map; // utile in partition union e find
        protected int timestamp; // utile per associare valori interi ai vertici
        protected int dist; // utile per memorizzare distanze in algoritmi per cammini minimi

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
