package br.com.desafioextra.database;

import br.com.desafioextra.config.Env;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {


    public static Connection getConexao() throws SQLException {
        String host = Env.get("DB_HOST","localhost");
        String port = Env.get("DB_PORT","3306");
        String name = Env.get("DB_NAME","projeto_crud");
        String user = Env.get("DB_USER","root");
        String password = Env.get("DB_PASSWORD","");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + name;

        return DriverManager.getConnection(url,user,password);

    }
}
