package Entidades.PIX;

import Entidades.Cliente;

public class ChaveTelefone extends ChavePIX
{

    public ChaveTelefone()
    {
        this.tipo = 3;
    }

    public String valor()
    {
        return this.cliente.telefone();
    }

}
