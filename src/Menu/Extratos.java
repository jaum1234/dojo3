package Menu;

import Entidades.Conta.Conta;

public class Extratos extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("EXTRATOS\n");

        System.out.println("De qual conta deseja visualizar o extrato? ");
        printTiposConta();

        int tipoConta = selecionarOpcao(1, 3);

        try {
            bancoController.listarExtratos(tipoConta);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        System.out.println("Pressione enter para continuar");
        System.in.read();

        System.out.println("Deseja visualizar um extrato?");
        System.out.println("[1] Sim");
        System.out.println("[2] Nao");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                extrato(tipoConta);
            case 2:
                Home.render();
            default:
                Home.render();
        }

    }

    public static void extrato(int tipoConta) throws Exception
    {
        System.out.println("Id do extrato");
        int id = scanner.nextInt();

        try {
            bancoController.exibirExtrato(id, tipoConta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }
}
