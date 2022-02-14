package Entidades.PIX;

import Entidades.Cliente;
import Entidades.Conta.Conta;

import java.util.Random;

abstract public class PIX
{

    protected Cliente cliente;
    protected int tipo;

    public PIX(Cliente cliente)
    {
        this.cliente = cliente;
    }

    abstract public String valor();

    public int tipo()
    {
        return this.tipo;
    }

    public static String gerarChaveAleatoria() {
        Random gerador = new Random();

        String alfanum = "0123456789abcdefghijklm0123456789nopqrstuvwxyz0123456789";

        int keySize = 22;
        int posicaoCaractere;

        char[] chaveCaracteres = new char[keySize];


        //Gerando uma chave alfanumerica aleatoria
        for (int i = 0; i < keySize; i++) {
            posicaoCaractere = gerador.nextInt(alfanum.length());
            chaveCaracteres[i] = alfanum.charAt(posicaoCaractere);
        }

        String chave = String.valueOf(chaveCaracteres);

        return chave;

    }
}
