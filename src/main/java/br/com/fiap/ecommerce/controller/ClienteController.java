package br.com.fiap.ecommerce.controller;

import java.util.List;

import br.com.fiap.ecommerce.dtos.ClienteRequestUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.ecommerce.dtos.ClienteRequestCreateDto;
import br.com.fiap.ecommerce.dtos.ClienteResponseDto;
import br.com.fiap.ecommerce.model.Cliente;
import br.com.fiap.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> list() {
        List<ClienteResponseDto> dtos = clienteService.list()
                .stream()
                .map(e -> new ClienteResponseDto().toDto(e))
                .toList();

        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> create(@RequestBody ClienteRequestCreateDto dto) {
        return ResponseEntity
                .status(201)
                .body(new ClienteResponseDto().toDto(clienteService.save(dto.toModel())));
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(clienteService.findById(id)
                        .map(cliente -> new ClienteResponseDto().toDto(cliente))
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (!clienteService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }
        clienteService.delete(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponseDto> update(
            @PathVariable Long id,
            @RequestBody ClienteRequestUpdateDto dto) {
        if (!clienteService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }

        return ResponseEntity.ok()
                .body(new ClienteResponseDto().toDto(clienteService.save(dto.toModel(id))));
    }

}

