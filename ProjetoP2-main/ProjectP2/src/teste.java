public class teste {
    public static void main(String[] args) {
        //Armazem : tipo, nome, morada, dimensoes, capacidade
        //Mercadoria : id, nome, peso, volume, valor, quantidade
        //Transportadora : id, capacidade, nome, armazemAssociado
        Arquivo arquivo = new Arquivo();
        GerenciadorArmazens gA = new GerenciadorArmazens();
        GerenciadorMercadorias gM = new GerenciadorMercadorias();
        GerenciadorTransportadoras gT = new GerenciadorTransportadoras();
        Consola consola = new Consola();
        for(int i = 1; i <= 10; i++){
            if(i <= 2){
                Armazem armazem = new Armazem(1, "Armazem " + i, "Morada " + i, 100, 1000);
                gA.getArmazens().add(armazem);
            }
            if(i <= 5){
                Mercadoria mercadoria = new MercadoriaNormal(i, "Mercadoria " + i, 10, 10, "tag" + i);
                gM.getMercadorias().add(mercadoria);
            }
            if(i %2 == 0){
                Transportadora transportadora = new TransportadoraExterna(i, 100, "Transportadora " + i, "Portugal");
                gT.getTransportadoras().add(transportadora);
            }else{
                Transportadora transportadora = new TransportadoraInterna(i, 100, "Transportadora " + i, gA.getArmazens().get(0));
                gT.getTransportadoras().add(transportadora);
            }
            consola.escrever("loop " + i + " executado");
        }
        Transportadora transportadora = new TransportadoraInterna(11, 100, "Transportadora 11", gA.getArmazens().get(1));
        gT.getTransportadoras().add(transportadora);
        Arquivo.salvarArmazens(gA.getArmazens());
        Arquivo.salvarMercadorias(gM.getMercadorias());
        Arquivo.salvarTransportadoras(gT.getTransportadoras());
    }
}
