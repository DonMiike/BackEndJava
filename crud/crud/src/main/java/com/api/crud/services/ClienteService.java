package com.api.crud.services;

import com.api.crud.models.Cliente;
import com.api.crud.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {


    private ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new RuntimeException("Cliente no encontrado con el ID: " + id);
        }
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Cliente clienteExistente = obtenerClientePorId(id);
        clienteExistente.setPersona(clienteActualizado.getPersona()); 
        clienteExistente.setContrasena(clienteActualizado.getContrasena());
        clienteExistente.setEstado(clienteActualizado.getEstado());
        return clienteRepository.save(clienteExistente);
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerClientePorId(id);  
        clienteRepository.delete(cliente);
    }
}