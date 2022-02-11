package Menu;

public class AberturaContaSalario extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Número da sua conta: ");
        int numeroConta = scanner.nextInt();

        System.out.println("Qual conta deseja transformar em conta salário");
        System.out.println("[1] Conta Corrente");
        System.out.println("[2] Conta Poupança");
        int tipoConta = scanner.nextInt();

        try {
            bancoController.abrirContaSalario(numeroConta, tipoConta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }
}
