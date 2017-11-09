/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steinertrees;

import java.util.*;

public class Graph {
    
    private Set<Integer> nodes;
//    private Map<Integer, Vertex> vertices;
    public List<Edge> edges;
    public int size;
    public Graph(){
        nodes = new HashSet<>();
//        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }
    
    public void add_node(int newNode){
        if(!this.nodes.contains(newNode)){
//            Vertex newVertex = new Vertex(newNode);
            this.nodes.add(newNode);
//            this.vertices.put(newNode, newVertex);
        }
    }
    
    public void add_edge(int start, int end, int weight){
        if(!this.nodes.contains(start))
            this.add_node(start);
        if(!this.nodes.contains(end))
            this.add_node(end);
//        this.vertices.get(start).add_neighbors(vertices.get(end), weight);
//        this.vertices.get(end).add_neighbors(vertices.get(start), weight);
        this.edges.add(new Edge(start, end, weight));
        //System.out.println("Start: " + start + " End: " + end + " Weight: " + weight);
    }
    
/*    public Vertex get_node(int vertex){
        if(this.nodes.contains(vertex))
            return this.vertices.get(vertex);
        else{
            return null;
        }
    }

    public Map<Integer, Vertex> get_nodes(){
        return this.vertices;
    }
    
    public Set<Integer> node_set(){
        return this.nodes;
    }
*/    
    public int find(int root[], int i){
        if(root[i]==-1){
            return i;
        }
        return find(root, root[i]);
    }
    
    public void join(int root[], int x, int y){
        int xset=find(root, x);
        int yset=find(root, y);
        root[xset]=yset;
    }
    
/*    public int get_distance(Vertex start, Vertex end){
        return start.get_distance() + end.get_distance();
    }
*/    
    public void set_id(int edgeNum){
        this.size=edgeNum;
    }
    
    public boolean is_cycle(Graph g){
        int root[]= new int [g.size];
        
        for(int i=0; i<g.size; i++){
            root[i]=-1;     
        }
        
        for(int i=0; i<g.edges.size(); i++){
            int x=g.find(root, g.edges.get(i).get_start());
            int y=g.find(root, g.edges.get(i).get_end());
            
            if(x==y){
                return true;
            }
            g.join(root, x, y);
        }
        return false;
    }
    
    public Queue<Edge> MST(){
        Collections.sort(this.edges, Comparator.comparingInt(Edge :: get_weight));
        Queue<Edge> goalPath=new LinkedList<>();
        Graph mst=new Graph();
        Edge current=this.edges.get(0);
        goalPath.add(current);
        mst.add_node(current.get_start());
        mst.add_node(current.get_end());
        //current.set_distance(0);
        while(mst.nodes.size()<=this.nodes.size()){
            this.edges.remove(0);
            current=this.edges.get(0);
            mst.add_edge(current.get_start(), current.get_end(), current.get_weight());
            if(!is_cycle(mst))
                goalPath.add(current);
        }
        return goalPath;
    }

}

