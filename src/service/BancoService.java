package service;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class BancoService implements Banco {

    private List<Cliente> clientes;
    private List<Conta> contas;

    public BancoService() {
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
    }

    @Override
    public void cadastrarCliente(String nome, String cpf) {
        // Implementar
    }

    @Override
    public void cadastrarConta(Conta conta) {
        // Implementar
    }

    @Override
    public void depositar(int numeroConta, double valor) {
        // Implementar
    }

    @Override
    public void sacar(int numeroConta, double valor) {
        // Implementar
    }

    @Override
    public void transferir(int contaOrigem, int contaDestino, double valor) {
        // Implementar
    }

    @Override
    public void aplicarRendimento() {
        // Implementar
    }

    @Override
    public double consultarSaldo(int numeroConta) {
        // Implementar
        return 0.0;
    }

    @Override
    public List<Conta> listarContas() {
        // Implementar
        return contas;
    }

    @Override
    public void gerarRelatorio() {
        // Implementar
    }
}