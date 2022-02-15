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
        System.out.println("DATA ATUAL: " + Tempo.hoje());
        System.out.println("");

        float saldoContaCorrente = Auth.user.hasConta(new ContaCorrente()) ? Auth.user.conta(new ContaCorrente()).saldo() : 0;
        float chequeEspecial = Auth.user.hasConta(new ContaCorrente()) ? Auth.user.conta(new ContaCorrente()).chequeEspecial() : 0;
        float saldoContaPoupanca = Auth.user.hasConta(new ContaPoupanca()) ? Auth.user.conta(new ContaPoupanca()).saldo() : 0;
        float saldoContaSalario = Auth.user.hasConta(new ContaSalario()) ? Auth.user.conta(new ContaSalario()).saldo() : 0;
        System.out.println("SALDO DA CONTA CORRENTE: R$ " + saldoContaCorrente);
        System.out.println("SALDO DA CONTE POUPANÇA: R$ "+ saldoContaPoupanca);
        System.out.println("SALDO DA CONTA SALÁRIO: R$ " + saldoContaSalario);
        System.out.println("\nCHEQUE ESPECIAL: R$" + chequeEspecial);
        System.out.println("");

        System.out.println("[1] Abrir nova conta");
        System.out.println("[2] Abrir conta salário");
        System.out.println("[3] Realizar depósito");
        System.out.println("[4] Realizar saque");
        System.out.println("[5] Realizar transferencia");
        System.out.println("[6] PIX");
        System.out.println("[7] Visualizar extratos");
        System.out.println("[8] Registrar boleto");
        System.out.println("[9] Pagamento boleto");
        System.out.println("\n[10] Logout");

        System.out.println("\nMODO DESENVOLVEDOR");
        System.out.println("[11] Manipular tempo");
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
                PIX.render();
            case 7:
                Extratos.render();
            case 8:
                RegistroBoleto.render();

            case 9:
                PagamentoBoleto.render();

            case 10:
                authController.logout();
                Entrada.render();
            case 11:
                ManipulacaoTempo.render();
        }

    }
}
