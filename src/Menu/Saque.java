package Menu;

public class Saque extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Insira o numero da sua conta: ");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Selecione o tipo da conta a ser sacada: ");
        System.out.println("[1] Conta corrente");
        System.out.println("[2] Conta poupanca");

        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Qual será o valor do saque? ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Insira sua senha: ");
        String senha = scanner.nextLine();

        try {
            bancoController.realizarSaque(numeroConta, valor, tipoConta, senha);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }
}
