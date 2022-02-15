package PopularSistema;

import Entidades.Banco;
import Entidades.Cliente;
import Entidades.Conta.Conta;
import Entidades.Conta.ContaCorrente;
import Entidades.Conta.ContaPoupanca;

import java.util.Random;

public class Popular
{
    public static void init() throws Exception
    {
        Banco banco = new Banco();

        Cliente cliente1 = new Cliente("12345678911", "joao", "04-09-2001", "email@dominio.com", "999999999", "123");
        Cliente cliente2 = new Cliente("98765432111", "Carlos", "05-02-1993", "outroemail@dominio.com", "988888888", "123");

        int numeroConta = new Random().nextInt(10000);
        Conta conta1 = new ContaCorrente();
        conta1.setNumeroConta(numeroConta);
        Conta conta2 = new ContaPoupanca();
        conta2.setNumeroConta(numeroConta);
        cliente1.addConta(conta1);
        cliente1.addConta(conta2);

        int outroNumeroConta = new Random().nextInt(10000);
        Conta conta3 = new ContaCorrente();
        conta3.setNumeroConta(outroNumeroConta);
        Conta conta4 = new ContaPoupanca();
        conta4.setNumeroConta(outroNumeroConta);
        cliente2.addConta(conta3);
        cliente2.addConta(conta4);

        banco.addCliente(cliente1);
        banco.addCliente(cliente2);

        banco.addConta(conta1);
        banco.addConta(conta2);
        banco.addConta(conta3);
        banco.addConta(conta4);
    }
}
