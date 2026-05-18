package br.com.desafioextra.dao;


import br.com.desafioextra.database.Conexao;
import br.com.desafioextra.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    //insere client na database
    public void salvar(Cliente cliente){
        Connection conexao = null;
        PreparedStatement ps = null;
        try{
            conexao = Conexao.getConexao();
            String sql = "insert into clientes (nome, cep, cidade, estado) values (?, ?, ?, ?)";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2,cliente.getCep());
            ps.setString(3, cliente.getCidade());
            ps.setString(4, cliente.getEstado());
            ps.executeUpdate();

            System.out.println("Cliente salvo com sucesso!");


        }catch (SQLException e){
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
        } finally {
            try{
                if (ps != null){
                    ps.close();
                }

                if (conexao != null){
                    conexao.close();
                }
            }catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    //retorna uma lista de todos os clientes da database
    public List<Cliente> listar(){
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try{
            conexao = Conexao.getConexao();
            String sql = "select * from clientes";
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCep(rs.getString("cep"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                clientes.add(cliente);
            }

        }catch (SQLException e){
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        } finally {
            try{

                if(rs != null){
                    rs.close();
                }

                if (ps != null){
                    ps.close();
                }

                if (conexao != null){
                    conexao.close();
                }
            }catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return clientes;
    }

        //atualiza um cliente da database utilizando o id_cliente como referencia;
        public void atualizar(Cliente cliente){
            Connection conexao = null;
            PreparedStatement ps = null;

            try{
                conexao = Conexao.getConexao();
                String sql = "update clientes set nome = ?, cep = ?, cidade = ?, estado = ? where id_cliente = ?";
                ps = conexao.prepareStatement(sql);
                ps.setString(1,cliente.getNome());
                ps.setString(2,cliente.getCep());
                ps.setString(3,cliente.getCidade());
                ps.setString(4,cliente.getEstado());
                ps.setInt(5,cliente.getId());
                ps.executeUpdate();

                System.out.println("Cliente atualizado com sucesso!");

            }catch (SQLException e){
                System.out.println("Erro ao atualizar: " + e.getMessage());
            }finally {
                try{
                    if (ps != null){
                        ps.close();
                    }

                    if (conexao != null){
                        conexao.close();
                    }
                }catch (SQLException e){
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }

    public void deletar(int id){
        Connection conexao = null;
        PreparedStatement ps = null;

        try{
            conexao = Conexao.getConexao();
            String sql = "delete from clientes where id_cliente = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();

            System.out.println("Cliente deletado com sucesso!");

        }catch (SQLException e){
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
        }finally {
            try{
                if (ps != null){
                    ps.close();
                }

                if (conexao != null){
                    conexao.close();
                }
            }catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

}
