import java.util.List;
import java.util.ArrayList;

public class Armazem {
    private ArrayList<Mercadoria> mercadorias;
    private int tipo;
    private String nome;
    private String morada;
    private int dimensoes;
    private int capacidade;

    public Armazem(int tipo, String nome, String morada, int dimensoes, int capacidade) {
        this.tipo = tipo;
        this.nome = nome;
        this.morada = morada;
        this.dimensoes = dimensoes;
        this.capacidade = capacidade;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String getMorada() {
        return morada;
    }

    public double getDimensoes() {
        return dimensoes;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setDimensoes(int dimensoes) {
        this.dimensoes = dimensoes;
    }
     
    public ArrayList<Mercadoria> getMercadorias() {
        return mercadorias;
    }
   
    public void adicionarMercadoria(Mercadoria mercadoria){
        mercadorias.add(mercadoria);
        System.out.println("Mercadoria " + mercadoria.getId() + " adicionada ao " + getNome());
    }
    public void removerMercadoria(Mercadoria mercadoria){
        mercadorias.remove(mercadoria);
        System.out.println("Mercadoria " + mercadoria.getId() + " removida do " + getNome());
    }


    @Override
    public String toString() {
        return "Armazem{" +
                "tipo=" + tipo +    
                "nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", dimensoes=" + dimensoes +
                "}\n";
    }
}
