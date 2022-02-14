package Menu;

public class Saque extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Selecione o tipo da conta a ser sacada: ");
        System.out.println("[1] Conta corrente");
        System.out.println("[2] Conta poupanca");

        int tipoConta = selecionarOpcao(1, 2);
        scanner.nextLine();

        System.out.println("Qual será o valor do saque? ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        try {
            bancoController.realizarSaque(valor, tipoConta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }
}
