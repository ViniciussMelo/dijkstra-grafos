package grafos;

public class Rota {

    private int id;
    private String nome;

    Rota(){

    }

    Rota(String  id, String nome){
        this.id = Integer.parseInt(id);
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
