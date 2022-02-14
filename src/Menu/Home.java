package Menu;

import Entidades.Conta.ContaCorrente;
import Entidades.Conta.ContaPoupanca;
import Entidades.Conta.ContaSalario;
import Globals.Auth;
import Globals.Tempo;

public class Home extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("------------------");
        System.out.println("Bem vindo de volta " + Auth.user.nome() + "!\n");
        System.out.println(Tempo.hoje());

        float saldoContaCorrente = Auth.user.hasConta(new ContaCorrente()) ? Auth.user.conta(new ContaCorrente()).saldo() : 0;
        float saldoContaPoupanca = Auth.user.hasConta(new ContaPoupanca()) ? Auth.user.conta(new ContaPoupanca()).saldo() : 0;
        float saldoContaSalario = Auth.user.hasConta(new ContaSalario()) ? Auth.user.conta(new ContaSalario()).saldo() : 0;
        System.out.println("SALDO DA CONTA CORRENTE: R$ " + saldoContaCorrente);
        System.out.println("SALDO DA CONTE POUPANÇA: R$ "+ saldoContaPoupanca);
        System.out.println("SALDO DA CONTA SALÁRIO: R$ " + saldoContaSalario);
        System.out.println("");

        System.out.println("[1] Abrir nova conta");
        System.out.println("[2] Abrir conta salário");
        System.out.println("[3] Realizar depósito");
        System.out.println("[4] Realizar saque");
        System.out.println("[5] Realizar transferencia");
        System.out.println("[6] Visualizar extratos");
        System.out.println("[7] Pagar boleto");
        System.out.println("\n[9] Logout");

        System.out.println("\nMODO DESENVOLVEDOR");
        System.out.println("[10] Manipular tempo");
        System.out.println("[11] Popular banco");
        System.out.println("------------------");

        int opcao = selecionarOpcao(1, 11);

        switch (opcao) {
            case 1:
                AberturaConta.render();
            case 2:
                AberturaContaSalario.render();
            case 3:
                Deposito.render();
            case 4:
                Saque.render();
            case 5:
                Transferencia.render();
            case 6:
                Extratos.render();
            case 7:
                PagamentoBoleto.render();
            case 8:
                SaldoContaPoupanca.render();
            case 9:
                authController.logout();
                Entrada.render();
            case 10:
                ManipulacaoTempo.render();
        }

    }
}
