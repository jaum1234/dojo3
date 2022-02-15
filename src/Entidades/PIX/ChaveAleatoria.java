package Entidades.PIX;

import Entidades.Cliente;

public class ChaveAleatoria extends ChavePIX
{
    public ChaveAleatoria()
    {
        this.tipo = 4;
    }

    public String valor()
    {
        return this.cliente.chaveAleatoria();
    }

}
