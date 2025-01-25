import java.util.ArrayList;

public class Transportes {
    private ArrayList<TransportadoraInterna> transportadorasInternas;
    private ArrayList<TransportadoraExterna> transportadorasExternas;
    private ArrayList<Armazem> armazens;

    public Transportes() {
        this.transportadorasInternas = new ArrayList<>();
        this.transportadorasExternas = new ArrayList<>();
        this.armazens = new ArrayList<>();
    }

    public void adicionarArmazem(Armazem armazem) {
        this.armazens.add(armazem);
    }

    public void transportarMercadoria(String tagIoT, String nomeArmazemOrigem, String nomeArmazemDestino) {
        Armazem origem = encontrarArmazem(nomeArmazemOrigem);
        Armazem destino = encontrarArmazem(nomeArmazemDestino);
        Mercadoria mercadoria = encontrarMercadoria(tagIoT, origem);

        if (origem == null || destino == null || mercadoria == null) {
            System.out.println("Erro: Armazem ou mercadoria não encontrados.");
            return;
        }

        listarTransportadorasInternas();

        TransportadoraInterna transportadoraInternaO = encontrarTransportadoraInterna(origem);
        TransportadoraExterna transportadoraExterna = encontrarTransportadoraExterna();
        TransportadoraInterna transportadoraInternaD = encontrarTransportadoraInterna(destino);

        if (transportadoraInternaO == null || transportadoraInternaD == null) {
            System.out.println("Erro: Transportadora adequada não encontrada.");
            return;
        }

        origem.removerMercadoria(mercadoria);
        System.out.println("Mercadoria " + tagIoT + " transportada internamente para a transportadora interna.");
        System.out.println("Mercadoria " + tagIoT + " transportada externamente para o destino.");
        destino.adicionarMercadoria(mercadoria);
    }

    private Armazem encontrarArmazem(String nome) {
        for (Armazem armazem : armazens) {
            if (armazem.getNome().equalsIgnoreCase(nome)) {
                return armazem;
            }
        }
        return null;
    }

    public void listarMercadorias(String nomeArmazem) {
        Armazem armazem = encontrarArmazem(nomeArmazem);
        if (armazem == null) {
            System.out.println("Erro: Armazém não encontrado.");
            return;
        }
    
        System.out.println("Mercadorias no armazém " + nomeArmazem + ":");
        for (Mercadoria m : armazem.getMercadorias()) {
            System.out.println(m);
        }
    }

    private Mercadoria encontrarMercadoria(String tagIoT, Armazem armazem) {
        
        if (armazem == null) {
            return null;
        }

        for (Mercadoria m : armazem.getMercadorias()) {
            if (m.getTag().equals(tagIoT)) {
                return m;
            }
        }
        return null;
    }

    private TransportadoraInterna encontrarTransportadoraInterna(Armazem armazem) {
        for (TransportadoraInterna transportadora : transportadorasInternas) {
            if (transportadora.getArmazemAssociado().equals(armazem)) {
                return transportadora;
            }
        }
        return null;
    }

    private TransportadoraExterna encontrarTransportadoraExterna() {
        for (TransportadoraExterna transportadora : transportadorasExternas) {
            if(transportadora != null){
                return transportadora;
        }
    }
    return null;
    }

    public void adicionarTransportadoraExterna(TransportadoraExterna transportadoraExterna) {
        this.transportadorasExternas.add(transportadoraExterna);
    }

    public void adicionarTransportadoraInterna(TransportadoraInterna transportadoraInterna) {
        this.transportadorasInternas.add(transportadoraInterna);
    }

    public void listarTransportadorasInternas() {
        for(TransportadoraInterna t: transportadorasInternas){
           System.out.println(t.toString());
        }
    }
}
