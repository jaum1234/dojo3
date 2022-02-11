package Menu;

import Entidades.PIX.*;

public class Transferencia extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Tipo da transferencia: ");
        System.out.println("[1] Via Agencia");
        System.out.println("[2] Via PIX");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                viaAgencia();
            case 2:
                viaPix();
        }
    }

    private static void viaPix()
    {
        System.out.println("Insira o numero de sua conta");

        System.out.println("Qual chave será utilizado: ");
        System.out.println("[1] CPF");
        System.out.println("[2] Email");
        System.out.println("[3] Telefone");
        System.out.println("[4] Chave aleatoria");

        int opcao = scanner.nextInt();
        scanner.nextLine();


        System.out.println("Valor: ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        try {
            //bancoController.realizarTransferenciaViaPIX(opcao, valor, senha);
        } catch (Exception e) {

        }
    }

    private static void viaAgencia() throws Exception
    {
        System.out.println("Numero da sua conta: ");
        int numeroContaTransferidor = scanner.nextInt();

        System.out.println("De qual conta será debitado? ");
        System.out.println("[1] Corrente ");
        System.out.println("[2] Poupança ");
        int tipoContaTransferidor = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Numero da do recebedor: ");
        int numeroContaRecebedor = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Qual tipo de conta do creditado? ");
        System.out.println("[1] Corrente ");
        System.out.println("[2] Poupança ");
        int tipoContaRecebedor = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Valor: ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Digite sua senha: ");
        String senha = scanner.nextLine();

        try {
            bancoController.realizarTransferenciaViaAgencia(
                    numeroContaTransferidor,
                    tipoContaTransferidor,
                    numeroContaRecebedor,
                    tipoContaRecebedor,
                    valor,
                    senha);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }
}
