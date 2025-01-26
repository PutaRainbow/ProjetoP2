public class TransportadoraInterna extends Transportadora {
    private Armazem armazemAssociado;

    public TransportadoraInterna(int id, int capacidade,String nome, Armazem armazemAssociado) {
        super(id , capacidade, nome);
        this.armazemAssociado = armazemAssociado;
    }

    public Armazem getArmazemAssociado() {
        return armazemAssociado;
    }

    public void setArmazemAssociado(Armazem armazemAssociado) {
        this.armazemAssociado = armazemAssociado;
    }

    @Override
    public String toString() {
        return "TransportadoraInterna{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", capacidade=" + getCapacidade() +
                ", armazemAssociado=" + (armazemAssociado != null ? armazemAssociado.getNome() : "Nenhum") +
                "}\n";
    }
}