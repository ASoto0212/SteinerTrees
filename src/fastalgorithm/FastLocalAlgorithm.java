/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastalgorithm;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import static fastalgorithm.ShortestPath.V;
import org.apache.commons.collections15.Transformer;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.util.*;
import static javafx.scene.input.KeyCode.V;
import static javafx.scene.paint.Color.color;
import javax.swing.*;

/**
 *
 * @author Christopher Luna
 */
public class FastLocalAlgorithm {
    
    public static EdgeWeightedGraph parseSteinerData(String inputFile){
        String path = new File(inputFile).getAbsolutePath();
        File test = new File(path);
        Scanner scan = null;
        try {
            scan = new Scanner(test);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open file.");
            return null;
        }
        String getNext;
        EdgeWeightedGraph graph = null;
        int nodes, edges, terminal, terminals, start, end, weight;
        while(scan.hasNextLine()){
            getNext=scan.nextLine();
            //System.out.println(getNext);
            switch(getNext){
                case "SECTION Graph":
                    scan.skip("Nodes ");
                    nodes=scan.nextInt();
                    graph = new EdgeWeightedGraph(nodes);
                    scan.nextLine();
                    scan.skip("Edges ");
                    edges=scan.nextInt();
                    scan.nextLine();
                    //graph = set_id(edges);
                    for(int i=0; i<edges; i++){
                        String isE=scan.next();
                        if (isE.equals("E "))
                            scan.skip("E ");
                        start=scan.nextInt()-1;
                        end=scan.nextInt()-1;
                        weight=scan.nextInt();
                        //graph.add_node(start);
                        //graph.add_node(end);
                        Edge e = new Edge(start, end, weight);
                        graph.addEdge(e);
                        /*}
                        else if (isE.equals("A")){
                            //scan.skip("A");
                            start=scan.nextInt()-1;
                            end=scan.nextInt()-1;
                            weight=scan.nextInt();
                            System.out.println(start);
                            System.out.println(end);
                            System.out.println(weight);
                            System.out.println(String.format("Reading A %d %d %d",start,end,weight));
                            //graph.add_node(start);
                            //graph.add_node(end);
                            Edge e = new Edge(start, end, weight);
                            graph.addEdge(e);
                        }*/
                    }
                    break;
                case "SECTION Terminals":
                    scan.skip("Terminals ");
                    terminals=scan.nextInt();
                    scan.nextLine();
                    //String isRoot = scan.next();
                    //if (isRoot.equals("Root")){
                    //    scan.nextLine();
                    //}
                    for (int i=0; i < terminals; i++){
                        String isE=scan.next();
                        if (isE.equals("T "))
                            scan.skip("T ");
                        terminal=scan.nextInt()-1;
                        //Possible null pointer exception if terminals listed before graph
                        graph.addTerminal(terminal);
                    }
                    break;
            }
        }
        return graph;
    }
    
