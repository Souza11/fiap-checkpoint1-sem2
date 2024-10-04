package br.com.fiap.ecommerce.dtos;

import org.modelmapper.ModelMapper;
import br.com.fiap.ecommerce.model.Pedido;

public class PedidoRequestUpdateDto {
    private String formaPagamento;
    private String status;

    private static final ModelMapper modelMapper = new ModelMapper();

    public Pedido toModel(Long id) {
        Pedido pedido = modelMapper.map(this, Pedido.class);
        pedido.setId(id); // Define o ID para garantir que estamos atualizando o pedido correto
        return pedido;
    }

    // Getters e Setters
    // ...
}

