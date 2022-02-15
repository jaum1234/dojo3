package Factories;

import Entidades.PIX.*;

public class GetPIXFactory
{
    public static ChavePIX getPIX(int tipoChave) throws Exception
    {
        switch (tipoChave) {
            case 1:
                return new ChaveCPF();
            case 2:
                return new ChaveEmail();
            case 3:
                return new ChaveTelefone();
            case 4:
                return new ChaveAleatoria();
            default:
                throw new Exception("O TIPO DE CHAVE PIX PASSADO NAO EXISTE.");
        }
    }

}
