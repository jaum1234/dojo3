package Factories;

import Entidades.Conta.Conta;
import Entidades.Conta.ContaCorrente;
import Entidades.Conta.ContaPoupanca;
import Entidades.Conta.ContaSalario;

public class GetContaFactory
{
    public static Conta getConta(int tipoConta) throws Exception
    {
        switch (tipoConta) {
            case 1:
                return new ContaCorrente();
            case 2:
                return new ContaPoupanca();
            case 3:
                return new ContaSalario();
            default:
                throw new Exception("O TIPO DE CONTA PASSADO NAO EXISTE.");
        }
    }
}
