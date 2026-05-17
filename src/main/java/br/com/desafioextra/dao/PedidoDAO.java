package br.com.desafioextra.dao;

import br.com.desafioextra.database.Conexao;
import br.com.desafioextra.entities.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void salvar(Pedido pedido){

        Connection conexao = null;
        PreparedStatement ps = null;
        try{
            conexao = Conexao.getConexao();
            String sql = "insert into pedidos (descricao, valor, cliente_id) values (?, ?, ?)";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, pedido.getDescricao());
            ps.setDouble(2,pedido.getValor());
            ps.setInt(3,pedido.getClienteId());
            ps.executeUpdate();

            System.out.println("Pedido salvo com sucesso!");


        }catch (SQLException e){
            System.out.println("Erro ao salvar: " + e.getMessage());
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

    public List<Pedido> listar(){
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<>();
        try{
            conexao = Conexao.getConexao();
            String sql = "select * from pedidos";
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setDescricao(rs.getString("descricao"));
                pedido.setValor(rs.getDouble("valor"));
                pedido.setClienteId(rs.getInt("cliente_id"));
                pedidos.add(pedido);
            }

        }catch (SQLException e){
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
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

        return pedidos;

    }

    public void atualizar(Pedido pedido){
        Connection conexao = null;
        PreparedStatement ps = null;

        try{
            conexao = Conexao.getConexao();
            String sql = "update pedidos set descricao = ?, valor = ? where id_pedido = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1,pedido.getDescricao());
            ps.setDouble(2,pedido.getValor());
            ps.setInt(3,pedido.getId());
            ps.executeUpdate();

            System.out.println("Pedido atualizado com sucesso!");

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
            String sql = "delete from pedidos where id_pedido = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();

            System.out.println("Pedido deletado com sucesso!");

        }catch (SQLException e){
            System.out.println("Erro ao deletar pedido: " + e.getMessage());
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
