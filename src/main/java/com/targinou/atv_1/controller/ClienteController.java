package com.targinou.atv_1.controller;

import com.targinou.atv_1.model.ClienteEntity;
import com.targinou.atv_1.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteEntity> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteEntity createCliente(@RequestBody ClienteEntity cliente) {
        return clienteService.createCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> updateCliente(
            @PathVariable Long id, @RequestBody ClienteEntity clienteDetails) {
        return ResponseEntity.ok(clienteService.updateCliente(id, clienteDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/deleteLogic/{id}")
    public ResponseEntity<Void> deleteLogicCliente(@PathVariable Long id) {
        clienteService.deleteLogicCliente(id);
        return ResponseEntity.noContent().build();
    }
}
