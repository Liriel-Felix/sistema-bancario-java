import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import service.*;

public class Main {
    public static void main(String[] args) {
        Banco banco = new BancoService();
        
        banco.cadastrarCliente("Ana Silva", "11111111111");
        banco.cadastrarCliente("Bruno Costa", "22222222222");
        System.out.println();

        Conta conta1 = new ContaCorrente(1001, new Cliente("Ana Silva", "11111111111"), 500.0);
        Conta conta2 = new ContaPoupanca(1002, new Cliente("Bruno Costa", "22222222222"), 300.0, 0.02);
        banco.cadastrarConta(conta1);
        banco.cadastrarConta(conta2);
        System.out.println();

        banco.depositar(1001, 200.0);
        banco.sacar(1001, 100.0);
        banco.transferir(1001, 1002, 150.0);
        banco.aplicarRendimento();
        System.out.println();

        banco.consultarSaldo(1001);
        banco.consultarSaldo(1002);
        System.out.println();

        banco.listarContas();
        System.out.println();

        banco.gerarRelatorio();
        System.out.println();
    }
}
