package Menu;


public class Transferencia extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("De qual conta será debitado? ");
        printTiposConta();
        int tipoContaTransferidor = selecionarOpcao(1, 3);
        scanner.nextLine();

        System.out.println("Numero da do recebedor: ");
        int numeroContaRecebedor = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Qual tipo de conta do creditado? ");
        printTiposConta();
        int tipoContaRecebedor = selecionarOpcao(1, 3);

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

    private static void viaPix()
    {

    }

}
