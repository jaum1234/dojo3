package Menu;

public class Login extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("[1] Fazer login");
        System.out.println("[2] Ainda nao possuo uma conta");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                login();
            case 2:
                AberturaConta.render();
        }
    }

    public static void login() throws Exception
    {
        System.out.println("Número da conta");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Senha");
        String senha = scanner.nextLine();

        try {
            bancoController.login(numeroConta, senha);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Login.render();
        }

        Home.render();
    }


}
