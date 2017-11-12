/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steinertrees;

import java.util.*;

public class Graph {
    
    private Set<Integer> nodes;
    public List<Edge> edges;
    public int numEdges, numNodes;
    public Graph(){
        nodes = new HashSet<>();
        edges = new ArrayList<>();
    }
    
    public void add_node(int newNode){
        if(!this.nodes.contains(newNode)){
            this.nodes.add(newNode);
        }
    }
    
    public void add_edge(int start, int end, int weight){
        if(!this.nodes.contains(start))
            this.add_node(start);
        if(!this.nodes.contains(end))
            this.add_node(end);
        this.edges.add(new Edge(start, end, weight));
    }
    
    public void set_edges(int e){
        this.numEdges=e;
    }
    
    public void set_nodes(int n){
        this.numNodes=n;
    }
    
    public Queue<Edge> MST(){
        Collections.sort(this.edges, Comparator.comparingInt(Edge :: get_weight));
        Queue<Edge> goalPath=new LinkedList<>();
        Graph mst=new Graph();
        Edge current=this.edges.get(0);
        goalPath.add(current);
        mst.add_node(current.get_start());
        mst.add_node(current.get_end());
        while(mst.nodes.size()<=this.nodes.size()){
            this.edges.remove(0);
            current=this.edges.get(0);
            mst.add_edge(current.get_start(), current.get_end(), current.get_weight());
            goalPath.add(current);
        }
        return goalPath;
    }

    //union and find are not my code.
    //just me testing if kruskals algorithm works for what we want to do
    //source: http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/   
    class subset{
        int parent, rank;
    };
    
    int find(subset subsets[], int i){
        if(subsets[i].parent != i)
            subsets[i].parent=find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
    
    void Union(subset subsets[], int x, int y){
        int xroot=find(subsets,x);
        int yroot=find(subsets,y);
        
        if(subsets[xroot].rank<subsets[yroot].rank)
            subsets[xroot].parent=yroot;
        else if(subsets[xroot].rank>subsets[yroot].rank)
            subsets[yroot].parent=xroot;
        else{
            subsets[yroot].parent=xroot;
            subsets[xroot].rank++;
        }
    }
}
