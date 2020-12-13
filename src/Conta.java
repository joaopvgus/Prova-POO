public class Conta {

    private int numero;
    private Banco banco;
    private Cliente cliente;
    private double saldo;

    public Conta(int numero, Banco banco, Cliente cliente) {

        this.numero = numero;
        this.setBanco(banco);
        this.setCliente(cliente);
        this.setSaldo(0);

    }

    public int getNumero() {
        return numero;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
