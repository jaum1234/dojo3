package Factory;

import Entidades.Cliente;

import java.util.ArrayList;
import java.util.Random;

public class ClienteFactory
{
    public static Random gerador = new Random();

    public static Cliente definition()
    {
        String cpf = gerarCPF();
        String nome = gerarNome();

        return new Cliente(cpf, nome, "123", "email", "telefone", "senha");
    }

    private static String gerarCPF()
    {
        String random = "0123456789";

        int keySize = 11;
        int posicaoCaractere;

        char[] chaveCaracteres = new char[keySize];

        for (int i = 0; i < keySize; i++) {
            posicaoCaractere = gerador.nextInt(random.length());
            chaveCaracteres[i] = random.charAt(posicaoCaractere);
        }

        return String.valueOf(chaveCaracteres);
    }

    private static String gerarNome()
    {
        ArrayList<String> nomes = new ArrayList<>();
        nomes.add("Joao");
        nomes.add("Lucas");
        nomes.add("Henrique");
        nomes.add("Emerson");
        nomes.add("Nelson");
        nomes.add("Joel");

        int nome = gerador.nextInt(nomes.size());
        return nomes.get(nome);
    }

}
