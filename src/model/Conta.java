package model;

public abstract class Conta {
    private int numero;
    private Cliente cliente;
    protected double saldo;

    public Conta(int numero, Cliente cliente, double saldoInicial) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldoInicial;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract void depositar(double valor);
    public abstract boolean sacar(double valor);
}

// Open/Closed (SOLID) - novos tipos de conta podem ser criados sem alterar a classe base, apenas herdando.