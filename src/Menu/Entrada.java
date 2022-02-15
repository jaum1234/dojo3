package Menu;

public class Entrada extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("------------------");
        System.out.println("BANCO JJR\n");

        System.out.println("[1] Fazer login");
        System.out.println("[2] Ainda nao possuo uma conta");

        System.out.println("\n[3] Sair");
        System.out.println("------------------");

        int opcao = selecionarOpcao(1, 3);

        switch (opcao) {
            case 1:
                login();
            case 2:
                AberturaConta.render();
            case 3:
                System.exit(0);
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
            authController.login(numeroConta, senha);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Entrada.render();
        }

        Home.render();
    }


}
