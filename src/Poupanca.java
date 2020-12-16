public class Poupanca extends Conta {

    private double juros;

    public Poupanca(int numero, Banco banco, Cliente cliente, double juros) {
        super(numero, banco, cliente);
        this.setJuros(juros);
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }
    
    public void renderJuros(){

        double juros = (this.getJuros() / 100) + 1;
        this.setSaldo(this.getSaldo() * juros);

    }

}
