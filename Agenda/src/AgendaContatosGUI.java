import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaContatosGUI extends JFrame {
    private DefaultListModel<Contatos> listModel;
    private JList<Contatos> listaContatos;
    private JTextField campoNome, campoTelefone, campoEmail;
    private static final String ARQUIVO = "contatos.txt";

    public AgendaContatosGUI() {
        super("Agenda de Contatos");

        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        listaContatos = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listaContatos);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de entrada
        JPanel painelEntrada = new JPanel(new GridLayout(4, 2, 5, 5));
        campoNome = new JTextField();
        campoTelefone = new JTextField();
        campoEmail = new JTextField();

        painelEntrada.add(new JLabel("Nome:"));
        painelEntrada.add(campoNome);
        painelEntrada.add(new JLabel("Telefone:"));
        painelEntrada.add(campoTelefone);
        painelEntrada.add(new JLabel("Email:"));
        painelEntrada.add(campoEmail);

        JButton botaoAdicionar = new JButton("Adicionar Contato");
        painelEntrada.add(botaoAdicionar);

        add(painelEntrada, BorderLayout.SOUTH);

        botaoAdicionar.addActionListener(e -> adicionarContato());

        carregarContatosDoArquivo();

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void adicionarContato() {
        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String email = campoEmail.getText().trim();

        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!telefone.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(this, "Telefone deve conter apenas números (10 ou 11 dígitos).", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$")) {
            JOptionPane.showMessageDialog(this, "Email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Contatos contato = new Contatos(nome, telefone, email);
        listModel.addElement(contato);
        salvarContatoNoArquivo(contato);

        campoNome.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
    }

    private void carregarContatosDoArquivo() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Contatos contato = Contatos.fromFileString(linha);
                listModel.addElement(contato);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar contatos: " + e.getMessage());
        }
    }

    private void salvarContatoNoArquivo(Contatos contato) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            writer.write(contato.toFileString());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar contato: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Ativar Look and Feel do sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Erro ao aplicar Look and Feel.");
        }

        SwingUtilities.invokeLater(AgendaContatosGUI::new);
    }
}
