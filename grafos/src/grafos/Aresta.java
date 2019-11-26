package grafos;

public class Aresta {

    private Rota origem;
    private Rota destino;
    private int peso;

    public Aresta() {

    }

    public Aresta(Rota origem, Rota destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Rota getDestino() {
        return destino;
    }

    public void setDestino(Rota destino) {
        this.destino = destino;
    }

    public Rota getOrigem() {
        return origem;
    }

    public void setOrigem(Rota origem) {
        this.origem = origem;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
