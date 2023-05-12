package com.springbr.demo.controllers;

import com.springbr.demo.entities.Cliente;
import com.springbr.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

        @Autowired
        private ClienteRepository clienteRepository;

        //SAVE CLIENTS
        @PostMapping("/")
        @ResponseStatus(HttpStatus.CREATED)
        public Cliente save(@RequestBody Cliente cliente){
                return clienteRepository.save(cliente);
        }


        //FIND ALL
        @GetMapping("/")
        public List<Cliente> findAll(){
                List<Cliente> allClients = clienteRepository.findAll();
                return allClients;
        }


        //FIND BY ID
        @GetMapping("/{id}")
        public Cliente getClientById(@PathVariable int id){
                return clienteRepository
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
        }


        //DELETE
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteCliente(@PathVariable Integer id){
                clienteRepository.findById(id)
                        .map(cliente -> {
                                clienteRepository.delete(cliente);
                                return cliente;
                        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        }


        //UPDATE
        @PutMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void update2(@PathVariable Integer id, @RequestBody Cliente newClient){
                clienteRepository.findById(id)
                        .map(cliente -> {newClient.setId(cliente.getId());
                                clienteRepository.save(newClient);
                                return newClient;
                        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        }


}


