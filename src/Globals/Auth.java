package Globals;

import Entidades.Cliente;

public class Auth
{
    public static Cliente user;

    public static boolean check()
    {
        return user != null;
    }

}
