public class Contatos {
    private String nome;
    private String telefone;
    private String email;

    public Contatos(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }

    public void exibirContato() {
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println();
    }

    public String toFileString() {
        return nome + ";" + telefone + ";" + email;
    }

    public static Contatos fromFileString(String line) {
        String[] parts = line.split(";");
        return new Contatos(parts[0], parts[1], parts[2]);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Telefone: " + telefone + ", Email: " + email;
    }
}
