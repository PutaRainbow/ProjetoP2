
public class App {
    public static void main(String[] args) throws Exception {
        int opcao=-1, opcaoMercadoria=-1, opcaoArmazem=-1, opcaoTransporte=-1;
        Consola consola = new Consola();
        GerenciadorArmazens gerenciadorArmazens = new GerenciadorArmazens();
        GerenciadorTransportadoras gerenciadorTransportadoras = new GerenciadorTransportadoras();
        GerenciadorMercadorias gerenciadorMercadorias = new GerenciadorMercadorias();
        gerenciadorMercadorias.setMercadorias(Arquivo.carregarMercadorias());
        gerenciadorArmazens.setArmazens(Arquivo.carregarArmazens());
        gerenciadorTransportadoras.setTransportadoras(Arquivo.carregarTransportadoras()); 
        while (opcao!=0) {
            System.out.println("\n_____________________________:Menu:_____________________________");
            System.out.println("1-Mercadorias");
            System.out.println("2-Armazens");
            System.out.println("3-Transportadoras");
            System.out.println("4-Movimentar Mercadorias");
            System.out.println("5-Relatorios");
            System.out.println("0-Sair");
            System.out.println("_______________________________________________________________");
            opcao= consola.lerInt("Escolhe uma opcao: ");
        switch (opcao) {
            case 1:
                opcaoMercadoria=-1;
                while (opcaoMercadoria!=0) {
                    System.out.println("\n_____________________________:Mercadoria:_____________________________");
                    System.out.println("1- Criar mercadoria");
                    System.out.println("2- Registrar Mercadoria em Armazem");
                    System.out.println("3- Listar Mercadorias");
                    System.out.println("4- Excluir Mercadoria");
                    System.out.println("5- Atualizar Mercadoria");
                    System.out.println("0- Sair");
                    System.out.println("_______________________________________________________________");
                    opcaoMercadoria= consola.lerInt("Escolhe uma opcao: ");
                    switch (opcaoMercadoria) {
                        case 1:
                        gerenciadorMercadorias.adicionarMercadoria(consola);
                            break;
                        case 2:
                        System.out.println("\n_____________________________:Registrar Mercadoria em Armazém:_____________________________");
                        String tag = consola.lerString("Digite a tag IoT da mercadoria:");
                        //refatorar
                        Mercadoria m = gerenciadorMercadorias.buscarMercadoriaPorTag(tag);
                        if(m == null){
                            consola.escrever("Impossivel encontrar mercadoria");
                            return;
                        }
                            gerenciadorArmazens.adicionarMercadoriaArmazem(consola, m); 
                            //Devias gravar arquivo
                            break;
                        case 3:
                        gerenciadorMercadorias.listarMercadorias(consola);
                            break;
                        case 4:
                        int id = consola.lerInt("ID:");
                        gerenciadorMercadorias.excluirMercadoria(consola, id);
                        //devias gravar arquivo
                            break;
                        case 5:
                        int a = consola.lerInt("ID:");
                        gerenciadorMercadorias.atualizarMercadoria(consola, a);
                            break;
                        default:
                            break;
                    }
                }
                break;
            case 2:
               opcaoArmazem=-1;
               while (opcaoArmazem!=0) {
                System.out.println("\n_____________________________:Armazem:_____________________________");
                System.out.println("1- Criar Armazem");
                System.out.println("2- Listar Armazens");
                System.out.println("3- Listar Mercadoria em Armazem");
                System.out.println("4- Excluir Armazem");
                System.out.println("5- Atualizar Armazem");
                System.out.println("0- Sair");
                System.out.println("_______________________________________________________________");
                opcaoArmazem= consola.lerInt("Escolhe uma opcao: ");
                switch (opcaoArmazem) {
                    case 1:
                    gerenciadorArmazens.adicionarArmazem(consola);
                        break;
                    case 2:
                    gerenciadorArmazens.listarArmazens(consola);
                        break;
                    case 3:
                    gerenciadorArmazens.listarMercadoriasArmazem(consola);
                        break;
                    case 4:
                    gerenciadorArmazens.excluirArmazem(consola);
                        break;
                    case 5:
                    gerenciadorArmazens.atualizarArmazem(consola);
                        break;
                    default:
                        break;
                }
               }
                break;
            case 3:
                opcaoTransporte=-1;
                while (opcaoTransporte!=0) {
                    System.out.println("\n_____________________________:Transportadora:_____________________________");
                    System.out.println("1- Criar Transportadora");
                    System.out.println("2- Listar Transportadoras");
                    System.out.println("3- Excluir Transportadora");
                    System.out.println("4- Atualizar Transportadora");
                    System.out.println("0- Sair");
                    System.out.println("_______________________________________________________________");
                    opcaoTransporte= consola.lerInt("Escolhe uma opcao: ");
                    switch (opcaoTransporte) {
                        case 1:
                        System.out.println("\n_____________________________:Transportadora:_____________________________");
                        System.out.println("1- Interna");
                        System.out.println("2- Externa");
                        int transporteIE = consola.lerInt("Escolhe uma opcao: ");

                        if(transporteIE==1) {
                            gerenciadorTransportadoras.adicionarTransportadoraInterna(consola, gerenciadorArmazens);
                        }else if(transporteIE==2) {
                            gerenciadorTransportadoras.adicionarTransportadoraExterna(consola);
                        }
                            break;
                        case 2:
                            gerenciadorTransportadoras.listarTransportadoras(consola);
                            break;
                        case 3:
                            gerenciadorTransportadoras.excluirTransportadora(consola);
                            break;
                        case 4:
                            gerenciadorTransportadoras.atualizarTransportadora(consola, gerenciadorArmazens);
                            break;
                        default:
                            break;
                    } 
                }
                 break;
            case 4:
            System.out.println("\n_____________________________:Movimentar Mercadoria:_____________________________");
                    String nomeArmazemOrigem = consola.lerString("Digite o nome do armazém de origem:");
                    String nomeArmazemDestino = consola.lerString("Digite o nome do armazém de destino:");
                    Armazem armOrigem = gerenciadorArmazens.encontrarArmazemPorNome(nomeArmazemOrigem);
                    Armazem armDestino = gerenciadorArmazens.encontrarArmazemPorNome(nomeArmazemDestino);
                    if(armOrigem == null){
                        consola.escrever("Impossivel encontrar armazem de origem");
                        return;
                    }
                    if(armDestino == null){
                        consola.escrever("Impossivel encontrar armazem de destino");
                        return;
                    }
                    consola.escrever(armOrigem.listarMercadorias());
                    String tagIoT = consola.lerString("\nDigite a tag IoT da mercadoria:");
                    gerenciadorMercadorias.transportarMercadoria(consola,tagIoT, nomeArmazemOrigem, nomeArmazemDestino, gerenciadorArmazens, gerenciadorTransportadoras);
                    //devias gravar arquivo
                    consola.escrever(armDestino.listarMercadorias());
                break;
            default:
            consola.escrever("Opcao invalida");
                break;
            
            case 5:
               gerenciadorArmazens.gerarRelatorioTexto();
            break;
        }       
        
    }
    Arquivo.salvarMercadorias(gerenciadorMercadorias.getMercadorias());
    Arquivo.salvarArmazens(gerenciadorArmazens.getArmazens());
    Arquivo.salvarTransportadoras(gerenciadorTransportadoras.getTransportadoras());
    }
}
