package Menu;

public class Deposito extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Insira o numero da conta a receber o depósito: ");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();

        printTiposConta();

        int tipoConta = selecionarOpcao(1, 3);

        System.out.println("Qual será o valor do depósito? ");
        float valor = scanner.nextFloat();

        try {
            bancoController.realizarDeposito(numeroConta, valor, tipoConta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }
}
