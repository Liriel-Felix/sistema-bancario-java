package model;

public class ContaPoupanca extends Conta {
    private double taxaRendimento; 

    public ContaPoupanca(int numero, Cliente cliente, double saldoInicial, double taxaRendimento) {
        super(numero, cliente, saldoInicial);
        this.taxaRendimento = taxaRendimento;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    @Override
    public void depositar(double valor) {}

    @Override
    public boolean sacar(double valor) {
        return false;
    }
}

// Liskov Substitution (SOLID) - a classe pode substituir a classe base Conta sem quebrar o sistema.