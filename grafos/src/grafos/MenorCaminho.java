package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenorCaminho {

    private Dijkstra dijkstra;
    private Map<Integer, String> vertices;
    private String[] lines;
    private List<Aresta> arestas;

    MenorCaminho(String[] lines) {
        vertices = new HashMap<>();
        arestas = new ArrayList<>();
        this.lines = lines;

        this.iniciar();
        this.contaVertices();
    }

    public void encontrar(int idOrigem, int idDestino) throws Exception {
        dijkstra = new Dijkstra(vertices.size() + 1);

        for (Aresta aresta : arestas) {
            dijkstra.inserirAresta(aresta);
        }

        dijkstra.menorCaminhoEncontrar(idOrigem, idDestino);
    }

    private void iniciar() {
        for (String line : this.lines) {
            String[] vertices = line.split(";");

            arestas.add(new Aresta(
                    new Rota(vertices[0], vertices[1]),
                    new Rota(vertices[2], vertices[3]),
                    Integer.parseInt(vertices[4])
            ));
        }
    }

    private void contaVertices() {
        for (Aresta aresta : arestas) {
            vertices.put(aresta.getDestino().getId(), aresta.getDestino().getNome());
            vertices.put(aresta.getOrigem().getId(), aresta.getOrigem().getNome());
        }
    }

    public static void main(String[] args) {
        String[] lines = {
                "1;Siderópolis;2;Criciúma;30",
                "1;Siderópolis;3;Forquilhina;43"
        };
        MenorCaminho menorCaminho = new MenorCaminho(lines);
        try {
            menorCaminho.encontrar(1, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
