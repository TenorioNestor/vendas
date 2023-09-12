package io.github.TenorioNestor.domain.repositorio;

import io.github.TenorioNestor.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {
    private static String INSERT = "insert into cliente (name) values (?)";
    private static String RETURN_ALL = "SELECT * FROM CLIENTE";
    private static String UPDATE = "UPDATE CLIENTE SET name= ? WHERE ID = ?";
    private static String DELETE = "DELETE from CLIENTE WHERE ID= ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, new Object[]{cliente.getName()});
        return cliente;
    }
    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getName(),cliente.getId()});
        return cliente;
    }
    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }
    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }
    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(RETURN_ALL.concat(" WHERE name LIKE ?"), new Object[]{"%"+ nome + "%"}, obterClienteMapper());
    }


    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(RETURN_ALL, obterClienteMapper());
    }

    private static RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("name");

                return new Cliente(id, nome);
            }

        };
    }
}