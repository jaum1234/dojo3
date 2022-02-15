package Entidades.PIX;

import Entidades.Cliente;

public class ChaveEmail extends ChavePIX
{
    public ChaveEmail()
    {
        this.tipo = 2;
    }

    public String valor()
    {
        return this.cliente.email();
    }

}
