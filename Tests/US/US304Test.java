package US;

import CsvReader.CsvReader;
import Domain.Local;
import Domain.US304.ParClienteHub;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import Shared.constants.Files;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class US304Test {
CsvReader readFiles = new CsvReader();

    @Test
    void Test1() throws Exception {

        List<ParClienteHub> clients = ParClienteHub.getParClienteHubList();

        clients.add(new ParClienteHub("C1",new Local("CT5"), new Local("B"), 1));
        clients.add(new ParClienteHub("C2",new Local("CT6"), new Local("C"), 2));
        clients.add(new ParClienteHub("C3",new Local("CT7"), new Local("D"), 3));
        clients.add(new ParClienteHub("C4",new Local("CT8"), new Local("E"), 4));
        clients.add(new ParClienteHub("C5",new Local("CT9"), new Local("F"), 5));

        assertEquals(clients.get(0).getCliente().getName(), "C1");
        assertEquals(clients.get(0).getHub().getName(), "A");

    }

    }
