package Security;

import Entidades.Cliente;

public class Auth
{
    public static Cliente user;

    public static boolean userIsAuthenticated()
    {
        return user != null;
    }
}
