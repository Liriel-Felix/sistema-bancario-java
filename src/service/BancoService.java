package service;

import model.*;
import java.util.ArrayList;
import java.util.Comparator;
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
        // Verifica se o cliente já existe pelo CPF
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                System.out.println("Cliente com CPF " + cpf + " já está cadastrado.");
                return;
            }
        }
        
        // Cria e adiciona o novo cliente
        Cliente novoCliente = new Cliente(nome, cpf);
        clientes.add(novoCliente);

        System.out.println("Cliente " + nome + " cadastrado com sucesso!");
    }

    @Override
    public void cadastrarConta(Conta conta) {
        // Verifica se o cliente da conta existe
        Cliente cliente = conta.getCliente();
        boolean clienteExiste = false;

        for (Cliente c : clientes) {
            if (c.getCpf().equals(cliente.getCpf())) {
                clienteExiste = true;
                break;
            }
        }

        if (!clienteExiste) {
            System.out.println("Cliente não cadastrado. Cadastre o cliente antes de criar a conta.");
            return;
        }

        // Verifica se já existe uma conta com o mesmo número
        for (Conta c : contas) {
            if (c.getNumero() == conta.getNumero()) {
                System.out.println("Já existe uma conta com o número " + conta.getNumero());
                return;
            }
        }

        // Adiciona a nova conta
        contas.add(conta);
        System.out.println("Conta número " + conta.getNumero() + " cadastrada com sucesso!");
    }

    @Override
    public void depositar(int numeroConta, double valor) {
        if (valor <= 0) {
            System.out.println("O valor do depósito deve ser positivo.");
            return;
        }

        // Busca a conta pelo número
        for (Conta conta : contas) {
            if (conta.getNumero() == numeroConta) {
                conta.depositar(valor);
                System.out.println("Depósito de R$ " + valor + " realizado na conta " + numeroConta);
                return;
            }
        }

        System.out.println("Conta número " + numeroConta + " não encontrada.");
    }

    @Override
    public void sacar(int numeroConta, double valor) {
        if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo.");
            return;
        }

        // Busca a conta pelo número
        for (Conta conta : contas) {
            if (conta.getNumero() == numeroConta) {
                boolean sucesso = conta.sacar(valor);

                if (sucesso) {
                    System.out.println("Saque de R$ " + valor + " realizado na conta " + numeroConta);
                } else {
                    System.out.println("Saldo insuficiente na conta " + numeroConta);
                }
                return;
            }
        }

        System.out.println("Conta número " + numeroConta + " não encontrada.");
    }

    @Override
    public void transferir(int contaOrigem, int contaDestino, double valor) {
        if (valor <= 0) {
            System.out.println("O valor da transferência deve ser positivo.");
            return;
        }

        Conta origem = null;
        Conta destino = null;

        // Localiza as contas
        for (Conta conta : contas) {
            if (conta.getNumero() == contaOrigem) {
                origem = conta;
            } else if (conta.getNumero() == contaDestino) {
                destino = conta;
            }
        }

        // Verificações básicas
        if (origem == null) {
            System.out.println("Conta de origem não encontrada.");
            return;
        }

        if (destino == null) {
            System.out.println("Conta de destino não encontrada.");
            return;
        }

        // Tenta realizar o saque na conta de origem
        if (origem.sacar(valor)) {
            destino.depositar(valor);
            System.out.println("Transferência de R$ " + valor + " realizada da conta " +
                contaOrigem + " para a conta " + contaDestino);
        } else {
            System.out.println("Saldo insuficiente na conta de origem.");
        }
    }

    @Override
    public void aplicarRendimento() {
        for (Conta conta : contas) {
            if (conta instanceof ContaPoupanca) {
                ContaPoupanca poupanca = (ContaPoupanca) conta;
                double saldoAtual = poupanca.getSaldo();
                double rendimento = saldoAtual * poupanca.getTaxaRendimento();

                poupanca.depositar(rendimento);
                System.out.println("Rendimento de R$ " + rendimento + " aplicado à conta " + poupanca.getNumero());
            }
        }
    }

    @Override
    public double consultarSaldo(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numeroConta) {
                System.out.println("Saldo da conta " + numeroConta + ": R$ " + conta.getSaldo());
                return conta.getSaldo();
            }
        }

        System.out.println("Conta número " + numeroConta + " não encontrada.");
        return 0.0;
    }

    @Override
    public List<Conta> listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            contas.sort(Comparator.comparingDouble(Conta::getSaldo).reversed());

            System.out.println("Lista de contas cadastradas (ordem decrescente de saldos):");
            for (Conta conta : contas) {
                System.out.println("Conta nº " + conta.getNumero() + 
                               " | Cliente: " + conta.getCliente().getNome() +
                               " | Saldo: R$ " + conta.getSaldo());
            }
        }
        return contas;
    }

    @Override
    public void gerarRelatorio() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta disponível para gerar relatório.");
            return;
        }

        System.out.println("===== RELATÓRIO DO BANCO =====");
        double saldoTotal = 0.0;
        double saldoPoupanca = 0.0;
        double saldoCorrente = 0.0;

        for (Conta conta : contas) {
            System.out.println("Conta nº " + conta.getNumero() + 
                           " | Cliente: " + conta.getCliente().getNome() +
                           " | Saldo: R$ " + conta.getSaldo());
            saldoTotal += conta.getSaldo();

            if (conta instanceof ContaPoupanca) {
                saldoPoupanca += conta.getSaldo();
            } else {
                saldoCorrente += conta.getSaldo();
            }
        }

        System.out.println("---------------------------------");
        System.out.println("Saldo total das contas Corrente: R$ " + saldoCorrente);
        System.out.println("Saldo total das contas Poupança: R$ " + saldoPoupanca);
        System.out.println("Total de contas: " + contas.size());
        System.out.println("Saldo total do banco: R$ " + saldoTotal);
        System.out.println("=================================\n");
    }
}