package Menu;

import Controllers.BancoController;
import Entidades.Banco;

import java.util.Scanner;

abstract public class Menu
{
    protected static Scanner scanner = new Scanner(System.in);
    protected static BancoController bancoController = new BancoController();

    public static void call() throws Exception
    {
        Login.render();
    }

}
