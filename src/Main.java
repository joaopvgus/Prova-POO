import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Conta> contas = new ArrayList<Conta>();
    static ArrayList<Poupanca> poupancas = new ArrayList<Poupanca>();
    static Scanner input = new Scanner(System.in);

    static int cadastrados = 0;

    public static int intInput(int a, int b) {

        Scanner input = new Scanner(System.in);

        try {
            int number = input.nextInt();
            if (number < a || number > b) {
                System.out.println("Valor inválido");
                number = intInput(a, b);
                return number;
            }
            return number;
        } catch (Exception e) {
            System.out.println("Valor inválido");
            int number = intInput(a, b);
            return number;
        }

    }

    public static long longInput() {

        Scanner input = new Scanner(System.in);

        try {
            long number = input.nextLong();
            return number;
        } catch (Exception e) {
            System.out.println("Valor inválido");
            long number = longInput();
            return number;
        }
    }

    public static String cpfInput(){

        Scanner input = new Scanner(System.in);

        try{
            String cpf = input.next();
            if (cpf.length() != 11){
                int cpfNum = Integer.parseInt(cpf);
                System.out.println("Valor inválido");
                cpf = cpfInput();
                return cpf;
            }
            return cpf;
        } catch (Exception e){
            System.out.println("Valor inválido");
            String cpf = cpfInput();
            return cpf;
        }

    }

    public static double doubleInput(){

        Scanner input = new Scanner(System.in);

        try {
            double number = input.nextDouble();
            return number;
        } catch (Exception e) {
            System.out.println("Valor inválido");
            double number = doubleInput();
            return number;
        }

    }

    public static void printMenu() {

        System.out.println("--------- MENU ---------");
        System.out.println("");
        System.out.println("1 - CADASTRAR");
        System.out.println("2 - REALIZAR DEPÓSITO");
        System.out.println("3 - RENDER JUROS");
        System.out.println("4 - CONSULTAR NÚMERO E NOME DA AGÊNCIA");
        System.out.println("5 - ALTERAR NÚMERO E NOME DA AGÊNCIA");
        System.out.println("0 - SAIR");

    }

    public static Banco cadastroBanco() {

        System.out.println("Digite o número da agência:");
        int numero = intInput(0, 10000);
        System.out.println("Digite o nome da agência:");
        String nomeDaAgencia = input.next();

        Banco banco = new Banco(numero, nomeDaAgencia);

        return banco;

    }

    public static Cliente cadastroCliente() {

        System.out.println("Digite o nome do cliente:");
        String nome = input.next();
        System.out.println("Digite o CPF do cliente:");
        String CPF = cpfInput();

        Cliente cliente = new Cliente(nome, CPF);

        System.out.println(cliente.getCPF());

        return cliente;

    }

    public static Conta cadastroContaCorrente() {

        System.out.println("--------- CADASTRO DE CONTA CORRENTE ---------");
        int numero = cadastrados;
        Banco banco = cadastroBanco();
        Cliente cliente = cadastroCliente();

        Conta conta = new Conta(numero, banco, cliente);

        cadastrados++;

        return conta;
    }

    public static Poupanca cadastroContaPoupanca() {

        System.out.println("--------- CADASTRO DE CONTA POUPANCA ---------");
        int numero = cadastrados;
        Banco banco = cadastroBanco();
        Cliente cliente = cadastroCliente();
        System.out.println("Digite qual será a taxa de juros(%): ");
        double juros = input.nextDouble();
        Poupanca poupanca = new Poupanca(numero, banco, cliente, juros);

        cadastrados++;

        return poupanca;
    }

    public static void cadastroConta() {

        if (cadastrados != 9) {

            System.out.println("--------- CADASTRO ---------");
            System.out.println("QUAL TIPO DE CONTA DESEJA CADASTRAR?");
            System.out.println("1 - CORRENTE");
            System.out.println("2 - POUPANÇA");

            int opcaoCadastro = input.nextInt();

            if (opcaoCadastro == 1) {
                contas.add(cadastroContaCorrente());
            } else {
                poupancas.add(cadastroContaPoupanca());
            }

        } else {
            System.out.println("Limite de cadastrados alcançado!");
        }

    }

    public static void deposito() {

        System.out.println("--------- DEPÓSITO ---------");
        System.out.println("Digite o CPF do cliente: ");
        String CPF = cpfInput();
        System.out.println("Digite o valor do depósito: ");
        double valorDeposito = doubleInput();

        boolean notFound = true;

        if (contas.size() != 0) {
            for (int i = contas.size(); i > 0; i--) {

                if (contas.get(i - 1).getCliente().getCPF().equals(CPF)) {
                    notFound = false;
                    Conta clone = contas.get(i - 1);
                    System.out.println(clone.getSaldo());
                    clone.setSaldo(clone.getSaldo() + valorDeposito);
                    System.out.println(clone.getSaldo());
                    contas.set(i - 1, clone);
                }
            }
        }

        if (poupancas.size() != 0) {
            for (int i = poupancas.size(); i > 0; i--) {
                if (poupancas.get(i - 1).getCliente().getCPF().equals(CPF)) {
                    notFound = false;
                    Poupanca clone = poupancas.get(i - 1);
                    System.out.println(clone.getSaldo());
                    clone.setSaldo(clone.getSaldo() + valorDeposito);
                    System.out.println(clone.getSaldo());
                    poupancas.set(i - 1, clone);
                }
            }
        }

        if (notFound) {
            System.out.println("Este CPF não está cadastrado!");
        } else {
            System.out.println("--------- DEPÓSITO REALIZADO COM SUCESSO ---------");
        }
    }

    public static void renderJuros() {

        System.out.println("--------- RENDER JUROS ---------");
        System.out.println("Digite o CPF do cliente: ");
        String CPF = cpfInput();
        boolean notFound = true;

        if (poupancas.size() != 0) {
            for (int i = poupancas.size(); i > 0; i--) {
                if (poupancas.get(i - 1).getCliente().getCPF().equals(CPF)) {
                    notFound = false;
                    Poupanca clone = poupancas.get(i - 1);
                    System.out.println(clone.getSaldo());
                    clone.renderJuros();
                    System.out.println(clone.getSaldo());
                    poupancas.set(i - 1, clone);
                }
            }
        }

        if (notFound) {
            System.out.println("CPF não cadastrado ou o cliente não tem conta poupança!");
        } else {
            System.out.println("--------- JUROS APLICADOS ---------");
        }

    }

    public static void consultaPorNumeroEAgencia() {

        System.out.println("--------- CONSULTA POR NÚMERO E NOME DA AGÊNCIA ---------");
        System.out.println("Digite número da agência: ");
        int numero = intInput(0, 10000);
        System.out.println("Digite o nome da agência: ");
        String nome = input.next();

        boolean notFound = false;

        if (contas.size() != 0) {
            for (int i = contas.size(); i > 0; i--) {
                if (contas.get(i - 1).getBanco().getNomeDaAgencia().equals(nome)
                        & contas.get(i - 1).getBanco().getNumeroDaAgencia() == numero) {
                    notFound = false;
                    System.out.print("Nome: ");
                    System.out.println(contas.get(i - 1).getCliente().getNome());
                    System.out.print("CPF: ");
                    System.out.println(contas.get(i - 1).getCliente().getCPF());
                }
            }

        }

        if (poupancas.size() != 0) {
            for (int i = poupancas.size(); i > 0; i--) {
                if (poupancas.get(i - 1).getBanco().getNomeDaAgencia() == nome
                        & poupancas.get(i - 1).getBanco().getNumeroDaAgencia() == numero) {
                    notFound = false;
                    System.out.print("Nome: ");
                    System.out.println(poupancas.get(i - 1).getCliente().getNome());
                    System.out.print("CPF: ");
                    System.out.println(poupancas.get(i - 1).getCliente().getCPF());

                }
            }
        }

        if (notFound) {
            System.out.println("Não há clientes cadastrados com esses dados!");
        }
    }

    public static void alterarNumeroENomeDaAgencia() {

        System.out.println("--------- MODIFICAR NÚMERO E NOME DA AGÊNCIA ---------");
        System.out.println("Digite o nome do cliente: ");
        String nome = input.next();
        System.out.println("Digite o novo número de agência: ");
        int numeroDaAgencia = intInput(0, 10000);
        System.out.println("Digite o nome da agência: ");
        String nomeDaAgencia = input.next();

        boolean notFound = false;

        if (contas.size() != 0) {
            for (int i = contas.size(); i > 0; i--) {
                if (contas.get(i - 1).getCliente().getNome().equals(nome)) {
                    notFound = false;
                    Conta cloneConta = contas.get(i - 1);
                    Banco cloneBanco = contas.get(i - 1).getBanco();
                    cloneBanco.setNomeDaAgencia(nomeDaAgencia);
                    cloneBanco.setNumeroDaAgencia(numeroDaAgencia);
                    cloneConta.setBanco(cloneBanco);
                    contas.set(i - 1, cloneConta);
                }
            }
        }

        if (poupancas.size() != 0) {
            for (int i = poupancas.size(); i > 0; i--) {
                if (poupancas.get(i - 1).getCliente().getNome().equals(nome)) {
                    notFound = false;
                    Poupanca clonePoupanca = poupancas.get(i - 1);
                    Banco cloneBanco = poupancas.get(i - 1).getBanco();
                    cloneBanco.setNomeDaAgencia(nomeDaAgencia);
                    cloneBanco.setNumeroDaAgencia(numeroDaAgencia);
                    clonePoupanca.setBanco(cloneBanco);
                    poupancas.set(i - 1, clonePoupanca);
                }
            }
        }

        if (notFound) {
            System.out.println("Não há clientes cadastrados com esses dados!");
        }

    }

    public static void main(String[] args) {

        int opcao = 0;

        do {

            printMenu();

            opcao = intInput(0, 5);

            if (opcao == 1) {
                cadastroConta();
            }

            else if (opcao == 2) {
                deposito();
            }

            else if (opcao == 3) {
                renderJuros();
            }

            else if (opcao == 4) {
                consultaPorNumeroEAgencia();
            }

            else if (opcao == 5) {
                alterarNumeroENomeDaAgencia();
            }

        } while (opcao != 0);

    }

}