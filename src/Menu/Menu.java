package Menu;

import Controllers.AuthController;
import Controllers.BancoController;

import java.util.Scanner;

abstract public class Menu
{
    protected static Scanner scanner = new Scanner(System.in);
    protected static BancoController bancoController = new BancoController();
    protected static AuthController authController = new AuthController();

    public static void call() throws Exception
    {
        Entrada.render();
    }

    protected static int selecionarOpcao(int min, int max)
    {
        int opcao;

        while (true) {
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao >= min && opcao <= max) {
                break;
            }

            System.out.println("Opçao inválida. Tente novamente: ");
        }

        return opcao;
    }
}
