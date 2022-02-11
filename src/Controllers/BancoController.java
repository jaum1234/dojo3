package Controllers;

import Entidades.*;
import Entidades.Conta.Conta;
import Entidades.Conta.ContaCorrente;
import Entidades.Conta.ContaPoupanca;
import Entidades.Conta.ContaSalario;
import Security.Auth;

import java.util.Random;

public class BancoController
{
    private Banco banco;
    private Cliente authUser;

    public BancoController()
    {
        this.banco = new Banco();
    }

    public void login(int numeroConta, String senha) throws Exception
    {
        if (!banco.hasConta(numeroConta)) {
            throw new Exception("Número de conta incorreto.");
        }

        Conta conta = banco.findConta(numeroConta);
        Cliente cliente = conta.cliente();
        String senhaCliente = cliente.senha();

        if (!senhaCliente.equals(senha)) {
            throw new Exception("Senha incorreta");
        }

        Auth.user = cliente;
    }

    public void logout()
    {
        Auth.user = null;
    }

    public void abrirConta(Cliente cliente, int tipoConta) throws Exception
    {
        if (!banco.hasExactCliente(cliente.cpf()) && (banco.hasExactCliente(cliente.email()) || banco.hasExactCliente(cliente.telefone()))) {
            throw new Exception("Cliente já foi cadastrado. Dados digitados ja foram utilizados.");
        }

        int numeroConta;

        if (cliente.hasContas()) {
            numeroConta = cliente.contas().get(0).numeroConta();
        } else {
            numeroConta = new Random().nextInt(10000);
        }

        Conta conta = tipoConta == 1 ? new ContaCorrente() : new ContaPoupanca();
        conta.setNumeroConta(numeroConta);

        cliente.addConta(conta);
        conta.addCliente(cliente);

        banco.contas().add(conta);
        banco.clientes().add(cliente);

        System.out.println("NOVA CONTA CRIADA: ");
        System.out.println("Numero da conta: " + conta.numeroConta());
        System.out.println("Senha: " + cliente.senha());
    }

    public void abrirContaSalario(int numeroConta, int tipoConta) throws Exception
    {
        if (!banco.hasExactConta(numeroConta, tipoConta)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta conta = banco.findExactConta(numeroConta, tipoConta);
        Cliente cliente = conta.cliente();

        Conta contaSalario = new ContaSalario();
        contaSalario.addCliente(cliente);
        contaSalario.setNumeroConta(contaSalario.numeroConta());

        cliente.addContaSalario(contaSalario, conta);
    }

    public void realizarDeposito(int numeroConta, float valor, int tipoConta) throws Exception
    {
        if (!banco.hasExactConta(numeroConta, tipoConta)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta conta = banco.findExactConta(numeroConta, tipoConta);
        conta.depositar(valor);

        Extrato extrato = new Extrato(valor, "Depositor realizado com sucesso.", "Deposito", null, conta);
        conta.addExtrato(extrato);
    }

    public void realizarSaque(int numeroConta, float valor, int tipoConta, String senha) throws Exception
    {
        if (!banco.hasExactConta(numeroConta, tipoConta)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta conta = banco.findExactConta(numeroConta, tipoConta);
        Cliente cliente = conta.cliente();

        if (!cliente.senha().equals(senha)) {
            throw new Exception("Senha incorreta.");
        }

        conta.sacar(valor);

        Extrato extrato = new Extrato(valor, "Extrato realizado com sucesso", "Saque", conta, null);
        conta.addExtrato(extrato);
    }

    public void realizarTransferenciaViaAgencia(
            int numeroContaTransferidor,
            int tipoContaTransferidor,
            int numeroContaRecebedor,
            int tipoContaRecebedor,
            float valor,
            String senha
    ) throws Exception {
        if (!banco.hasExactConta(numeroContaTransferidor, tipoContaTransferidor) || !banco.hasExactConta(numeroContaRecebedor, tipoContaRecebedor)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta contaTransferidor = banco.findExactConta(numeroContaTransferidor, tipoContaTransferidor);
        Conta contaRecebedor = banco.findExactConta(numeroContaRecebedor, tipoContaRecebedor);

        Cliente clienteTransferidor = contaTransferidor.cliente();

        if (!clienteTransferidor.senha().equals(senha)) {
            throw new Exception("Senha incorreta");
        }

        contaTransferidor.transferirViaAgencia(contaRecebedor, valor);

        Extrato extrato = new Extrato(
            valor,
            "Transferencia realizada com sucesso.",
            "Transferencia via agencia.",
            contaTransferidor,
            contaRecebedor
        );

        contaTransferidor.addExtrato(extrato);
    }

    public void realizarTransferenciaViaPIX(
            int numeroContaTransferidor,
            int tipoContaTransferidor,
            int tipoChavePix,
            int tipoContaRecebedor,
            float valor,
            String senha
    ) throws Exception {

    }

    public void listarExtratos(int numeroConta, int tipoConta, String senha) throws Exception
    {
        if (!banco.hasExactConta(numeroConta, tipoConta)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta conta = banco.findExactConta(numeroConta, tipoConta);
        String clienteContaSenha = conta.cliente().senha();

        if (!senhaValida(clienteContaSenha, senha)) {
            throw new Exception("Senha incorreta.");
        }

        conta.listarExtratos();
    }

    public void exibirExtrato(int idExtrato, int numeroConta, int tipoConta) throws Exception
    {
        if (!banco.hasExactConta(numeroConta, tipoConta)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta conta = banco.findExactConta(numeroConta, tipoConta);

        if (!conta.hasExactExtrato(idExtrato)) {
            throw new Exception("Extrato nao encontrado");
        }

        Extrato extrato = conta.findExtrato(idExtrato);
        extrato.dadosFormatados();
    }

    private boolean senhaValida(String senha, String senhaASerVerificada)
    {
        return senha.equals(senhaASerVerificada);
    }

    public boolean clienteJaCadastrado(String cpf)
    {
        return banco.hasExactCliente(cpf);
    }

    public Cliente buscarCliente(String cpf)
    {
        return banco.findCliente(cpf);
    }
}
