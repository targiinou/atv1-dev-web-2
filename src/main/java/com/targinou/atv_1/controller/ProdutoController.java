package com.targinou.atv_1.controller;

import com.targinou.atv_1.model.ProdutoEntity;
import com.targinou.atv_1.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoEntity> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> getProdutoById(@PathVariable Long id) {
        return produtoService.getProdutoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProdutoEntity createProduto(@RequestBody ProdutoEntity produto) {
        return produtoService.createProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> updateProduto(
            @PathVariable Long id, @RequestBody ProdutoEntity produtoDetails) {
        return ResponseEntity.ok(produtoService.updateProduto(id, produtoDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/deleteLogic/{id}")
    public ResponseEntity<Void> deleteLogicProduto(@PathVariable Long id) {
        produtoService.deleteLogicProduto(id);
        return ResponseEntity.noContent().build();
    }
}
