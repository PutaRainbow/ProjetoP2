import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


public class GerenciadorArmazens {
    private ArrayList<Armazem> armazens = new ArrayList<>();
    private ArrayList<Mercadoria> mercadorias = new ArrayList<>();
    private Transportes transportes;
    
    public GerenciadorArmazens(Transportes transportes) {
        this.transportes = transportes;
    }
    
    public ArrayList<Armazem> getArmazens() {
        return armazens;
        
    }
    
    public void setArmazens(ArrayList<Armazem> armazens) {
        this.armazens = armazens;
    }
    public void adicionarArmazem(Consola consola) {
        int tipo = consola.lerInt("Tipo do Armazem (1 - Normal, 2 - Reciclagem):");
        String nome = consola.lerString("Nome do Armazem:");
        String morada = consola.lerString("Morada:");
        int dimensoes = consola.lerInt("Dimensoes (m^2):");
        int capacidade = consola.lerInt("Capacidade (m^3):");
        Armazem armazem = new Armazem(tipo, nome, morada, dimensoes, capacidade);
        armazens.add(armazem);
        consola.escrever("Armazém criado com sucesso!");
        transportes.adicionarArmazem(armazem);
    }



    public void excluirArmazem(Consola consola){
        String nomeEscolhido = consola.lerString("Nome do armazém para excluir:");
        for (Armazem armazem : armazens) {
            if (armazem.getNome().equalsIgnoreCase(nomeEscolhido)) {
                armazens.remove(armazem);
                consola.escrever("Armazém \"" + nomeEscolhido + "\" excluído com sucesso!");
                return;
            }
        }
        consola.escrever("Armazém com o nome \"" + nomeEscolhido + "\" não encontrado.");

    }

    public void listarArmazens(Consola consola) {
        if (armazens.isEmpty()) {
            consola.escrever("Não há armazéns cadastrados.");
        } else {
            consola.escrever("Lista de Armazéns:\n");
            for (Armazem armazem : armazens) {
                consola.escrever(armazem.toString());
            }
        }
    }

    public Armazem encontrarArmazemPorNome(String nomeArmazem){
        for (Armazem a : armazens){
            if(a.getNome.equalsIgnoreCase(nomeArmazem)){
                return a;
            }
        }
        return null;
    }

    public void atualizarArmazem(Consola consola) {
        String nomeEscolhido = consola.lerString("Nome do armazém para atualizar:");
        for (Armazem armazem : armazens) {
            if (armazem.getNome().equalsIgnoreCase(nomeEscolhido)) { // Comparação ignorando maiúsculas/minúsculas
                consola.escrever("Atualizando informações do armazém: " + armazem.getNome());
                
                String novoNome = consola.lerString("Novo Nome:");
                armazem.setNome(novoNome);

                String novaMorada = consola.lerString("Nova Morada:");
                armazem.setMorada(novaMorada);

                int novasDimensoes = consola.lerInt("Novas Dimensões (m²):");
                armazem.setDimensoes(novasDimensoes);

                int novaCapacidade = consola.lerInt("Nova Capacidade (m³):");
                armazem.setCapacidade(novaCapacidade);

                consola.escrever("Armazem atualizado com sucesso!");
                return;
            }
        }
        consola.escrever("Armazem com o nome \"" + nomeEscolhido + "\" nao encontrado.");
    }

    public void adicionarMercadoriaArmazem(Consola consola, Mercadoria mercadoria) {
        String nomeEscolhido = consola.lerString("Nome do armazem para adicionar mercadoria:");

        for (Armazem armazem : armazens) {
            if (armazem.getNome().equalsIgnoreCase(nomeEscolhido)) {
                if (armazem.getTipo() == 2) {
                    if(!(mercadoria instanceof MercadoriaPerecivel)) {
                    consola.escrever("Armazem de Reciclagem. Mercadoria não pode ser adicionada.");
                    return;
                    }
                }
                armazem.adicionarMercadoria(mercadoria);
                consola.escrever("Mercadoria adicionada com sucesso ao armazem " + armazem.getNome() + "!");
                return; 
            }
        }
    
        consola.escrever("Armazem com o nome \"" + nomeEscolhido + "\" nao encontrado.");
    }

    public void removerMercadoriaArmazem(Consola consola, Mercadoria mercadoria) {
        String nomeEscolhido = consola.lerString("Nome do armazem para remover mercadoria:");
        for (Armazem armazem : armazens) {
            if (armazem.getNome().equalsIgnoreCase(nomeEscolhido)) {
                String tagMercadoria = consola.lerString("Tag da Mercadoria:");
                for (Mercadoria m : mercadorias) {
                    if (mercadoria.getTag()==tagMercadoria) {
                        armazem.removerMercadoria(mercadoria);
                        consola.escrever("Material removido com sucesso do armazem " + armazem.getNome() + "!");
                        return;
                    }
                }
                consola.escrever("Material com a tag \"" + tagMercadoria + "\" não encontrado no armazem " + armazem.getNome() + ".");
                return;
            }
        }
        consola.escrever("Armazem com o nome \"" + nomeEscolhido + "\" não encontrado.");
    }
    
    public void listarMercadoriasArmazem(Consola consola) {
        String nomeEscolhido = consola.lerString("Nome do armazem para listar mercadorias:");
        for (Armazem armazem : armazens) {
            if (armazem.getNome().equalsIgnoreCase(nomeEscolhido)) {
                consola.escrever("Lista de Mercadorias do Armazem " + armazem.getNome() + ":");
                for (Mercadoria mercadoria : armazem.getMercadorias()) {
                    consola.escrever(mercadoria.toString());
                }
                return;
            }
        }
        consola.escrever("Armazem com o nome \"" + nomeEscolhido + "\" não encontrado.");
    }

    public void gerarRelatorioTexto() {
        try (FileWriter writer = new FileWriter("relatorio_mercadorias.txt")) {
            writer.write("Relatório de Mercadorias nos Armazéns\n");
            writer.write("=====================================\n\n");

            for (Armazem armazem : armazens) {
                writer.write("Armazém: " + armazem.getNome() + "\n");
                writer.write("Tipo: " + armazem.getTipo() + "\n");
                writer.write("Mercadorias:\n");

                for (Mercadoria mercadoria : armazem.getMercadorias()) {
                    writer.write(" - Tag IoT: " + mercadoria.getTag() + "\n");
                    writer.write("   Descricao: " + mercadoria.getDescricao() + "\n");
                    writer.write("   Peso: " + mercadoria.getPeso() + "kg\n");
                }
                writer.write("\n");
            }
            System.out.println("Relatório gerado com sucesso: relatorio_mercadorias.txt");
        } catch (IOException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }


}