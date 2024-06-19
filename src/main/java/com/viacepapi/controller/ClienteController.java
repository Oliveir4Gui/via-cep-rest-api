package com.viacepapi.controller;


import com.viacepapi.model.Cliente;
import com.viacepapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    ResponseEntity<Iterable<Cliente>> buscarClientes() {
        return ResponseEntity.ok(clienteService.buscarClientes());
    }
    @PostMapping("/clientes")
    ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.inserir(cliente));
    }

    @PutMapping("/clientes/{id}")
    ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.atualizar(id, cliente));
    }

    @DeleteMapping("/clientes/{id}")
    ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
