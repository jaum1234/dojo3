package Entidades.PIX;

import Entidades.Cliente;
import Entidades.Conta.Conta;

public class ChaveCPF extends PIX
{
    public ChaveCPF(Cliente cliente)
    {
        super(cliente);
    }

    public String valor()
    {
        return this.cliente.cpf();
    }
}
