import java.io.Serializable;
import java.util.ArrayList;

public class Armazem implements Serializable{
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
        this.mercadorias = new ArrayList<>();
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

    public Mercadoria encontrarMercadoriaPorTag(String tag){
        for (Mercadoria m : mercadorias){
            if(m.getTag().equals(tag)){
                return m;
            }
        }
        return null;
    }
   
    public void adicionarMercadoria(Mercadoria mercadoria){
        mercadorias.add(mercadoria);
        System.out.println("Mercadoria " + mercadoria.getId() + " adicionada ao " + getNome());
    }
    public void removerMercadoria(Mercadoria mercadoria){
        mercadorias.remove(mercadoria);
        System.out.println("Mercadoria " + mercadoria.getId() + " removida do " + getNome());
    }

    public String listarMercadorias(){
        if(this.mercadorias.size() == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(Mercadoria m : mercadorias){
            sb.append(m.toString());
        }
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Armazem armazem = (Armazem) o;
        if (tipo != armazem.tipo) return false;
        if (dimensoes != armazem.dimensoes) return false;
        if (capacidade != armazem.capacidade) return false;
        if (nome != null ? !nome.equals(armazem.nome) : armazem.nome != null) return false;
        return morada != null ? morada.equals(armazem.morada) : armazem.morada == null;
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
