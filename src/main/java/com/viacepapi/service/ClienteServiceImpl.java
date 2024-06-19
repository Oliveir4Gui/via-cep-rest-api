package com.viacepapi.service;

import com.viacepapi.model.Cliente;
import com.viacepapi.model.ClienteRepository;
import com.viacepapi.model.Endereco;
import com.viacepapi.model.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl  implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente inserir(Cliente cliente) {
           Endereco endereco = enderecoRepository.findById(cliente.getEndereco().getCep()).orElseGet(()-> {
           Endereco novoEndereco = viaCepService.buscarEnderecoPorCep(cliente.getEndereco().getCep());
           return enderecoRepository.save(novoEndereco);
        });
           cliente.setEndereco(endereco);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clienteSalvo = buscarPorId(id);
        if(clienteSalvo == null){
            return null;
        }
        Endereco endereco = enderecoRepository.findById(cliente.getEndereco().getCep()).orElseGet(()-> {
            Endereco novoEndereco = viaCepService.buscarEnderecoPorCep(cliente.getEndereco().getCep());
            return enderecoRepository.save(novoEndereco);
        });
        cliente.setEndereco(endereco);
        return clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
