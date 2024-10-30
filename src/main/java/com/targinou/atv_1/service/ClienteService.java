package com.targinou.atv_1.service;

import com.targinou.atv_1.model.ClienteEntity;
import com.targinou.atv_1.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteEntity> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public ClienteEntity createCliente(ClienteEntity cliente) {
        cliente.setAtivo(true);
        return clienteRepository.save(cliente);
    }

    public ClienteEntity updateCliente(Long id, ClienteEntity clienteDetails) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(clienteDetails.getNome());
        cliente.setCpf(clienteDetails.getCpf());
        cliente.setGenero(clienteDetails.getGenero());
        cliente.setDataNascimento(clienteDetails.getDataNascimento());

        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public void deleteLogicCliente(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
}
