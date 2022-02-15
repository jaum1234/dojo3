package Entidades.PIX;

import Entidades.Cliente;
import Entidades.Conta.Conta;

import java.util.Random;

abstract public class ChavePIX
{

    protected Cliente cliente;
    protected int tipo;
    protected String valor;

    abstract public String valor();

    public int tipo()
    {
        return this.tipo;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }
}
