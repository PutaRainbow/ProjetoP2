public class MercadoriaNormal extends Mercadoria{
    public MercadoriaNormal(int id, String descricao, int peso, int volume, String tag) {
        super(id, descricao, peso, volume, tag);
    }

    @Override
    public String toString() {
        return "MercadoriaNormal{" + super.toString() + '}';
    }
}
