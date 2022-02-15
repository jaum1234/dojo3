package Menu;

import Entidades.Cliente;
import Globals.Auth;

public class AberturaConta extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("------------------");
        System.out.println("Qual conta deseja abrir? ");
        System.out.println("[1] Conta corrente");
        System.out.println("[2] Conta poupanca");
        System.out.println("------------------");

        int tipoConta = selecionarOpcao(1, 2);

        if (Auth.check()) {
            try {
                bancoController.abrirConta(Auth.user, tipoConta);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Home.render();
            return;
        }

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

            System.out.println("Data de nascimento (dd-mm-yyyy): ");
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

        System.out.println("Pressione enter para continuar");
        System.in.read();

        if (Auth.check()) {
            Home.render();
        }

        Entrada.render();

    }

}
