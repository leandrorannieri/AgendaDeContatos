import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgendaContatos {
    private static final String ARQUIVO = "contatos.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Contatos> contatos = carregarContatos();

        System.out.println("CADASTRO DE CONTATOS");
        System.out.println("---------------------");

        while (contatos.size() < 100) {
            System.out.println("\nContato " + (contatos.size() + 1));

            String nome;
            do {
                System.out.print("Digite o nome do contato: ");
                nome = scanner.nextLine().trim();
                if (nome.isEmpty()) {
                    System.out.println("Nome não pode ser vazio. Tente novamente.");
                }
            } while (nome.isEmpty());

            String telefone;
            do {
                System.out.print("Digite o telefone do contato (somente números): ");
                telefone = scanner.nextLine().trim();
                if (!telefone.matches("\\d{10,11}")) {
                    System.out.println("Telefone inválido! Deve conter entre 10 e 11 dígitos.");
                }
            } while (!telefone.matches("\\d{10,11}"));

            String email;
            do {
                System.out.print("Digite o email do contato: ");
                email = scanner.nextLine().trim();
                if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$")) {
                    System.out.println("Email inválido! Tente novamente.");
                }
            } while (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$"));

            Contatos novoContato = new Contatos(nome, telefone, email);
            contatos.add(novoContato);
            salvarContato(novoContato);

            System.out.print("Deseja adicionar outro contato? (s/n): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("n")) break;
        }

        scanner.close();

        System.out.println("\nAGENDA DE CONTATOS");
        System.out.println("------------------");
        for (Contatos contato : contatos) {
            System.out.println(contato);
        }
    }

    public static void salvarContato(Contatos contato) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            writer.write(contato.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o contato: " + e.getMessage());
        }
    }

    public static List<Contatos> carregarContatos() {
        List<Contatos> contatos = new ArrayList<>();
        File file = new File(ARQUIVO);
        if (!file.exists()) return contatos;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                contatos.add(Contatos.fromFileString(linha));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os contatos: " + e.getMessage());
        }
        return contatos;
    }
}
