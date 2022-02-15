package Controllers;

import Entidades.*;
import Entidades.Conta.Conta;
import Entidades.Conta.ContaPoupanca;
import Entidades.Conta.ContaSalario;
import Entidades.Extrato.ExtratoBoleto;
import Entidades.Extrato.ExtratoTransferencia;
import Entidades.PIX.ChavePIX;
import Entidades.Extrato.Extrato;
import Factories.GetContaFactory;
import Factories.GetPIXFactory;
import Globals.Auth;
import Globals.Tempo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BancoController
{
    private Banco banco;

    public BancoController()
    {
        this.banco = new Banco();
    }

    public void abrirConta(Cliente cliente, int tipoConta) throws Exception
    {
        if (!banco.hasExactClienteWithCPF(cliente.cpf()) && (banco.hasExactClienteWithEmail(cliente.email()) || banco.hasExactClienteWithTelefone(cliente.telefone()))) {
            throw new Exception("CLIENTE JÁ CADASTRADO. DADOS DIGITADOS JÁ FORAM UTILIZADOS.");
        }

        String regex = "^[0-3][0-9]-[0-3][0-9]-(?:[0-9][0-9])?[0-9][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cliente.dataNascimento());

        if (!matcher.matches()) {
            throw new Exception("DATA DE NASCIMENTO NO FORMATO INVÁLIDO");
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

        banco.addConta(conta);
        banco.addCliente(cliente);

        System.out.println("------------------");
        System.out.println("NOVA CONTA CRIADA: ");
        System.out.println("Numero da conta: " + conta.numeroConta());
        System.out.println("Senha: " + cliente.senha());
        System.out.println("------------------");
    }

    public void abrirContaSalario(int tipoContaASubstituir) throws Exception
    {

        Conta tipo = GetContaFactory.getConta(tipoContaASubstituir);

        if (!Auth.user.hasConta(tipo)) {
            throw new Exception("Cliente com essa conta nao encontrado.");
        }

        Conta conta = Auth.user.conta(tipo);

        Conta contaSalario = GetContaFactory.getConta(3);

        Auth.user.addContaSalario(contaSalario, conta);
        banco.contas().remove(conta);
        banco.contas().add(contaSalario);
    }

    public void realizarDeposito(int numeroConta, float valor, int tipoConta) throws Exception
    {
        if (!banco.hasExactConta(numeroConta, tipoConta)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta conta = banco.findExactConta(numeroConta, tipoConta);
        conta.depositar(valor);

        Extrato extrato = new ExtratoTransferencia(null , conta, valor, "Depositor realizado com sucesso.", "Deposito");
        conta.addExtrato(extrato);

        System.out.println("------------------");
        System.out.println("DEPOSITO NO VALOR DE R$ " + valor + " REALIZADO!");
        System.out.println("------------------");
    }

    public void realizarSaque(float valor, int tipoConta) throws Exception
    {
        Conta tipo = GetContaFactory.getConta(tipoConta);
        Conta conta = Auth.user.conta(tipo);

        conta.sacar(valor);

        Extrato extrato = new ExtratoTransferencia(conta, null, valor, "Extrato realizado com sucesso", "Saque");
        conta.addExtrato(extrato);
    }

    public void realizarTransferenciaViaAgencia(
            int tipoContaTransferidor,
            int numeroContaRecebedor,
            int tipoContaRecebedor,
            float valor
    ) throws Exception {
        if (!banco.hasExactConta(numeroContaRecebedor, tipoContaRecebedor)) {
            throw new Exception("Conta do recebedor nao encontrada.");
        }

        Conta tipo = GetContaFactory.getConta(tipoContaTransferidor);

        if (!Auth.user.hasConta(tipo)) {
            throw new Exception("Conta do transferidor nao encontrada.");
        }

        Conta contaTransferidor = Auth.user.conta(tipo);
        Conta contaRecebedor = banco.findExactConta(numeroContaRecebedor, tipoContaRecebedor);

        contaTransferidor.transferir(contaRecebedor, valor);

        Extrato extrato = new ExtratoTransferencia(
            contaTransferidor,
            contaRecebedor,
            valor,
            "Transferencia realizada com sucesso.",
            "Transferencia via agencia."
        );

        contaTransferidor.addExtrato(extrato);
        contaRecebedor.addExtrato(extrato);
    }

    public void realizarTransferenciaViaPIX(
            int tipoContaTransferidor,
            int tipoChave,
            String chave,
            int tipoContaRecebedor,
            float valor
    ) throws Exception {

        if (!banco.hasExactClienteWithChavePIX(tipoChave, chave)) {
            throw new Exception("Nenhum cliente com essa chave PIX encontrado");
        }

        if (!Auth.user.hasConta(GetContaFactory.getConta(tipoContaTransferidor))) {
            throw new Exception("Conta do transferidor nao encontrado.");
        }

        Cliente cliente = banco.findClienteByChavePIX(tipoChave, chave);
        Conta contaTransferidor = Auth.user.conta(GetContaFactory.getConta(tipoContaTransferidor));
        Conta contaRecebedor = cliente.conta(GetContaFactory.getConta(tipoContaRecebedor));

        contaTransferidor.transferir(contaRecebedor, valor);

        Extrato extrato = new ExtratoTransferencia(
                contaTransferidor,
                contaRecebedor,
                valor,
                "Transferencia via PIX realizada com sucesso",
                "PIX"
        );

        contaTransferidor.addExtrato(extrato);
        contaRecebedor.addExtrato(extrato);
        System.out.println("TRANSFERECIA PIX REALIZADA COM SUCESSO.");

    }

    public void listarExtratos(int tipoConta) throws Exception
    {
        Conta tipo = GetContaFactory.getConta(tipoConta);

        if (!Auth.user.hasConta(tipo)) {
            throw new Exception("Conta nao encontrada");
        }

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

    public void realizarRendimento(LocalDate novoTempo) throws Exception
    {
        if (!Auth.user.hasConta(GetContaFactory.getConta(2))) {
            return;
        }

        Conta contaPoupanca = Auth.user.conta(GetContaFactory.getConta(2));
        int diff = Period.between(contaPoupanca.data(), novoTempo).getMonths();

        if (diff < 1) {
            return;
        }
        for (int i = 1; i <= diff; i++) {
            contaPoupanca.renderSaldo();
        }
    }

    public void vincularChavePix(int tipoChave) throws Exception
    {
        ChavePIX chave = GetPIXFactory.getPIX(tipoChave);

        Auth.user.addChavePIX(chave);
    }

    public void realizarDepositoSalario(LocalDate novoTempo) throws Exception
    {
        if (!Auth.user.hasConta(GetContaFactory.getConta(3))) {
            return;
        }

        Conta contaSalario = Auth.user.conta(new ContaSalario());

        int diff = Period.between(contaSalario.data(), novoTempo).getMonths();

        if (diff < 1) {
            return;
        }

        for (int i = 1; i <= diff; i++) {
           contaSalario.depositarSalario();
        }

    }

    public void gerarBoleto(String codigoBoleto, float valor, String dataVencimento) throws Exception
    {
        if (codigoBoleto.length() < 48) {
            throw new Exception("Código do boleto deve ter pelo menos 48 dígitos.");
        }

        if (valor < 1) {
            throw new Exception("Valor inválido.");
        }

        String regex = "^[0-3][0-9]-[0-3][0-9]-(?:[0-9][0-9])?[0-9][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dataVencimento);

        if (!matcher.matches()) {
            throw new Exception("Data inválida.");
        }

        Boleto boleto = new Boleto(codigoBoleto, valor, dataVencimento);
        banco.addBoleto(boleto);
    }

    public void pagarBoleto(String codigoBoleto, int tipoConta) throws Exception
    {
        if (!banco.hasExactBoleto(codigoBoleto)) {
            throw new Exception("BOLETO NAO REGISTRADO");
        }

        Boleto boleto = banco.findBoleto(codigoBoleto);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dataVencimentoBoleto = LocalDate.parse(boleto.vencimento(), formatter);

        int diff = Period.between(dataVencimentoBoleto, Tempo.hoje()).getDays();
        float valorDaMulta = 0;
        float valorComMulta = 0;

        if (diff >= 1) {
            for (int i = 1; i <= diff; i++) {
                valorComMulta = boleto.aplicarMulta();
            }
        }

        valorDaMulta = valorComMulta - boleto.valor();

        Conta conta = Auth.user.conta(GetContaFactory.getConta(tipoConta));
        conta.sacar(boleto.valor());

        Extrato extrato = new ExtratoBoleto(codigoBoleto, valorDaMulta, boleto.valor(), "Boleto pago com sucesso", "Pagamento de boleto");
        conta.addExtrato(extrato);
    }

    public boolean clienteJaCadastrado(String cpf)
    {
        return banco.hasExactClienteWithCPF(cpf);
    }

    public Cliente buscarCliente(String cpf)
    {
        return banco.findClienteByCPF(cpf);
    }
}
