package US;

import Domain.Local;
import Shared.GraphCommon.Algorithms;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import Shared.MapGraphs.MapVertex;

import java.util.*;

public class US302 {
    public Graph<Local,Integer> map;
    public US302(Graph<Local,Integer> map){
        this.map = map;
    }
    public Map<String,Map<String,Integer>> ligaçoesMinimas(){
        if (!connected())return null;
        Map<String,Map<String,Integer>> resultado=new HashMap<>();
        Map<String,Integer> dist=new HashMap<>();
        Graph<Local,ArrayList<Local>> minimas=new MapGraph<Local,ArrayList<Local>>(false);
        LinkedList<Local> lista=null;
        MapVertex<Local,ArrayList<Local>> mapvertex=null;

        for(Local local:map.vertices()){
            dist=algDist(local);
            resultado.put(local.getName(),dist);
        }
        return resultado;
    }
    public Map<String,Integer> algDist(Local origem){
        Map<String,Integer> resultado=new HashMap<>();
        List<Local> fila=new ArrayList<>();
        int dist[]=new int[map.numVertices()];
        String path[]=new String[map.numVertices()];
        boolean visitados[]=new boolean[map.numVertices()];
        Local original=origem;
        List<Local> result=new LinkedList<>();

        for (int d=0;d<map.numVertices();d++){
            dist[d]=999;
            visitados[d]=false;
        }

        if (!map.validVertex(origem))return null;

        dist[map.key(origem)]=0;
        visitados[map.key(origem)]=true;
        for (Local verts:map.adjVertices(origem)) {
            if (!fila.contains(verts) && visitados[map.key(verts)]==false) {
                if (dist[map.key(verts)]>dist[map.key(origem)]+1){
                    dist[map.key(verts)]=dist[map.key(origem)]+1;
                    path[map.key(verts)]=origem.getName();
                }
                fila.add(verts);
            }
        }

        while(true){
            result.add(origem);
            for (int d=0;d<fila.size();d++){
                if (visitados[map.key(fila.get(d))]==false){
                    origem=fila.get(d);
                    visitados[map.key(origem)]=true;
                    fila.remove(d);
                    break;
                }
                fila.remove(d);
            }

            for (Local verts:map.adjVertices(origem)) {
                if (!fila.contains(verts) && visitados[map.key(verts)]==false) {
                    if (dist[map.key(verts)]>dist[map.key(origem)]+1){
                        dist[map.key(verts)]=dist[map.key(origem)]+1;
                        path[map.key(verts)]=origem.getName();
                    }
                    fila.add(verts);
                }
            }

            if (fila.size()==0){
                if (!result.contains(origem)){
                    result.add(origem);
                }
                break;
            }
        }

        if (result.size()==0)return null;

        for (Local loc: map.vertices()){
            resultado.put(loc.getName(),dist[map.key(loc)]);
        }
        return resultado;
    }

    private boolean connected(){

        LinkedList<Local> result=null;
        List<Local> lista=map.vertices();
        result= Algorithms.BreadthFirstSearch(map,map.vertex(0));

        if (result.size()==lista.size()){
            return true;
        }
        else return false;
    }
}