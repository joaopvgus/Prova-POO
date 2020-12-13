public class Cliente {

    private String nome;
    private long CPF;

    public Cliente(String nome, long CPF) {

        this.setNome(nome);
        this.setCPF(CPF);

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

}
