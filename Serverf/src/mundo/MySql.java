/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author admin
 */
public class MySql {
// Atributos
    //private static Connection conn; 

    private Connection connection;
    private ResultSet rst;
    private Statement statement;

    // Constantes  
    private final String driver = "com.mysql.jdbc.Driver";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/chat";

    // Constructor
    public MySql() {
    }

    public Connection connect() {
        this.connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e) {
            System.out.println("Mysql() :: " + e.getMessage());
        }
        return this.connection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    /* Métodos de comportamiento CRUD */
    /**
     * Utilizada para INSERT, UPDATE, o DELETE.
     *
     * @param nick
     * @param ip
     * @return reg Número de registros afectados.
     * @throws java.sql.SQLException
     */
    public int updateIp(String nick, String ip) throws SQLException {
        this.statement = connect().createStatement();
        String sql = "UPDATE users SET ip = '" + ip + "' WHERE nick ='" + nick + "'";
        return statement.executeUpdate(sql);
    }

    public int updateEstado(String nick, String estado) throws SQLException {
        this.statement = connect().createStatement();
        String sql = "UPDATE users SET estado = " + estado + " WHERE nick ='" + nick + "'";
        return statement.executeUpdate(sql);
    }

    /**
     * Retorna el resultado de la consulta ejecutada.
     *
     * @param nick
     * @return rst Resultado de la consulta
     * @throws java.sql.SQLException
     */
    public String selectUsers(String nick) throws SQLException {
        this.statement = connect().createStatement();
        String sql = "SELECT nick FROM  users  WHERE nick !='" + nick + "'";
        this.rst = statement.executeQuery(sql);
        String usersSend = "";
        while (rst.next()) {
            usersSend += rst.getString("nick") + "*";
        }
        return usersSend;
    }

    public void insertUser(String nick, String ip) throws SQLException {
        this.statement = connect().createStatement();
        String sql = "INSERT INTO users ( nick, ip, estado ) VALUES ('" + nick + "','" + ip + "',TRUE)";
        statement.executeUpdate(sql);
    }

    public boolean verificarUser(String nick) throws SQLException {
        this.statement = connect().createStatement();
        String sql = "SELECT * FROM  users  WHERE nick ='" + nick + "'";
        this.rst = statement.executeQuery(sql);
        return rst.next() != false;
    }

    public void saveMsg(String nickSend, String nickreceived, String msg) throws SQLException {
        this.statement = connect().createStatement();
        String idUser = "SELECT Id_User FROM users WHERE nick=" + "'" + nickreceived + "'";
        this.rst = statement.executeQuery(idUser);
        String id = null;
        while (rst.next()) {
            id = rst.getString("Id_User");

        }
        String sql = "INSERT INTO msg ( nickSend, nickreceived, msg, Id_User ) "
                + "VALUES ('" + nickSend + "','" + nickreceived + "','" + msg + "', " + Integer.parseInt(id) + ")";
        statement.executeUpdate(sql);
    }

    public void showMsg(String nick) throws SQLException {
        this.statement = connect().createStatement();
        String id = "SELECT Id_User FROM users WHERE nick ='" + nick + "'";
        this.rst = statement.executeQuery(id);
        while (rst.next()) {
            id = rst.getString("Id_User");

        }
        String sql = "SELECT msg, nickSend FROM msg WHERE Id_User=" + id;

    }

}
