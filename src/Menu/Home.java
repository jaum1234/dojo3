package Menu;

import Security.Auth;

public class Home extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Bem vindo de volta " + Auth.user.nome() + "!\n");
        System.out.println("[1] Abrir nova conta");
        System.out.println("[2] Abrir conta salário");
        System.out.println("[3] Realizar depósito");
        System.out.println("[4] Realizar saque");
        System.out.println("[5] Realizar transferencia");
        System.out.println("[6] Visualizar extratos");
        System.out.println("\n[7] Logout");

        System.out.println("\nMODO DESENVOLVEDOR");
        System.out.println("[8] Popular banco");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                AberturaConta.render();
            case 2:
                AberturaContaSalario.render();
            case 3:
                Deposito.render();
            case 4:
                Saque.render();
            case 5:
                Transferencia.render();
            case 6:
                Extratos.render();
            case 7:
                bancoController.logout();
                Login.render();
        }

    }
}
