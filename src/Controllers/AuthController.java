package Controllers;

import Entidades.Banco;
import Entidades.Cliente;
import Entidades.Conta.Conta;
import Globals.Auth;

public class AuthController
{
    //public static Cliente user;
    private static Banco banco = new Banco();

    public void login(int numeroConta, String senha) throws Exception
    {
        if (!banco.hasConta(numeroConta)) {
            throw new Exception("Número de conta incorreto.");
        }

        Conta conta = banco.findConta(numeroConta);
        Cliente cliente = conta.cliente();
        String senhaCliente = cliente.senha();

        if (!senhaCliente.equals(senha)) {
            throw new Exception("Senha incorreta");
        }

        Auth.user = cliente;
    }

    public void logout()
    {
        Auth.user = null;
    }

    //public static boolean userIsAuthenticated()
    //{
    //    return Auth.user != null;
    //}
}
