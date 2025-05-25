package SA;
import java.util.*;
public class SAMetalurgica {

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
			String opcaoMenu = entrada.next();
			if (!opcaoMenu.equals("1") &&
					!opcaoMenu.equals("2") &&
					!opcaoMenu.equals("3") &&
					!opcaoMenu.equals("4") &&
					!opcaoMenu.equals("5")) {
				System.out.println("Favor digitar uma opção numérica entre 1 e 5.");
				System.out.println("                               ");
				continue;
			}
			int opcao = Integer.parseInt(opcaoMenu);
			
			switch (opcao) {
			case 1:
				System.out.println("-------- CADASTRAR --------");
				cadastrar(entrada);
				break;
			case 2:
				System.out.println("Emitir relatório: ");
				exibirProducaoSemanal();
				break;
			case 3:
				System.out.println("Emitir relatório de setores com melhor performance: ");
				exibirSetorMelhorPerformance();
				break;
			case 4:
				System.out.println("Emitir relatório de setores abaixo da meta: ");
				exibirMaquinasAbaixoDaMeta();
				break;
			case 5:
				exibirMenu = false;
				System.out.println("Finalizando programa...");
				System.out.println("                               ");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
				System.out.println("                               ");
				break;
			}
			
		} while (exibirMenu);
		
	}
	
	public static void cadastrar(Scanner entrada) {

		if(setores.size() >= numSetores) {
			System.out.println("Limite de setores cadastrados foi atingido.");
			System.out.println("                               ");
			return;
		}

		System.out.println("Digite o setor: ");
		String setor = entrada.next();
		
		if(setores.contains(setor)) {
			System.out.println("Setor já cadastrado.");
			System.out.println("                               ");
		}

		else {
			cadastrarMaquinasPecas(entrada, setor);
			setores.add(setor);
			System.out.println("Cadastro efetuado com sucesso!");
			System.out.println("                               ");
		}
		
	}
	
	public static void cadastrarMaquinasPecas(Scanner entrada, String nomeSetor) {
		System.out.println("Cadastrar máquinas para o setor " + nomeSetor + ": ");
		System.out.println("Digite o nome da máquina: ");
		String maquina = entrada.next();
		maquinas.add(maquina);
		
		int numeroPecas = 0;
		
		do {
		System.out.println("Digite a quantidade de peças produzidas para a máquina " + maquina + ": ");
		numeroPecas = entrada.nextInt();
		if(numeroPecas < 0 ) {
			System.out.println("Quantidade inválida. Tente novamente.");
		
		}
		else {
		pecas.add(numeroPecas);
		}
		} while (numeroPecas < 0);
	}
	
	
	public static void exibirProducaoSemanal() {
		System.out.println("-------- PRODUÇÃO SEMANAL --------");
		for(int i = 0; i < setores.size(); i++) {
			System.out.println("                               ");
			System.out.println("Setor: " + setores.get(i));
			System.out.println("Máquina: " + maquinas.get(i));
			System.out.println("Produção de peças: " + pecas.get(i));
			System.out.println("                               ");
		}
	}
	
	public static void exibirSetorMelhorPerformance() {
		String mensagemNenhumSetorPerformando = "Nenhum setor está performando.";
		if (setores.isEmpty()) {
			System.out.println(mensagemNenhumSetorPerformando);
			return;
		}
		
		String melhorSetor = "";
		boolean temAlgumSetorPerformando = false;
		int maiorNumeroDePecasProduzidas = 0;
		
		System.out.println("-------- SETOR COM MELHOR PERFORMANCE --------");
		System.out.println("                               ");

		for(int i = 0; i < setores.size(); i++) {
			String nomeSetor = setores.get(i);
			int producaoSetor = pecas.get(i);
			
			if(producaoSetor > maiorNumeroDePecasProduzidas) {
				melhorSetor = nomeSetor;
				maiorNumeroDePecasProduzidas = producaoSetor;
				temAlgumSetorPerformando = true;
			}
			
		}
		
		if(temAlgumSetorPerformando == false) {
			System.out.println(mensagemNenhumSetorPerformando);
			return;
		}
		
		System.out.println("Melhor setor é: " + melhorSetor + " | Número de peças produzidas: " + maiorNumeroDePecasProduzidas);
		System.out.println("                               ");
	}
	
	public static void exibirMaquinasAbaixoDaMeta() {
		
		int quantidadeMaquinasAbaixoDaMeta = 0;
		System.out.println("-------- MÁQUINAS ABAIXO DA META --------");
		System.out.println("                               ");

		for(int i = 0; i < setores.size(); i++) {
			int producaoSetor = pecas.get(i);
			
			if(producaoSetor < minProducao) {
				System.out.println("Setor " + setores.get(i) + " | Máquina: " + maquinas.get(i) + " | Peça: " + producaoSetor);
				System.out.println("META: " + minProducao + "| REALIZADO: " + producaoSetor);
				quantidadeMaquinasAbaixoDaMeta++;
			}
			
			System.out.println("                               ");
		}
		if (quantidadeMaquinasAbaixoDaMeta == 0) {
			System.out.println("Nenhum setor está abaixo da meta.");
		}
	}
	
	public static void producao() {
		System.out.println("-------- PRODUÇÃO SEMANAL --------");
		for(int i = 0; i< setores.size(); i++) {
			System.out.println("Setor: " + setores.get(i) + " | Máquina: " + maquinas.get(i) + " | Peça: " + pecas.get(i));
		}
	}

	public static void exibirMenu() {
		System.out.println("-------- MENU --------");
		System.out.println("1) Cadastrar setor, máquina e quantidade de peças: ");
		System.out.println("2) Emitir relatório de produção: ");
		System.out.println("3) Exibir setor com melhor performance: ");
		System.out.println("4) Exibir máquinas com produção abaixo da meta: ");
		System.out.println("5) Sair...");
		System.out.println("                               ");

	}
	
}

