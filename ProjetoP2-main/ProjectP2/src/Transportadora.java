import java.io.Serializable;
public abstract class Transportadora implements Serializable{
    private int id;
    private int capacidade;
    private String nome;

    public Transportadora(int id, int capacidade, String nome) {
        this.nome = nome;
        this.id = id;
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

}
