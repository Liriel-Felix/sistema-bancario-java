package model;

public class ContaCorrente extends Conta {
    public ContaCorrente(int numero, Cliente cliente, double saldoInicial) {
        super(numero, cliente, saldoInicial);
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