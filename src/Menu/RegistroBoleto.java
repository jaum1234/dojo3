package Menu;

public class RegistroBoleto extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Codigo de barras do boleto (48 digitos): ");
        String codigoBoleto = scanner.nextLine();

        System.out.println("Valor a ser pago: ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Data de vencimento (dd-mm-yyyy): ");
        String dataVencimento = scanner.nextLine();

        try {
            bancoController.gerarBoleto(codigoBoleto, valor, dataVencimento);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }

}
