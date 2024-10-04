package br.com.fiap.ecommerce.dtos;

import org.modelmapper.ModelMapper;
import br.com.fiap.ecommerce.model.Pedido;

public class PedidoResponseDto {
    private Long id;
    private String formaPagamento;
    private String status;

    private static final ModelMapper modelMapper = new ModelMapper();

    public PedidoResponseDto toDto(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResponseDto.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

