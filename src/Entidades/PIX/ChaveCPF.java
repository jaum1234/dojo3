package Entidades.PIX;

import Entidades.Cliente;

public class ChaveCPF extends ChavePIX
{
    public ChaveCPF()
    {
        this.tipo = 1;
    }

    public String valor()
    {
        return this.cliente.cpf();
    }

}
