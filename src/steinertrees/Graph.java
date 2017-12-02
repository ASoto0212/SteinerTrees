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
    
    public Edge[] MST(){
        Collections.sort(this.edges, Comparator.comparingInt(Edge :: get_weight));
        Edge goalPath[]=new Edge[this.numNodes];
        Graph mst=new Graph();
        int e=0,i=0;
        for(int j=0;j<this.numNodes;j++){
            goalPath[j]=new Edge();
        }
        subset subsets[]=new subset[this.numNodes];
        for(int j=0;j<this.numNodes;j++){
            subsets[j]=new subset();
        }
        for(int v=0;v<this.numNodes;v++){
            subsets[v].parent=v;
            subsets[v].rank=0;
        }
        while(e<this.numNodes-1){
            Edge next=new Edge();
            next=this.edges.get(i++);
            int x=find(subsets, next.get_start());
            int y=find(subsets, next.get_end());
            
            if(x!=y){
                goalPath[e++]=next;
                Union(subsets,x,y);
            }
        }
        System.out.println(goalPath.length);
        for(int j=0;j<goalPath.length-1;j++){
            System.out.println("Edge:" + j + " " +
                                goalPath[j].get_start()+
                                "<-->"+goalPath[j].get_end()+
                                "   Weight="+goalPath[j].get_weight());
        }
        return goalPath;
    }

    //union and find are not my code.
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
