package Entidades.PIX;

import Entidades.Cliente;
import Entidades.Conta.Conta;

public class ChaveEmail extends PIX
{
    public ChaveEmail(Cliente cliente)
    {
        super(cliente);
    }

    public String valor()
    {
        return this.cliente.email();
    }
}
