import br.com.desafioextra.dao.ClienteDAO;
import br.com.desafioextra.dao.PedidoDAO;
import br.com.desafioextra.database.Conexao;
import br.com.desafioextra.entities.Cliente;
import br.com.desafioextra.service.ViaCepResponse;
import br.com.desafioextra.service.ViaCepService;


import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option;

            while (true) {
                try {

                    menuPrincipal();
                    option = scanner.nextInt();
                    scanner.nextLine();


                    switch (option){
                        case 1:
                            menuClientes();
                            int optionClient = scanner.nextInt();
                            scanner.nextLine();
                            switch (optionClient){
                                case 1:
                                    cadastrarCliente(scanner);
                                    break;
                                case 2:

                                    break;
                                case 3:

                                    break;
                                case 4:

                                    break;
                                case 0:
                                    return;
                                default:
                                    System.out.println("Opção Invalida.");
                            }
                        break;


                        case 2:
                            menuPedidos();
                            int optionPedido = scanner.nextInt();
                            switch (optionPedido){
                                case 1:

                                    break;
                                case 2:

                                    break;
                                case 3:

                                    break;
                                case 4:

                                    break;
                                case 0:
                                    return;
                                default:
                                    System.out.println("Opção Invalida.");
                            }
                        break;
                        case 0:
                            System.out.println("Finalizando programa.");
                        return;
                        default:
                            System.out.println("Opção invalida.");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Utilize apenas números, tente novamente.");
                }
            }
    }

    static void menuPrincipal(){
        System.out.println("=== SISTEMA DE CLIENTES E PEDIDOS ===");
        System.out.println("1. Clientes");
        System.out.println("2. Pedidos");
        System.out.println("0. Sair");
        System.out.print("Selecione uma opção: ");
    }

    static void menuClientes(){
        System.out.println("=== CLIENTES ===");
        System.out.println("1. Cadastrar cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Atualizar cliente");
        System.out.println("4. Deletar cliente");
        System.out.println("0. Voltar.");
        System.out.print("Selecione uma opção: ");
    }


    static void menuPedidos(){
        System.out.println("=== Pedidos ===");
        System.out.println("1. Cadastrar pedido");
        System.out.println("2. Listar pedidos");
        System.out.println("3. Atualizar pedido");
        System.out.println("4. Deletar pedido");
        System.out.println("0. Voltar.");
        System.out.print("Selecione uma opção: ");
    }

    static void cadastrarCliente(Scanner scanner){
      try {
          System.out.println("=== CADASTRO DE CLIENTE ===");
          System.out.print("Nome: ");
          String nome = scanner.nextLine();
          if(nome == null || nome.isBlank() ){
              System.out.println("Nome não informado!");
              return;
          }
          System.out.print("CEP (*****-***): ");
          String cep = scanner.nextLine();
          if(cep == null || cep.isBlank()){
              System.out.println("CEP não informado!");
              return;
          }

          ViaCepResponse endereco = ViaCepService.buscarCep(cep);

          Cliente cliente = new Cliente(nome, endereco.getCep(), endereco.getLocalidade(), endereco.getUf());
          ClienteDAO dao = new ClienteDAO();
          dao.salvar(cliente);

      }catch (NullPointerException e){
          System.out.println("Consulta de CEP falhou!" + e.getMessage());
      }
    }

}
