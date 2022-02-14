package Controllers;

import Entidades.*;
import Entidades.Conta.Conta;
import Entidades.Conta.ContaPoupanca;
import Entidades.Conta.ContaSalario;
import Globals.Auth;
import Globals.Tempo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class BancoController
{
    private Banco banco;
    //private Cliente authUser;

    public BancoController()
    {
        this.banco = new Banco();
    }

    //public void login(int numeroConta, String senha) throws Exception
    //{
    //    if (!banco.hasConta(numeroConta)) {
    //        throw new Exception("Número de conta incorreto.");
    //    }
    //
    //    Conta conta = banco.findConta(numeroConta);
    //    Cliente cliente = conta.cliente();
    //    String senhaCliente = cliente.senha();
    //
    //    if (!senhaCliente.equals(senha)) {
    //        throw new Exception("Senha incorreta");
    //    }
    //
    //    Auth.user = cliente;
    //}

    //public void logout()
    //{
    //    Auth.user = null;
    //}

    public void abrirConta(Cliente cliente, int tipoConta) throws Exception
    {
        if (!banco.hasExactCliente(cliente.cpf()) && (banco.hasExactCliente(cliente.email()) || banco.hasExactCliente(cliente.telefone()))) {
            throw new Exception("CLIENTE JÁ CADASTRADO. DADOS DIGITADOS JÁ FORAM UTILIZADOS.");
        }

        int numeroConta;

        if (cliente.hasContas()) {
            numeroConta = cliente.contas().get(0).numeroConta();
        } else {
            numeroConta = new Random().nextInt(10000);
        }

        Conta conta = GetContaFactory.getConta(tipoConta);
        conta.setNumeroConta(numeroConta);

        cliente.addConta(conta);
        conta.addCliente(cliente);

        banco.contas().add(conta);
        banco.clientes().add(cliente);

        System.out.println("------------------");
        System.out.println("NOVA CONTA CRIADA: ");
        System.out.println("Numero da conta: " + conta.numeroConta());
        System.out.println("Senha: " + cliente.senha());
        System.out.println("------------------");
    }

    public void abrirContaSalario(int tipoConta) throws Exception
    {
        Conta tipo = GetContaFactory.getConta(tipoConta);
        Conta conta = Auth.user.conta(tipo);

        Conta contaSalario = new ContaSalario();
        contaSalario.addCliente(Auth.user);
        contaSalario.setNumeroConta(contaSalario.numeroConta());

        conta.transferir(contaSalario, conta.saldo());

        Auth.user.addContaSalario(contaSalario, conta);
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

    public void realizarSaque(float valor, int tipoConta) throws Exception
    {
        Conta tipo = GetContaFactory.getConta(tipoConta);
        Conta conta = Auth.user.conta(tipo);

        conta.sacar(valor);

        Extrato extrato = new Extrato(valor, "Extrato realizado com sucesso", "Saque", conta, null);
        conta.addExtrato(extrato);
    }

    public void realizarTransferenciaViaAgencia(
            int tipoContaTransferidor,
            int numeroContaRecebedor,
            int tipoContaRecebedor,
            float valor
    ) throws Exception {
        if (!banco.hasExactConta(numeroContaRecebedor, tipoContaRecebedor)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta tipo = GetContaFactory.getConta(tipoContaTransferidor);
        Conta contaTransferidor = Auth.user.conta(tipo);

        Conta contaRecebedor = banco.findExactConta(numeroContaRecebedor, tipoContaRecebedor);

        contaTransferidor.transferir(contaRecebedor, valor);

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
            int tipoContaTransferidor,
            String chave,
            int tipoContaRecebedor,
            float valor
    ) throws Exception {

        if (!banco.hasExactCliente(chave)) {
            throw new Exception("Cliente nao encontrado.");
        }

        Cliente cliente = banco.findClienteByChavePIX(chave);

        System.out.println(cliente.nome());

        Conta contaTransferidor = GetContaFactory.getConta(tipoContaTransferidor);
        Conta contaRecebedor = cliente.conta(GetContaFactory.getConta(tipoContaRecebedor));

        Auth.user.conta(contaTransferidor).transferir(contaRecebedor, valor);

        Extrato extrato = new Extrato(
                valor,
                "Transferencia realizado com sucesso",
                "Transferencia via PIX",
                null,
                contaRecebedor
        );

        Auth.user.conta(contaTransferidor).addExtrato(extrato);
        System.out.println("TRANSFERECIA REALIZADA COM SUCESSO.");

    }

    public void listarExtratos(int tipoConta) throws Exception
    {
        Conta tipo = GetContaFactory.getConta(tipoConta);
        Conta conta = Auth.user.conta(tipo);

        conta.listarExtratos();
    }

    public void exibirExtrato(int idExtrato, int tipoConta) throws Exception
    {
        Conta tipo = GetContaFactory.getConta(tipoConta);
        Conta conta = Auth.user.conta(tipo);

        if (!conta.hasExactExtrato(idExtrato)) {
            throw new Exception("Extrato nao encontrado");
        }

        Extrato extrato = conta.findExtrato(idExtrato);
        extrato.dadosFormatados();
    }

    public void realizarRendimento(int meses) throws Exception
    {
        Conta contaPoupanca = Auth.user.conta(new ContaPoupanca());

        for (int i = 1; i <= meses; i++) {
            contaPoupanca.renderSaldo();
        }
    }

    public void realizarDepositoSalario(int meses) throws Exception
    {
        Conta contaSalario = Auth.user.conta(new ContaSalario());

        for (int i = 1; i <= meses; i++) {
           contaSalario.depositarSalario();
        }

    }

    public boolean clienteJaCadastrado(String cpf)
    {
        return banco.hasExactCliente(cpf);
    }

    public Cliente buscarCliente(String cpf)
    {
        return banco.findClienteByCPF(cpf);
    }
}
