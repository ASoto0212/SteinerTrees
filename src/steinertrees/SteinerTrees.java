package steinertrees;
import java.io.*;
import java.util.*;

public class SteinerTrees {

    public static void main(String[] args) throws FileNotFoundException {
        File test= new File("C:\\Users\\Alec\\Desktop\\B\\B\\b01.stp");
        System.out.println(test.canRead());
        Scanner scan = new Scanner(test);
        String next;
        int nodes, edges, terminals;
        while(scan.hasNextLine()){
            next=scan.nextLine();
            //System.out.println(next);
            switch(next){
                case "SECTION Graph":
                    System.out.println("AUSSIE AUSSIE AUSSIE");
                    scan.skip("Nodes ");
                    nodes=scan.nextInt();
                    scan.skip("\nEdges ");
                    edges=scan.nextInt();
                    next=scan.nextLine();
                    while(!next.equals("END")){
                        System.out.println(next);
                        next=scan.nextLine();
                    }
                    break;
            }
        }
    }
}
