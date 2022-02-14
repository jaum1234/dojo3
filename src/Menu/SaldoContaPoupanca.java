package Menu;

import Entidades.Cliente;
import Entidades.Conta.ContaPoupanca;
import Globals.Auth;

public class SaldoContaPoupanca extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("SALDO DA CONTA POUPANÇA");

        float saldo = Auth.user.hasConta(new ContaPoupanca()) ? Auth.user.conta(new ContaPoupanca()).saldo() : 0;
        System.out.println("R$ " + saldo);

        Home.render();

        //String sair = scanner.nextLine();
        //
        //if (sair.equals(null)) {
        //    Home.render();
        //}

    }
}
