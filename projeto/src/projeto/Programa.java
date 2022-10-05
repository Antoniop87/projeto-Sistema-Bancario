package projeto;


import java.util.Scanner;

import persistencia.PersistenciaArquivo;



public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PersistenciaArquivo pa = new PersistenciaArquivo();
		Scanner sc = new Scanner(System.in);

		boolean sair = true;
		int opcao = 0;

		while (sair) {
			System.out.println("Escolha uma opção:\n1) Cadastrar um cliente;\n2) Opções da Conta;\n3) Remover Cliente;\n4)Listar Clientes;\n5) Sair");
			opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				String nome = "";
				String cpf = "";
				System.out.println("Insira o nome do cliente: ");
				nome = sc.next();
				System.out.println("Insira o CPF: ");
				cpf = sc.next();
				Cliente cli = new Cliente(cpf, nome);
				pa.cadastrarCliente(cli);
				break;
			case 2:
				System.out.println("Insira o CPF do cliente: ");
				String cpfConsulta = "";
				int segundaOpcao = 0;
				boolean segundoSair = true;
				cpfConsulta = sc.next();
				Cliente cliConsulta = pa.buscarClienteCPF(cpfConsulta);
				if (cliConsulta != null) {
					while (segundoSair) {
						System.out.println(
								"\n\n\n\nEscolha as opções para CONTA: \n1) Cadastrar uma conta;\n2) Remover uma conta;\n3) Listar contas;\n4) Fazer Deposito; \n5 Fazer saque;\n6)Consultar saldo;\n7)Sair;");
						segundaOpcao = sc.nextInt();
						switch (segundaOpcao) {
						case 1:
							String numeroConta = "";
							System.out.println("Insira o numero da conta: ");
							numeroConta = sc.next();
							Conta c1 = new Conta(numeroConta);
							cliConsulta.adicionarConta(c1);
							pa.atualizarCliente(cliConsulta);
							break;
						case 2:
							System.out.println("Conta que deseja remover: ");
							numeroConta = sc.next();
							Conta c2 = new Conta(numeroConta);
							cliConsulta.removerConta(c2);
							pa.atualizarCliente(cliConsulta);
							break;
						case 3:
							System.out.println(cliConsulta.getContas());
							break;
						case 4:
							System.out.println("Numero da conta: ");
							numeroConta = sc.next();
							Conta c3 = new Conta(numeroConta);
							System.out.println("Valor do Deposito: ");
							float valor = sc.nextFloat();
							if(cliConsulta.buscaConta(c3)!=null) {
								c3 = cliConsulta.buscaConta(c3);
								c3.realizarDeposito(valor);
								cliConsulta.atualizaConta(c3);
								pa.atualizarCliente(cliConsulta);
							}
							break;
						case 5:
							System.out.println("Numero da sua conta: ");
							numeroConta = sc.next();
							Conta c4 = new Conta(numeroConta);
							System.out.println("Valor do Saque: ");
							float quantia = sc.nextFloat();
							if(cliConsulta.buscaConta(c4)!=null) {
								c4 = cliConsulta.buscaConta(c4);
								c4.realizarSaque(quantia);
								cliConsulta.atualizaConta(c4);
								pa.atualizarCliente(cliConsulta);
							}
							break;
						case 6:
							System.out.println("Numero da sua Conta: ");
							numeroConta = sc.next();
							Conta c5 = new Conta(numeroConta);
							if(cliConsulta.buscaConta(c5)!=null) {
								c5 = cliConsulta.buscaConta(c5);
								System.out.println(c5.saldo());
							}
							break;
						case 7:
							segundoSair = false;
							System.out.println("\n\n\n");
							break;
						default:
							sair = false;
							break;
						}
					}
				} else
					System.err.println("Cliente não encontrado!");
				break;
			case 3:
				System.out.println("Insira seu CPF: ");
				cpfConsulta = sc.next();
				Cliente cliConsultaa = pa.buscarClienteCPF(cpfConsulta);
				if(cliConsultaa != null) {
					pa.RemoverCliente(cliConsultaa);
				}
				break;
			case 4:
				pa.buscarCliente();
				break;
			case 5: 
				System.out.println("Sistema finalizado");
				sair = false;
			default:
				break;
			}
		}
	}

}
