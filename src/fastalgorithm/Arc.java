/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastalgorithm;

import java.util.ArrayList;

/**
 *
 * @author Christopher Luna
 */
public class Arc extends Edge{
    protected ArrayList<Edge> edgeList;
    
    public Arc(int v, int w, double weight) {
        super(v, w, weight);
        edgeList = new ArrayList<Edge>();
    }
    
    public Arc(Edge e){
        this.v = e.either();
        this.w = e.other(this.v);
        this.weight = e.weight();
        edgeList = new ArrayList<Edge>();
        edgeList.add(e);
    }
    
    public Arc(){
        v=0;
        w=0;
        weight=0.0;
        edgeList=new ArrayList<Edge>();
    }

    public Arc(int start, int end) {
        v = start;
        w = end;
        weight = 0.0;
        edgeList=new ArrayList<Edge>();
    }
    
    public void addEdge(Edge e){
        edgeList.add(e);
        this.weight += e.weight();
    }
    
    public void setStart(int _v){
        this.v = _v;
    }
    
    public void setEnd(int _w){
        this.w = _w;
    }
    
    public ArrayList<Edge> getEdgeList(){
        return edgeList;
    }
    
}
