package SA;
import java.util.*;
public class TestandoCodigo {

	static ArrayList<String> setores = new ArrayList<>();
	static ArrayList<String> maquinas = new ArrayList<>();
	static ArrayList<Integer> pecas = new ArrayList<>();
	
	static final int minProducao = 5;
	static final int numMaquinas = 6;
	static final int numSetores = 3;
	
	public static void main(String[] args) {
		boolean exibirMenu = true;
		Scanner entrada = new Scanner(System.in);
		
		do {
			exibirMenu();
			int opcaoMenu = entrada.nextInt();
			
			switch (opcaoMenu) {
			case 1:
				System.out.println("---- Cadastrar ---");
				cadastrar(entrada);
				break;
			case 2:
				System.out.println("---- Emitir Relatorio ---");
				exibirProducaoSemanal();
				break;
			case 3:
				System.out.println("---- Emitir relatorio setores melhor performance ---");
				exibirSetorMelhorPerformance();
				break;
			case 4:
				System.out.println("---- Emitir relatorio setores abaixo da meta ---");
				exibirMaquinasAbaixoDaMeta();
				break;
			case 5:
				exibirMenu = false;
				System.out.println("Finalizando programa");
				break;
			default:
				break;
			}
			
		} while (exibirMenu);
		
	}
	
	public static void cadastrar(Scanner entrada) {
		if(setores.size() >= numSetores) {
			System.out.println("Limite de setores cadastrados foi atingido.");
			return;
		}
		
		String nomeDoSetor = cadastrarSetor(entrada);
		cadastrarMaquinasPecas(entrada, nomeDoSetor);
		
		System.out.println("Cadastro efetuado com sucesso!");
	}

	private static String cadastrarSetor(Scanner entrada) {
		System.out.println("Digite o setor: ");
		String setor = entrada.next();
		setores.add(setor);
		return setor;
	}
	
	
	public static void cadastrarMaquinasPecas(Scanner entrada, String nomeSetor) {
		System.out.println("Cadastrar maquinas para o setor" + nomeSetor);
		System.out.println("Digite o nome da máquina: ");
		String maquina = entrada.next();
		maquinas.add(maquina);
		
		
		System.out.println("Digite a quantidade de pecas produzidas para a maquina: " + maquina);
		int numeroPecas = entrada.nextInt();
		pecas.add(numeroPecas);
	}
	
	
	public static void exibirProducaoSemanal() {
		System.out.println("--------PRODUÇÃO SEMANAL---------");
		for(int i = 0; i < setores.size(); i++) {
			System.out.println("|                            |");
			System.out.println("Setor " + setores.get(i));
			System.out.println("Maquina : " + maquinas.get(i));
			System.out.println("Producao pecas : " + pecas.get(i));
			System.out.println("|                            |");
		}
	}
	
	public static void exibirSetorMelhorPerformance() {
		String melhorSetor = setores.get(0);
		int maiorNumeroDePecasProduzidas = pecas.get(0);
		
		System.out.println("--------Setor Melhor performance---------");
		System.out.println("|                            |");

		for(int i = 0; i < setores.size(); i++) {
			String nomeSetor = setores.get(i);
			int producaoSetor = pecas.get(i);
			
			if(producaoSetor > maiorNumeroDePecasProduzidas) {
				melhorSetor = nomeSetor;
				maiorNumeroDePecasProduzidas = producaoSetor;
			}
			
		}
		System.out.println("|                            |");
		
		System.out.print("Melhor setor é o :" + melhorSetor);
	}
	
	public static void exibirMaquinasAbaixoDaMeta() {
		
		System.out.println("--------Maquinas abaixo da Meta : "+ minProducao + "---------");
		System.out.println("|                            |");

		for(int i = 0; i < setores.size(); i++) {
			int producaoSetor = pecas.get(i);
			
			if(producaoSetor < minProducao) {
				System.out.println("Setor " + setores.get(i) + " | Máquina: " + maquinas.get(i) + " | Peça: " + producaoSetor);
				System.out.println("META : " + minProducao + "| REALIZADO : " + producaoSetor);

			}
			
			System.out.println("|                            |");
		}
	}
	
	public static void producao() {
		System.out.println("--------PRODUÇÃO SEMANAL---------");
		for(int i = 0; i< setores.size(); i++) {
			System.out.println("Setor " + setores.get(i) + " | Máquina: " + maquinas.get(i) + " | Peça: " + pecas.get(i));
		}
	}

	public static void exibirMenu() {
		System.out.println("-------- MENU ---------");
		System.out.println("1) Cadastrar Setor,Máquina e quantidade de pecas");
		System.out.println("2) Emitir relatorio de producao");
		System.out.println("3) Exibir setor com melhor performance");
		System.out.println("4) Exibir as maquinas que estao produzindo abaixo da meta");
		System.out.println("5) Sair");
		System.out.println("-------- ---- ---------");

	}
	
}