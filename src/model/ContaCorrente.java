package model;

public class ContaCorrente extends Conta {
    public ContaCorrente(int numero, Cliente cliente, double saldoInicial) {
        super(numero, cliente, saldoInicial);
    }

    @Override
    public void depositar(double valor) {}

    @Override
    public boolean sacar(double valor) {
        return false;
    }
}

// Liskov Substitution (SOLID) - a classe pode substituir a classe base Conta sem quebrar o sistema.
