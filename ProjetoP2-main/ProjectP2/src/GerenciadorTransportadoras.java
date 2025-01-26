
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTransportadoras{
    private ArrayList<Transportadora> transportadoras;

    public GerenciadorTransportadoras(){
        this.transportadoras = new ArrayList<>();
    }

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = new ArrayList<>(transportadoras);
    }

    public ArrayList<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public  TransportadoraInterna encontrarTransportadoraInternaArmazem(Armazem arm){
        for(Transportadora t : transportadoras){
            if(t instanceof TransportadoraInterna && ((TransportadoraInterna)t).getArmazemAssociado().equals(arm)){
                return (TransportadoraInterna)t;
            }
        }
        return null;
    }

    public TransportadoraExterna encontrarTransportadoraExterna(){
        for(Transportadora t : transportadoras){
            if(t instanceof TransportadoraExterna){
                return (TransportadoraExterna)t;
            }
        }
        return null;
    }

    public void adicionarTransportadoraInterna(Consola consola, GerenciadorArmazens gA) {
        int id = consola.lerInt("ID:");
        consola.limparBuffer();
        String nome = consola.lerString("Nome da Transportadora:");
        consola.limparBuffer();
        int capacidade = consola.lerInt("Capacidade da Transportadora:");
        consola.limparBuffer();

        consola.escrever("Escolha um armazém para associar à transportadora:");
        if (gA.getArmazens().isEmpty()) {
            consola.escrever("Nenhum armazém disponível. Você deve criar um armazém primeiro.");
            return;
        }

        for (int i = 0; i < gA.getArmazens().size(); i++) {
            consola.escrever((i + 1) + " - " + gA.getArmazens().get(i).getNome());
        }
        int escolhaArmazem = consola.lerInt("Digite o número do armazém associado:");
        consola.limparBuffer();

        if (escolhaArmazem < 1 || escolhaArmazem > gA.getArmazens().size()) {
            consola.escrever("Opção inválida. Cancelando a criação da transportadora interna.");
            return;
        }

        Armazem armazemAssociado = gA.getArmazens().get(escolhaArmazem - 1);

        consola.escrever("Transportadora Interna: ");
        consola.escrever("\nID: " + id);
        consola.escrever("\nNome: " + nome);
        consola.escrever("\nCapacidade: " + capacidade);
        consola.escrever("\nArmazém Associado: " + armazemAssociado.getNome());

        if (consola.desejaProsseguir()) {
            TransportadoraInterna interna = new TransportadoraInterna(id, capacidade, nome, armazemAssociado);
            transportadoras.add(interna);
            consola.escrever("\nTransportadora interna adicionada com sucesso!");
        } else {
            consola.escrever("Cancelado.");
        }
    }

    public void adicionarTransportadoraExterna(Consola consola) {
        int id = consola.lerInt("ID:");
        consola.limparBuffer();
        String nome = consola.lerString("Nome da Transportadora:");
        consola.limparBuffer();
        int capacidade = consola.lerInt("Capacidade da Transportadora:");
        consola.limparBuffer();
        String pais = consola.lerString("País da Transportadora:");
        consola.limparBuffer();

        consola.escrever("Transportadora Externa: ");
        consola.escrever("ID: " + id);
        consola.escrever("Nome: " + nome);
        consola.escrever("Capacidade: " + capacidade);
        consola.escrever("País: " + pais);

        if (consola.desejaProsseguir()) {
            TransportadoraExterna externa = new TransportadoraExterna(id, capacidade, nome, pais);
            transportadoras.add(externa);
            consola.escrever("Transportadora externa adicionada com sucesso!");
        } else {
            consola.escrever("Cancelado.");
        }
    }

    public void listarTransportadoras(Consola consola) {
        if (transportadoras.isEmpty()) {
            consola.escrever("Nao ha transportadoras cadastradas.");
        } else {
            consola.escrever("Lista de Transportadoras:\n");
            for (Transportadora transportadora : transportadoras) {
                consola.escrever(transportadora.toString());
            }
        }
    }

    public void excluirTransportadora(Consola consola) {
        int id = consola.lerInt("ID da transportadora para excluir:");
        for (Transportadora transportadora : transportadoras) {
            if (transportadora.getId() == id) {
                transportadoras.remove(transportadora);
                consola.escrever("Transportadora removida com sucesso!");
                return;
            }
        }
        consola.escrever("Transportadora com ID " + id + " nao encontrada.");
    }

    public void atualizarTransportadora(Consola consola, GerenciadorArmazens gA) {
        int id = consola.lerInt("ID da transportadora para atualizar:");
        for (Transportadora transportadora : transportadoras) {
            if (transportadora.getId() == id) {
                consola.escrever("Atualizando Transportadora ID: " + id);

                String novoNome = consola.lerString("Novo Nome:");
                consola.limparBuffer();
                int novaCapacidade = consola.lerInt("Nova Capacidade:");
                consola.limparBuffer();


                if (transportadora instanceof TransportadoraInterna) {
                    consola.escrever("Novo armazem para associar:");
                    for (int i = 0; i < gA.getArmazens().size(); i++) {
                        consola.escrever((i + 1) + " - " + gA.getArmazens().get(i).getNome());
                    }
                    int escolhaArmazem = consola.lerInt("Digite o numero do armazem associado:");
                    consola.limparBuffer();

                    if (escolhaArmazem < 1 || escolhaArmazem > gA.getArmazens().size()) {
                        consola.escrever("Opcao invalida. O armazem associado permanecere o mesmo.");
                    } else {
                        Armazem novoArmazem = gA.getArmazens().get(escolhaArmazem - 1);
                        ((TransportadoraInterna) transportadora).setArmazemAssociado(novoArmazem);
                    }


                } else if (transportadora instanceof TransportadoraExterna) {
                    String novoPais = consola.lerString("Novo Pais:");
                    ((TransportadoraExterna) transportadora).setPais(novoPais);
                }

                transportadora.setNome(novoNome);
                transportadora.setCapacidade(novaCapacidade);
                consola.escrever("Transportadora atualizada com sucesso!");
                return;
            }
        }
        consola.escrever("Transportadora com ID " + id + " nao encontrada.");
    }
}