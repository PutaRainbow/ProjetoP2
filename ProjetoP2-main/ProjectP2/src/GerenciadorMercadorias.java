import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorMercadorias implements Transportes{
    private ArrayList<Mercadoria> mercadorias;
    
    public GerenciadorMercadorias() {
        this.mercadorias = new ArrayList<>(); 
    }

    public void adicionarMercadoria(Consola consola) {
        int id = consola.lerInt("ID:");
    consola.limparBuffer();
    String descricao = consola.lerString("Descricao:");
    consola.limparBuffer();
    int peso = consola.lerInt("Peso:");
    consola.limparBuffer();
    int volume = consola.lerInt("Volume:");
    consola.limparBuffer();
    consola.escrever("Tipo da Mercadoria: \n");
    consola.escrever("1 - Normal\n");
    consola.escrever("2 - Perecível\n");
    consola.escrever("3 - Fragil\n");
    int tipo = consola.lerInt("Tipo:");
    consola.limparBuffer();
    String tag = consola.lerString("Tag IoT:");
    consola.limparBuffer();

    Mercadoria m ;

    switch (tipo) {
        case 1 -> m = new MercadoriaNormal(id, descricao, peso, volume, tag);
        case 2 -> {
            consola.escrever("Informe a data de validade (YYYY-MM-DD):");
            String data = consola.lerString("Data de Validade:");
            consola.limparBuffer();
            m = new MercadoriaPerecivel(id, descricao, peso, volume, tag, LocalDate.parse(data));
        }
        case 3 -> m = new MercadoriaFragil(id, descricao, peso, volume, tag);
        default -> {
            consola.escrever("Tipo inválido!");
            return;
        }
    }

    if (consola.desejaProsseguir()) {
        mercadorias.add(m);
        consola.escrever("Mercadoria Criada.");
    } else {
        consola.escrever("Cancelado.");
    }

    }

    public void listarMercadorias(Consola consola) {
        if (mercadorias.isEmpty()) {
            consola.escrever("Nao ha mercadorias cadastradas.");
        } else {
            consola.escrever("Lista de Mercadorias:");
            for (Mercadoria mercadoria : mercadorias) {
                consola.escrever(mercadoria.toString()+"\n");

            }
        }
    }

    public void excluirMercadoria(Consola consola, int id) {
        for (Mercadoria mercadoria : mercadorias) {
            if (mercadoria.getId() == id) {
                mercadorias.remove(mercadoria);
                System.out.println("Mercadoria removida com sucesso!");
                return;
            }
        }
    }

    public ArrayList<Mercadoria> getMercadorias() {
        return mercadorias;
    }

    public void setMercadorias(ArrayList<Mercadoria> mercadorias) {
        this.mercadorias = mercadorias;
    }

    public void atualizarMercadoria(Consola consola, int id) {
        for (Mercadoria mercadoria : mercadorias) {
        if (mercadoria.getId() == id) {
            consola.escrever("Atualizando mercadoria ID: " + id);
            
            String novaDescricao = consola.lerString("Nova Descricao:");
            consola.limparBuffer();
            int novoPeso = consola.lerInt("Novo Peso:");
            consola.limparBuffer();
            int novoVolume = consola.lerInt("Novo Volume:");
            consola.limparBuffer();
            System.out.println("Tipo da Mercadoria: ");
            System.out.println("1 - Normal");
            System.out.println("2 - Perecível");
            System.out.println("3 - Fragil");
            int novoTipo = consola.lerInt("Novo Tipo:");
            consola.limparBuffer();
            String novaTag = consola.lerString("Nova Tag IoT:");
            consola.limparBuffer();

            Mercadoria novaMercadoria = null;
            switch (novoTipo) {
                case 1 -> novaMercadoria = new MercadoriaNormal(id, novaDescricao, novoPeso, novoVolume, novaTag);
                case 2 -> {
                    consola.escrever("Informe a data de validade (YYYY-MM-DD):");
                    String data = consola.lerString("Data de Validade:");
                    novaMercadoria = new MercadoriaPerecivel(id, novaDescricao, novoPeso, novoVolume, novaTag, LocalDate.parse(data));
                }
                case 3 -> novaMercadoria = new MercadoriaFragil(id, novaDescricao, novoPeso, novoVolume, novaTag);
                default -> {
                    consola.escrever("Tipo inválido!");
                    return;
                }
            }

            mercadorias.remove(mercadoria); // Remove a antiga
            mercadorias.add(novaMercadoria); // Adiciona a nova
            consola.escrever("Mercadoria atualizada com sucesso!");
            return;
        }
    }
    consola.escrever("Mercadoria com ID " + id + " não encontrada.");
    }

    public void registrarMercadoriaEmArmazem(Armazem armazem, Mercadoria mercadoria) {
        
        if (mercadoria != null && armazem != null) {
            armazem.adicionarMercadoria(mercadoria);
            System.out.println("Mercadoria registrada no armazém com sucesso!");
        } else {
            System.out.println("Erro: Mercadoria ou armazém inválido.");
        }
    }

    public Mercadoria buscarMercadoriaPorTag(String tag) {
        for (Mercadoria mercadoria : mercadorias) {
            if (mercadoria.getTag().equals(tag)) {
                return mercadoria;
            }
        }
        return null;
    }

    public void transportarMercadoria(Consola consola,String tagIoT, String nomeArmazemOrigem, String nomeArmazemDestino,GerenciadorArmazens gerArm,GerenciadorTransportadoras gerTransport){
        Armazem origem = gerArm.encontrarArmazemPorNome(nomeArmazemOrigem);
        Armazem destino = gerArm.encontrarArmazemPorNome(nomeArmazemDestino);
        if(origem == null || destino == null) return;
        Mercadoria mercadoria = origem.encontrarMercadoriaPorTag(tagIoT);
        if(mercadoria == null){
            consola.escrever("A mercadoria que deseja mover não foi encontrada no armazém de origem");
            return;
        }
        TransportadoraInterna transportadoraInternaO = gerTransport.encontrarTransportadoraInternaArmazem(origem);
        TransportadoraExterna transportadoraExterna = gerTransport.encontrarTransportadoraExterna();
        TransportadoraInterna transportadoraInternaD = gerTransport.encontrarTransportadoraInternaArmazem(destino);

        if (transportadoraInternaO == null || transportadoraInternaD == null) {
            System.out.println("Erro: Transportadora adequada não encontrada.");
            return;
        }
        origem.removerMercadoria(mercadoria);
        System.out.println("Mercadoria " + tagIoT + " transportada internamente para a transportadora interna.");
        System.out.println("Mercadoria " + tagIoT + " transportada externamente para o destino.");
        destino.adicionarMercadoria(mercadoria);
    }

    @Override
    public String toString() {
        return "GerenciadorMercadorias{" +
                "mercadorias=" + mercadorias +
                '}';
    }
    
}
