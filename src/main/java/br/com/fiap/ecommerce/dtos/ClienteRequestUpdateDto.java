package br.com.fiap.ecommerce.dtos;

import org.modelmapper.ModelMapper;
import br.com.fiap.ecommerce.model.Cliente;

public class ClienteRequestUpdateDto {
    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private String cep;
    private String uf;

    private static final ModelMapper modelMapper = new ModelMapper();

    public Cliente toModel(Long id) {
        Cliente cliente = modelMapper.map(this, Cliente.class);
        cliente.setId(id); // Define o ID para garantir que estamos atualizando o cliente correto
        return cliente;
    }

    // Getters e Setters
    // ...
}

