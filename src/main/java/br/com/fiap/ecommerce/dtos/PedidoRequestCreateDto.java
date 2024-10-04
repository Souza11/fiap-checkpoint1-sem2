package br.com.fiap.ecommerce.dtos;

import org.modelmapper.ModelMapper;
import br.com.fiap.ecommerce.model.Pedido;

public class PedidoRequestCreateDto {
    private String formaPagamento;
    private String status;

    private static final ModelMapper modelMapper = new ModelMapper();

    public Pedido toModel() {
        return modelMapper.map(this, Pedido.class);
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
