package Menu;

public class Deposito extends Menu
{
    public static void render()
    {
        System.out.println("Insira o numero da conta a receber o depósito: ");
        int numeroConta = scanner.nextInt();

        System.out.println("Selecione o tipo da conta: ");
        System.out.println("[1] Conta corrente");
        System.out.println("[2] Conta poupanca");

        int tipoConta = scanner.nextInt();

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
