package com.viacepapi.service;

import com.viacepapi.model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarClientes();

    Cliente buscarPorId(Long id);

    Cliente inserir(Cliente cliente);

    Cliente atualizar(Long id, Cliente cliente);

    void deletar(Long id);


}
