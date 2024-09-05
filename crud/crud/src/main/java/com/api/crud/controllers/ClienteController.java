package com.api.crud.controllers;

import com.api.crud.execptions.ResponseMessage;
import com.api.crud.models.Cliente;
import com.api.crud.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Listar todos los clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    // Guardar un nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.guardarCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    // Actualizar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, clienteActualizado));
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> eliminarCliente(@PathVariable Long id) {
        ResponseMessage response = clienteService.eliminarCliente(id);
        if (response.getMessage().equals("Cliente eliminado con éxito.")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}