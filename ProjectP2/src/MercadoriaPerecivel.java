import java.time.LocalDate;

public class MercadoriaPerecivel extends Mercadoria{
        private LocalDate dataValidade;

    public MercadoriaPerecivel(int id, String descricao, int peso, int volume, String tag, LocalDate dataValidade) {
        super(id, descricao, peso, volume, tag);
        this.dataValidade = dataValidade;
    }

    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }

    @Override
    public String toString() {
        return "MercadoriaPerecivel{" +
                super.toString() +
                ", dataValidade=" + dataValidade +
                '}';
    }
}
