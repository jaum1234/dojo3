package Menu;

public class PagamentoBoleto extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Conta a ser debitada: ");
        System.out.println("[1] Conta corrente");
        System.out.println("[2] Conta poupança");
        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Codigo de barras do boleto (48 digitos): ");
        String codigoBoleto = scanner.nextLine();

        try {
            bancoController.pagarBoleto(codigoBoleto, tipoConta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }
}
