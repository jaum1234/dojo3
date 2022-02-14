package Entidades.PIX;

import Entidades.Cliente;
import Entidades.Conta.Conta;

public class ChaveAleatoria extends PIX
{
    public ChaveAleatoria(Cliente cliente)
    {
        super(cliente);
    }

    public String valor()
    {
        return gerarChaveAleatoria();
    }
}
