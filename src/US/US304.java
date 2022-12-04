package US;


import Domain.Local;
import Domain.US304.ParClienteHub;
import Shared.GraphCommon.Algorithms;
import Shared.GraphCommon.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class US304 {

    public static List<ParClienteHub> findNearestHubs(Graph<Local, Integer> map, Integer numberOfHubs){
        List<Local> hubs = US303.findHubs(map, numberOfHubs);

        return findNearestHubsAlgorithm(map, hubs);
    }

    public static List<ParClienteHub> findNearestHubsAlgorithm(Graph<Local, Integer> map, List<Local> hubs){

        List<Local> clients = getClients(map);
        Integer mindist = Integer.MAX_VALUE;
        LinkedList<Local> shortpath = new LinkedList<>();
        Local hubToAdd = null;

        List<ParClienteHub> hubClosestToEachClient = new ArrayList<>();


        if(clients.size() == 0)
            return null;

        for(Local client : clients){



            for(Local hub : hubs){
                Integer distance = Algorithms.shortestPath(
                        map,
                        client,
                        hub,
                        Integer::compareTo,
                        Integer::sum,
                        0,
                        shortpath
                );

                if(distance < mindist){
                    mindist = distance;
                    hubToAdd = hub;
                }
            }

            hubClosestToEachClient.add(new ParClienteHub(client, hubToAdd, mindist));
            mindist = Integer.MAX_VALUE;
        }

        if(hubClosestToEachClient.size() == 0){
            return null;
        }

        return hubClosestToEachClient;
    }

    public static List<Local> getClients(Graph<Local, Integer> map){
        List<Local> clients = new ArrayList<>();

        for(Local member : map.vertices()){
            if(!(member.getDestinatário().charAt(0) == 'P'))
                clients.add(member);
        }

        return clients;
    }


}