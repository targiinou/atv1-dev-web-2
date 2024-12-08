package com.targinou.atv_1.service;

import com.targinou.atv_1.model.PedidoEntity;
import com.targinou.atv_1.model.ProdutoEntity;
import com.targinou.atv_1.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoEntity> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<PedidoEntity> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    public PedidoEntity createPedido(PedidoEntity pedido) {
        pedido.setAtivo(true);
        return pedidoRepository.save(pedido);
    }

    public PedidoEntity updatePedido(Long id, PedidoEntity pedidoDetails) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setCodigo(pedidoDetails.getCodigo());
        pedido.setCliente(pedidoDetails.getCliente());
        pedido.setProdutos(pedidoDetails.getProdutos());

        return pedidoRepository.save(pedido);
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public void deleteLogicPedido(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setAtivo(false);
        pedidoRepository.save(pedido);
    }

    public PedidoEntity adicionarProduto(Long id, ProdutoEntity produto) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.getProdutos().add(produto);
        return pedidoRepository.save(pedido);
    }

    public PedidoEntity removerProduto(Long id, ProdutoEntity produto) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.getProdutos().removeIf(p -> p.getId().equals(produto.getId()));

        return pedidoRepository.save(pedido);
    }

}