    public static void main(String [] args) {
        //String inputfile = "src/steinertrees/gene61f.txt";
        String inputfile = "src/fastalgorithm/test1.txt";
        EdgeWeightedGraph G = parseSteinerData(inputfile);
        //System.out.println(G.size());
        //System.out.println(G.E());
        ArrayList<Integer> terminals = G.getTerminals();
        //for (Integer i: terminals){
        //    System.out.println(i+1);
        //}
        int[] predecessors;
        int terminal;
        // Step 1 Construct the complete undirected distance graph from
        // the graph G and set of Steiner points S
        
        // pred to paths to arcs to graph
        
        // Find shortest distances for each terminal
        EdgeWeightedGraph G1 = new EdgeWeightedGraph(G.size());
        
        int target = -1;
        // For each terminal, we need the shortest distance to every other terminal
        for (int i = 0; i < terminals.size();i++){
            terminal = terminals.get(i);
            predecessors = Dijkstra.dijkstra(G, terminal);
            if (predecessors != null){
                // Add shortest path to each other terminal to the subgraph
                for (int j = 0; j < terminals.size(); j++)
                    target = terminals.get(j);
                if (target != terminal){
                    ArrayList<Integer> path = Dijkstra.printPath(G, predecessors, terminal, target);
                    
                    // Parse the list of vertices into the Arc for the subgraph
                    Arc shortest_path = new Arc(path.get(0),path.get(path.size()-1));
                    for (int k = 1; k < path.size(); k++){
                        int start = path.get(k-1);
                        int end = path.get(k);
                        Edge toAdd = new Edge(start, end, G.getWeight(start, end));
                        //System.out.println(toAdd == null);
                        shortest_path.addEdge(toAdd);
                    }
                    G1.addEdge(shortest_path);
                }
            }
        }
        
        // Step 2 find the minimal spanning tree, T1 of G1.
        
        KruskalMST T1 = new KruskalMST(G1);
        
        // Step 3 construct the subgraph Gs of G by replacing each edge in T1 by its
        // corresponding shortest path in G
        
        LinkedList<Edge> GsEdges = T1.getEdges();
        EdgeWeightedGraph Gs = new EdgeWeightedGraph(G.size());
        // Expand ARCs for subgraph
        for (int i = 0; i < GsEdges.size(); i++){
            Arc thisArc = (Arc) GsEdges.get(i);
            ArrayList<Edge> thisEdgeList = thisArc.getEdgeList();
            for (int j = 0; j < thisEdgeList.size(); j++){
                Gs.addEdge(thisEdgeList.get(j));
            }
        }
        
        // Step 4 find the minimal spanning tree, Ts of Gs
        
        KruskalMST Ts = new KruskalMST(Gs);
        
        // Step 5 construct a steiner tree Th from Ts by deleting edges in Ts if 
        // necessary so that all the leaves in Th are Steiner points
        
        EdgeWeightedGraph untrimmed = new EdgeWeightedGraph(G.size());
        LinkedList<Edge> untrimmedEdges = Ts.getEdges();
        for (int i = 0; i < untrimmedEdges.size(); i++){
            untrimmed.addEdge(untrimmedEdges.get(i));
        }
        
        EdgeWeightedGraph steinerTree = new EdgeWeightedGraph(G.size());
        for (int i = 0; i < untrimmedEdges.size(); i++){
            Edge thisEdge = untrimmedEdges.get(i);
            int v,w;
            v = thisEdge.either();
            w = thisEdge.other(v);
            if ((untrimmed.degree(v) > 1 || terminals.contains(v)) && (untrimmed.degree(w) > 1 || terminals.contains(w))){
                steinerTree.addEdge(thisEdge);
            }
        }
        
        steinerTree.setTerminals(terminals);
        int count=0;
        UndirectedSparseGraph mst = new UndirectedSparseGraph();
        //Algorithm complete steinerTree completed
        //System.out.println(steinerTree.toString());
        for(int i=1;i<=steinerTree.size();i++){
            for(int j=1;j<=steinerTree.size();j++){
                if(steinerTree.adjacency[i-1][j-1]!=Double.MAX_VALUE){
                    mst.addVertex(i);
                    mst.addVertex(j);
                    mst.addEdge(count, i, j);
                    count++;
                }
            }
        }
        
        Layout<Integer, Integer> layout = new KKLayout(mst);
   
        BasicVisualizationServer<Integer, Integer> vs = new BasicVisualizationServer<Integer, Integer>(layout);
        vs.setPreferredSize(new Dimension(700,700));
        
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        
        Transformer<Integer, Paint> vertexColor = new Transformer<Integer, Paint>(){
            public Paint transform(Integer i){
                for(Integer term : steinerTree.terminals){
                if(i-1==term)return Color.BLUE;
                }
                return Color.RED;
            }
        };
        
        vs.getRenderContext().setVertexDrawPaintTransformer(vertexColor);
        vs.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<Integer,Integer>());
        
        JFrame frame = new JFrame();
        frame.getContentPane().add(vs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
}

    
