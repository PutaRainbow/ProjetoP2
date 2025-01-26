public class MercadoriaFragil extends Mercadoria {
    public MercadoriaFragil(int id, String descricao, int peso, int volume, String tag) {
        super(id, descricao, peso, volume, tag);
    }

    @Override
    public String toString() {
        return "MercadoriaFragil{" + super.toString() + '}';
    }
}
