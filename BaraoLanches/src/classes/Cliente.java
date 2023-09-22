package classes;

public class Cliente {
    private String nome;
    private String datNasc;
    private String cpf; // CPF como String
    private String telefone;
    private String endereco;

    public Cliente(String nome, String datNasc, String cpf, String telefone, String endereco) {
        this.nome = nome;
        this.datNasc = datNasc;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatNasc() {
        return datNasc;
    }

    public void setDatNasc(String datNasc) {
        this.datNasc = datNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String mostrarDados() {
        return "**********************************\n" +
                "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\nData de Nascimento: " + datNasc +
                "\nTelefone: " + telefone +
                "\nEndereço: " + endereco + "\n";
    }


}
