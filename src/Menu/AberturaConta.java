package Menu;

import Entidades.Cliente;
import Security.Auth;

public class AberturaConta extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Qual conta deseja abrir? ");
        System.out.println("[1] Conta corrente");
        System.out.println("[2] Conta poupanca");

        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Insira seus dados: ");

        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        String nome;
        String dataNascimento;
        String email;
        String telefone;
        String senha;
        Cliente cliente;

        if (!bancoController.clienteJaCadastrado(cpf)) {
            System.out.println("Nome: ");
            nome = scanner.nextLine();

            System.out.println("Data de nascimento: ");
            dataNascimento = scanner.nextLine();

            System.out.println("Email: ");
            email = scanner.nextLine();

            System.out.println("Telefone: ");
            telefone = scanner.nextLine();

            System.out.println("Senha: ");
            senha = scanner.nextLine();

            cliente = new Cliente(cpf, nome, dataNascimento, email, telefone, senha);
        } else {
            cliente = bancoController.buscarCliente(cpf);
        }

        try {
            bancoController.abrirConta(cliente, tipoConta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (Auth.userIsAuthenticated()) {
            Home.render();
        }

        Login.render();

    }
}
