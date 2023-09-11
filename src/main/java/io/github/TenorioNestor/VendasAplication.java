package io.github.TenorioNestor;


import io.github.TenorioNestor.domain.entity.Cliente;
import io.github.TenorioNestor.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class VendasAplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            clientes.salvar(new Cliente(1, "Nestor Tenorio"));
            clientes.salvar(new Cliente(2,"Tayze Tenorio"));
            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(VendasAplication.class, args);
    }
}
