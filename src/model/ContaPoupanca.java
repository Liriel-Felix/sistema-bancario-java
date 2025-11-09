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
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }
}