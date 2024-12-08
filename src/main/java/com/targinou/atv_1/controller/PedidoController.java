package com.targinou.atv_1.controller;

import com.targinou.atv_1.model.PedidoEntity;
import com.targinou.atv_1.model.ProdutoEntity;
import com.targinou.atv_1.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoEntity> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PedidoEntity createPedido(@RequestBody PedidoEntity pedido) {
        return pedidoService.createPedido(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoEntity> updatePedido(
            @PathVariable Long id, @RequestBody PedidoEntity pedidoDetails) {
        return ResponseEntity.ok(pedidoService.updatePedido(id, pedidoDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/deleteLogic/{id}")
    public ResponseEntity<Void> deleteLogicPedido(@PathVariable Long id) {
        pedidoService.deleteLogicPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/addProduto")
    public ResponseEntity<PedidoEntity> adicionarProduto(
            @PathVariable Long id, @RequestBody ProdutoEntity produto) {
        return ResponseEntity.ok(pedidoService.adicionarProduto(id, produto));
    }

    @PatchMapping("/{id}/removeProduto")
    public ResponseEntity<PedidoEntity> removerProduto(
            @PathVariable Long id, @RequestBody ProdutoEntity produto) {
        return ResponseEntity.ok(pedidoService.removerProduto(id, produto));
    }
}
