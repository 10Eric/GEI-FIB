package edu.upc.prop.clusterxx.estrategias_calculo;

import edu.upc.prop.clusterxx.clases_dominio.Producte;
import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;

import java.util.*;
import java.io.Serializable;

public class EstrategiaTSP implements EstrategiaCalculo, Serializable {

    @Override
    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, int altura) {
        int n = productes.size();
        ArrayList<Edge> aristas = generateEdges(productes);


        //aplicamos kruskal o prim
        ArrayList<Edge> mst = prim(aristas, productes);

        //tenemos un mst pero tiene en cuenta el primero y el ultimo?

        //  Construiım el graf dirigit T’ reempla¸cant cada aresta de T per dos arcs de direccions
        //  oposades.

        ArrayList<Edge> grafoDirigido = new ArrayList<>();

        for (Edge arista : mst) {
            // Crear dos arcos dirigidos para cada arista.
            grafoDirigido.add(new Edge(arista.from, arista.to, arista.peso)); // Arco A -> B
            grafoDirigido.add(new Edge(arista.to, arista.from, arista.peso)); // Arco B -> A
        }

        //calculamos un ciclo hamiltoniano dado el ciclo euleriano que se nos genera al crear el grafo dirigido
        ArrayList<String> ciclo_hamiltoniano = cicloHamiltoniano(grafoDirigido, aristas, new ArrayList<>(productes));


        //prepara la salida pasa de string a productes colocats!
        return actualitza_posicions(prepara_salida(productes, ciclo_hamiltoniano), altura);

    }

    private List<ProducteColocat> prepara_salida(List<ProducteColocat> productes, ArrayList<String> cicloHamiltoniano) {
        List<ProducteColocat> resultat = new ArrayList<>();

        for (String nom : cicloHamiltoniano) {
            for (ProducteColocat producte : productes) {
                if (producte.getnom().equals(nom)) {
                    resultat.add(producte);
                    break;
                }
            }
        }
        return resultat;
    }


    class Edge {
        String from;
        String to;
        int peso;

        Edge(String from, String to, int peso) {
            this.from = from;
            this.to = to;
            this.peso = peso;
        }

        public int compareTo(Edge o) {
            return this.peso - o.peso;
        }


    }

    private ArrayList<Edge> generateEdges(List<ProducteColocat> productes) {
        ArrayList<Edge> edges = new ArrayList<>();
        int n = productes.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int similitud = productes.get(i).getProducte().getSimilitud(productes.get(j).getProducte());
                edges.add(new EstrategiaTSP.Edge(productes.get(i).getProducte().getNom(), productes.get(j).getProducte().getNom(), similitud));
            }
        }
        return edges;
    }

    private ArrayList<Edge> prim(ArrayList<Edge> edges, List<ProducteColocat> productes) {
        ArrayList<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> maxHeap = new PriorityQueue<>((e1, e2) -> Integer.compare(e2.peso, e1.peso));
        Set<String> visited = new HashSet<>();

        // Empezamos desde cualquier nodo, por ejemplo, el primero
        String startNode = productes.get(0).getnom();
        visited.add(startNode);

        // Añadimos todas las aristas que conectan con el nodo inicial
        for (Edge edge : edges) {
            if (edge.from.equals(startNode) || edge.to.equals(startNode)) {
                maxHeap.add(edge);
            }
        }

        while (!maxHeap.isEmpty() && mst.size() < productes.size() - 1) {
            Edge currentEdge = maxHeap.poll(); // Sacamos la arista de mayor peso

            // Determinamos el nodo no visitado
            String nextNode;

            if (!visited.contains(currentEdge.from)) {
                nextNode = currentEdge.from;
            } else {
                nextNode = currentEdge.to;
            }

            if (!visited.contains(nextNode)) {
                visited.add(nextNode);
                mst.add(currentEdge);

                // Añadimos las aristas que conectan con el nodo recién visitado
                for (Edge edge : edges) {
                    if ((edge.from.equals(nextNode) && !visited.contains(edge.to)) ||
                            (edge.to.equals(nextNode) && !visited.contains(edge.from))) {
                        maxHeap.add(edge);
                    }
                }
            }
        }

        return mst;
    }



    private int calcula_similitud(ArrayList<String> posibleSolucion, ArrayList<Edge> aristas) {
        int resultado = 0;
        int posibleSolucion_size = posibleSolucion.size();
        for (int i = 0; i < posibleSolucion_size - 1; ++i) {
            //el get similitud devuelve -1 si no lo encuentra, no deberia
            resultado += get_similitud_2_productes(posibleSolucion.get(i), posibleSolucion.get(i + 1), aristas);
        }
        resultado += get_similitud_2_productes(posibleSolucion.getFirst(), posibleSolucion.getLast(), aristas);


        return resultado;
    }

    private int get_similitud_2_productes(String s, String s1, ArrayList<Edge> elRestoDeAristas) {
        for (Edge edge : elRestoDeAristas) {
            if (edge.from.equals(s) && edge.to.equals(s1)) {
                return edge.peso;
            }
        }
        return 0;
    }

    private Set<String> calcula_numero_nodos(ArrayList<Edge> cicloEuleriano) {
        Set<String> nodos = new HashSet<>();
        for (Edge edge : cicloEuleriano) {
            nodos.add(edge.from);
            nodos.add(edge.to);
        }
        return nodos;
    }


    public ArrayList<String> cicloHamiltoniano(ArrayList<Edge> cicloEuleriano, ArrayList<Edge> el_resto_de_aristas, ArrayList<ProducteColocat> productes_totals) {
        //Lo ordenamos por si no viene ordenador, se ha de comprovar porque alomejor se puede quitar
        cicloEuleriano.sort((e1, e2) -> Integer.compare(e2.peso, e1.peso));

        //Solucion final la creamos
        ArrayList<String> cicloHamiltoniano = new ArrayList<>();
        Set<String> nodos = calcula_numero_nodos(cicloEuleriano);

        // Recorremos cada nodo y hacemos un ciclo hamiltoniano de cada nodo con las aristas del ciclo euleriano y si no puede continuar
        int nodos_size = nodos.size();

        Iterator<String> iterator = nodos.iterator();
        int solucion_provisional = -1;

        //recorremos por cada nodo sacamos un ciclo hamiltoniano
        for (int i = 0; i < nodos_size; ++i) {
            String nodo_inicial = iterator.next();
            ArrayList<String> solucion_prov = new ArrayList<>();
            String nodo_actual = nodo_inicial;
            Set<String> visitados = new HashSet<>();

            while (solucion_prov.size() < nodos_size) {
                solucion_prov.add(nodo_actual);
                visitados.add(nodo_actual);

                Edge arista = get_arista(visitados, nodo_actual, cicloEuleriano);
                if (arista == null) break; // Si no hay más aristas válidas, termina el ciclo

                nodo_actual = arista.to;
            }

            int similitud_nova_distro = calcula_similitud(solucion_prov,el_resto_de_aristas);
            if (solucion_provisional < similitud_nova_distro){
                cicloHamiltoniano = solucion_prov;
                solucion_provisional = similitud_nova_distro;
            }
        }
        return cicloHamiltoniano;

    }

    private Edge get_arista(Set<String> visitados, String nodo_actual, ArrayList<Edge> cicloEuleriano) {
        Edge mejorArista = null;

        // Primera prioridad: Buscar aristas donde nodo_actual sea el origen (from) y el destino (to) no esté visitado
        for (Edge edge : cicloEuleriano) {
            if (edge.from.equals(nodo_actual) && !visitados.contains(edge.to)) {
                return edge; // Prioridad máxima, devolvemos inmediatamente
            }
        }

        // Segunda prioridad: Buscar aristas donde el destino (to) no esté visitado
        for (Edge edge : cicloEuleriano) {
            if (!visitados.contains(edge.to)) {
                mejorArista = edge; // Mantén una opción si no se cumple la primera prioridad
            }
        }

        // Si no hay aristas válidas, mejorArista será null
        return mejorArista;
    }


    private List<ProducteColocat> actualitza_posicions (List <ProducteColocat> solucio, int altura){
        int medida;
        if (solucio.size() % altura == 0) {
            medida = solucio.size() / altura;
        } else {
            medida = solucio.size() / altura + 1;
        }

        for (int i = 0; i < solucio.size(); i++) {
            if (solucio.get(i) != null) {
                solucio.get(i).setPos(i + 1);
                solucio.get(i).setAltura((i / medida) + 1);
            }
        }
        return solucio;
    }

}
