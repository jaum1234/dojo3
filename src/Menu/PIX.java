package Menu;

import Globals.Auth;

public class PIX extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("[1] Adicionar uma chave");
        System.out.println("[2] Fazer PIX");

        int opcao = selecionarOpcao(1, 2);

        switch (opcao) {
            case 1:
                adicinonarChave();
            case 2:
                fazerPix();
        }
    }

    public static void adicinonarChave() throws Exception
    {
        System.out.println("Qual chave deseja adicionar? ");
        System.out.println("[1] CPF: " + Auth.user.cpf());
        System.out.println("[2] Email: " + Auth.user.email());
        System.out.println("[3] Telefone: " + Auth.user.telefone());
        System.out.println("[4] Chave aleatoria: " + Auth.user.chaveAleatoria());

        int tipoChave = selecionarOpcao(1, 4);

        try {
            bancoController.vincularChavePix(tipoChave);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }

    public static void fazerPix() throws Exception
    {
        System.out.println("Qual de suas contas sera debitada?");
        printTiposConta();
        int tipoContaTransferidor = selecionarOpcao(1, 3);

        System.out.println("Qual chave será utilizado: ");
        System.out.println("[1] CPF");
        System.out.println("[2] Email");
        System.out.println("[3] Telefone");
        System.out.println("[4] Chave aleatoria");

        int tipoChave = selecionarOpcao(1, 4);

        System.out.println("CHAVE: ");
        String chave = scanner.nextLine();

        System.out.println("Valor: ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Qual tipo de conta do creditado? ");
        printTiposConta();
        int tipoContaRecebedor = selecionarOpcao(1, 3);

        try {
            bancoController.realizarTransferenciaViaPIX(tipoContaTransferidor, tipoChave, chave, tipoContaRecebedor, valor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Pressione enter para continuar");
        System.in.read();

        Home.render();
    }
}
