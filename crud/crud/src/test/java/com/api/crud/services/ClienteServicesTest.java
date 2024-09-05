package com.api.crud.services;

import com.api.crud.models.Cliente;
import com.api.crud.models.Persona;
import com.api.crud.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ClienteServiceTest {

    // Mocks
    private final ClienteRepository clienteRepository = Mockito.mock(ClienteRepository.class);
    private final ClienteService clienteService = new ClienteService(clienteRepository);

    @Test
    void listarClientesShouldReturnClientsList() {
        //GIVEN
        Persona personatest = new Persona();
        personatest.setDireccion("Dir 1");
        personatest.setEdad(30);
        personatest.setGenero("Masculino");
        personatest.setId(4L);
        personatest.setNombre("Johan");
        personatest.setIdentificacion("54564");
        personatest.setTelefono("222222");

        Cliente clienteTest = new Cliente();
        clienteTest.setContrasena("password101");
        clienteTest.setClienteId(4L);
        clienteTest.setEstado(true);
        clienteTest.setPersona(personatest);

        Mockito.when(clienteRepository.findAll()).thenReturn(List.of(clienteTest));

        //when
        List<Cliente> listaCliente = clienteService.listarClientes();

        //then
        assertEquals(1, listaCliente.size());
        assertEquals(clienteTest.getClienteId(), listaCliente.get(0).getClienteId());
    }

    @Test
    void listarClientesShouldReturnEmptyList() {
        //when
        List<Cliente> listaCliente = clienteService.listarClientes();

        //then
        assertEquals(0, listaCliente.size());
        assertEquals(List.of(), listaCliente);
    }
}