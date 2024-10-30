package com.targinou.atv_1.service;

import com.targinou.atv_1.model.ProdutoEntity;
import com.targinou.atv_1.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoEntity> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoEntity> getProdutoById(Long id) {
        return produtoRepository.findById(id);
    }

    public ProdutoEntity createProduto(ProdutoEntity produto) {
        produto.setAtivo(true);
        return produtoRepository.save(produto);
    }

    public ProdutoEntity updateProduto(Long id, ProdutoEntity produtoDetails) {
        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNomeProduto(produtoDetails.getNomeProduto());
        produto.setMarca(produtoDetails.getMarca());
        produto.setDataFabricacao(produtoDetails.getDataFabricacao());
        produto.setDataValidade(produtoDetails.getDataValidade());
        produto.setCategoria(produtoDetails.getCategoria());
        produto.setLote(produtoDetails.getLote());

        return produtoRepository.save(produto);
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public void deleteLogicProduto(Long id) {
        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }
}
