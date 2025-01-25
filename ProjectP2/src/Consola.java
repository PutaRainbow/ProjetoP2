import java.util.Scanner;

public class Consola {
     Scanner teclado;

    public Consola(){
    teclado = new Scanner(System.in);
    }

    public void escrever(String texto){
        System.out.print(texto);
    }

    public String lerString(String texto){
        escrever(texto);
        return teclado.nextLine();
    }

    public int lerInt(String texto){
        boolean valido = false;
        int valor = -1;
        do{
            try{
                System.out.println(texto);
                valor = teclado.nextInt();
                teclado.nextLine(); 
                valido = true;
            }catch(Exception e){
                System.out.println("Valor invalido. Tente novamente.");
                teclado.nextLine(); }
        }while(!valido);
        return valor;
    
    }
    
    public double lerDouble(String texto){
        boolean valido = false;
        double valor = -1;
        do{
            try{
                System.out.println(texto);
                valor = teclado.nextDouble();
                teclado.nextLine(); 
                valido = true;
            }catch(Exception e){
                System.out.println("Valor inválido. Tente novamente.");
                teclado.nextLine(); // Limpar o buffer em caso de erro
            }
        }while(!valido);
        return valor;
    }

    public boolean desejaProsseguir(){
        int opcao = -1;
        do{
            escrever("Deseja prosseguir com a operação?");
            escrever("1 - Sim");
            escrever("2 - Não");
            opcao = lerInt("Escolha uma opção:");
        }while(opcao != 1 && opcao != 2);
        return opcao == 1;
    }

    public void fechar(){
        if (teclado != null){ 
            teclado.close();
        }
    }

    public void esperarEnter(){
        System.out.println("Prima Enter para continuar...");
        teclado.nextLine();
    }

    public void esperarEnter(String texto){
        escrever(texto);
        teclado.nextLine();
    }

    public void limparBuffer(){
        teclado.nextLine();
    }
}
