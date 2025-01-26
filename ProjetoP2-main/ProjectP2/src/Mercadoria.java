import java.io.Serializable;

public abstract class Mercadoria implements Serializable {
    private int id;
    private String descricao;
    private int peso;
    private int volume;
    private String tag;

    public Mercadoria(int id, String descricao, int peso, int volume, String tag) {
        this.id = id;
        this.descricao = descricao;
        this.peso = peso;
        this.volume = volume;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPeso() {
        return peso;
    }

    public int getVolume() {
        return volume;
    }


    public String getTag() {
        return tag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    public void setVolume(int volume) {
        this.volume = volume;
    }



    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Mercadoria{" +
                "id=" + id +
                ", descrição='" + descricao + '\'' +
                ", peso=" + peso +
                ", volume=" + volume +
                ", tag='" + tag + '\'' +
                '}';
    }


}
