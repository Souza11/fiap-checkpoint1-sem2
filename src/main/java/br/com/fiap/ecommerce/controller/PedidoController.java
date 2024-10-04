package br.com.fiap.ecommerce.controller;

import java.util.List;

import br.com.fiap.ecommerce.dtos.PedidoRequestUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.ecommerce.dtos.PedidoRequestCreateDto;
import br.com.fiap.ecommerce.dtos.PedidoResponseDto;
import br.com.fiap.ecommerce.model.Pedido;
import br.com.fiap.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> list() {
        List<PedidoResponseDto> dtos = pedidoService.list()
                .stream()
                .map(e -> new PedidoResponseDto().toDto(e))
                .toList();

        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDto> create(@RequestBody PedidoRequestCreateDto dto) {
        return ResponseEntity
                .status(201)
                .body(new PedidoResponseDto().toDto(pedidoService.save(dto.toModel())));
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(pedidoService.findById(id)
                        .map(pedido -> new PedidoResponseDto().toDto(pedido))
                        .orElseThrow(() -> new RuntimeException("Pedido não encontrado")));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (!pedidoService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }
        pedidoService.delete(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<PedidoResponseDto> update(
            @PathVariable Long id,
            @RequestBody PedidoRequestUpdateDto dto) {
        if (!pedidoService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }

        return ResponseEntity.ok()
                .body(new PedidoResponseDto().toDto(pedidoService.save(dto.toModel(id))));
    }

}
