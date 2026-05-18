import br.com.desafioextra.dao.ClienteDAO;
import br.com.desafioextra.dao.PedidoDAO;
import br.com.desafioextra.database.Conexao;
import br.com.desafioextra.entities.Cliente;
import br.com.desafioextra.service.ViaCepResponse;
import br.com.desafioextra.service.ViaCepService;


import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
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
                                    listarClientes();
                                    break;
                                case 3:
                                    atualizarCliente(scanner);
                                    break;
                                case 4:
                                    deletarCliente(scanner);
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

          String cep;
          while (true) {
              System.out.print("CEP (*****-***): ");
              cep = scanner.nextLine();
              if (cep == null || cep.isBlank()) {
                  System.out.println("CEP não informado!");
                  continue;
              }
              if(cep.replaceAll("\\D","").length() != 8){
                  System.out.println("CEP deve ter 8 números, tente novamente!");
                  continue;
              }
              break;
          }

          ViaCepResponse endereco = ViaCepService.buscarCep(cep);

          Cliente cliente = new Cliente(nome, endereco.getCep(), endereco.getLocalidade(), endereco.getUf());
          ClienteDAO dao = new ClienteDAO();
          dao.salvar(cliente);

      }catch (NullPointerException e){
          System.out.println("Consulta de CEP falhou!" + e.getMessage());
      }
    }

    static void listarClientes(){
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clienteList = dao.listar();

        if(clienteList.isEmpty()){
            System.out.println("Não há clientes para listar");
            return;
        }

        System.out.println("=== LISTA DE CLIENTES ===");
        for (Cliente c : clienteList){
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() + " | CEP: " + c.getCep() +
                    " | Cidade: " + c.getCidade() + " | Estado: " + c.getEstado());
        }
    }

    static void atualizarCliente(Scanner scanner){
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clienteList = dao.listar();
        listarClientes();

        try{
            System.out.println("=== Atualizar Cliente ===");
            System.out.print("Digite o ID do cliente que você deseja atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            boolean encontrado = false;
            for(Cliente c : clienteList){
                if(id == c.getId()){
                    encontrado = true;

                    System.out.print("Digite o novo nome: ");
                    String nome = scanner.nextLine();
                    if(nome == null || nome.isBlank()){
                        System.out.println("Nome não informado!");
                        return;
                    }

                    String cep;
                    while (true) {
                        System.out.print("Digite o novo CEP (*****-***): ");
                        cep = scanner.nextLine();
                        if (cep == null || cep.isBlank()) {
                            System.out.println("CEP não informado!");
                            continue;
                        }
                        if(cep.replaceAll("\\D","").length() != 8){
                            System.out.println("CEP deve ter 8 números, tente novamente!");
                            continue;
                        }
                        break;
                    }

                    ViaCepResponse endereco = ViaCepService.buscarCep(cep);

                    c.setNome(nome);
                    c.setCep(endereco.getCep());
                    c.setCidade(endereco.getLocalidade());
                    c.setEstado(endereco.getUf());
                    dao.atualizar(c);
                    break;
                }
            }

            if(!encontrado){
                System.out.println("ID não encontrado no sistema.");
            }

        }catch (NullPointerException e){
            System.out.println("Consulta de CEP falhou: " + e.getMessage());
        }catch (InputMismatchException e){
            System.out.println("Utilize apenas números no ID.");
        }
    }

    public static void deletarCliente(Scanner scanner){
        ClienteDAO dao = new ClienteDAO();
        listarClientes();

        try{
            System.out.println("Digite o ID do cliente que deseja deletar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            dao.deletar(id);

        }catch (InputMismatchException e){
            System.out.println("Utilize apenas números no ID!" + e.getMessage());
        }






    }

    }




