import java.io.*;
import java.util.ArrayList;

public class Arquivo {
    private static final String FILE_NAME = "mercadorias.dat";

    // Método para salvar a lista de mercadorias em um arquivo
    public static void salvarMercadorias(ArrayList<Mercadoria> mercadorias) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(mercadorias);
            System.out.println("Mercadorias salvas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar mercadorias: " + e.getMessage());
        }
    }

    // Método para carregar a lista de mercadorias do arquivo
    public static ArrayList<Mercadoria> carregarMercadorias() {
        File file = new File(FILE_NAME);
        
        // Se o arquivo não existir, retorna uma lista vazia
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Mercadoria>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar mercadorias: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Método para salvar a lista de armazéns em um arquivo
public static void salvarArmazens(ArrayList<Armazem> armazens) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("armazens.dat"))) {
        oos.writeObject(armazens);
        System.out.println("Armazéns salvos com sucesso!");
    } catch (IOException e) {
        System.err.println("Erro ao salvar armazéns: " + e.getMessage());
    }
}

// Método para carregar a lista de armazéns do arquivo
public static ArrayList<Armazem> carregarArmazens() {
    File file = new File("armazens.dat");
    if (!file.exists()) {
        return new ArrayList<>();
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        return (ArrayList<Armazem>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Erro ao carregar armazéns: " + e.getMessage());
        return new ArrayList<>();
    }
}

// Método para salvar a lista de transportadoras em um arquivo
public static void salvarTransportadoras(ArrayList<Transportadora> transportadoras) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transportadoras.dat"))) {
        oos.writeObject(transportadoras);
        System.out.println("Transportadoras salvas com sucesso!");
    } catch (IOException e) {
        System.err.println("Erro ao salvar transportadoras: " + e.getMessage());
    }
}

// Método para carregar a lista de transportadoras do arquivo
public static ArrayList<Transportadora> carregarTransportadoras() {
    File file = new File("transportadoras.dat");
    if (!file.exists()) {
        return new ArrayList<>();
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        return (ArrayList<Transportadora>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Erro ao carregar transportadoras: " + e.getMessage());
        return new ArrayList<>();
    }
}
}
