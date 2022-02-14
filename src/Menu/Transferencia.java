package Menu;


public class Transferencia extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Tipo da transferencia: ");
        System.out.println("[1] Via Agencia");
        System.out.println("[2] Via PIX");

        int escolha = selecionarOpcao(1, 2);

        switch (escolha) {
            case 1:
                viaAgencia();
            case 2:
                viaPix();
        }
    }

    private static void viaPix()
    {
        System.out.println("Qual de suas contas sera debitada?");
        System.out.println("[1] Conta corrente");
        System.out.println("[2] Conta poupança");
        int tipoContaTransferidor = selecionarOpcao(1, 2);

        System.out.println("Qual chave será utilizado: ");
        System.out.println("[1] CPF");
        System.out.println("[2] Email");
        System.out.println("[3] Telefone");
        System.out.println("[4] Chave aleatoria");

        int tipoChave = selecionarOpcao(1, 4);
        scanner.nextLine();

        System.out.println("CHAVE: ");
        String chave = scanner.nextLine();

        System.out.println("Valor: ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Qual tipo de conta do creditado? ");
        System.out.println("[1] Conta corrente");
        System.out.println("[2] Conta poupança");
        int tipoContaRecebedor = selecionarOpcao(1, 2);

        try {
            bancoController.realizarTransferenciaViaPIX(tipoContaTransferidor, chave, tipoContaRecebedor, valor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viaAgencia() throws Exception
    {
        System.out.println("De qual conta será debitado? ");
        System.out.println("[1] Corrente ");
        System.out.println("[2] Poupança ");
        int tipoContaTransferidor = selecionarOpcao(1, 2);
        scanner.nextLine();

        System.out.println("Numero da do recebedor: ");
        int numeroContaRecebedor = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Qual tipo de conta do creditado? ");
        System.out.println("[1] Corrente ");
        System.out.println("[2] Poupança ");
        int tipoContaRecebedor = selecionarOpcao(1, 2);
        scanner.nextLine();

        System.out.println("Valor: ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        try {
            bancoController.realizarTransferenciaViaAgencia(
                    tipoContaTransferidor,
                    numeroContaRecebedor,
                    tipoContaRecebedor,
                    valor
                    );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }
}
