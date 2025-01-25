public class TransportadoraExterna extends Transportadora{

    private String pais;

    public TransportadoraExterna( int id, int capacidade, String nome, String pais) {
        super( id, capacidade, nome);
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setNome(String nome) {
        this.setNome(nome);
    }

    @Override
    public String toString() {
        return "TransportadoraExterna{" +
                "id=" + getId() +
                ", capacidade=" + getCapacidade() +
                ", nome='" + getNome() + '\'' +
                ", pais='" + pais + '\'' +
                "}\n";
    }
}
