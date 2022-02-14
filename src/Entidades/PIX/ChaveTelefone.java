package Entidades.PIX;

import Entidades.Cliente;
import Entidades.Conta.Conta;

public class ChaveTelefone extends PIX
{
    public ChaveTelefone(Cliente cliente)
    {
        super(cliente);
    }

    public String valor()
    {
        return this.cliente.telefone();
    }
}
