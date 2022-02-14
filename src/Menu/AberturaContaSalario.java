package Menu;

public class AberturaContaSalario extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Qual conta deseja transformar em conta salário");
        System.out.println("[1] Conta Corrente");
        System.out.println("[2] Conta Poupança");
        int tipoConta = selecionarOpcao(1, 2);

        try {
            bancoController.abrirContaSalario(tipoConta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }
}
