package service;

import model.*;
import java.util.List;

public interface Banco {
    void cadastrarCliente(String nome, String cpf);
    void cadastrarConta(Conta conta);
    void depositar(int numeroConta, double valor);
    void sacar(int numeroConta, double valor);
    void transferir(int contaOrigem, int contaDestino, double valor);
    void aplicarRendimento();
    double consultarSaldo(int numeroConta);
    List<Conta> listarContas();
    void gerarRelatorio();
}
