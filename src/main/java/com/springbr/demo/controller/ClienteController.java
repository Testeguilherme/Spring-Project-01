package com.springbr.demo.controller;

import com.springbr.demo.entities.Cliente;
import com.springbr.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

        @Autowired
        private ClienteRepository clienteRepository;

        @GetMapping("/hello/{nome}")
        public String hello(@PathVariable String nome){
                return "Hello World! " + nome;
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

        //SAVE CLIENTS
        @PostMapping("/")
        @ResponseStatus(HttpStatus.CREATED)
        public Cliente save(@RequestBody Cliente cliente){
                return clienteRepository.save(cliente);
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
//        @PutMapping("/{id}")
//        public ResponseEntity updateClient(@PathVariable Integer id, @RequestBody Cliente newClient){
//                Optional<Cliente> client = clienteRepository.findById(id);
//                if(client.isPresent()){
//                        client.get().setNome(newClient.getNome());
//                        Cliente updCliente = clienteRepository.save(client.get());
//                        return ResponseEntity.noContent().build();
//                }
//                return ResponseEntity.notFound().build();
//        }

        //UPDATE
        @PutMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void update2(@PathVariable Integer id, @RequestBody Cliente newClient){
                clienteRepository.findById(id)
                        .map(cliente -> {newClient.setId(cliente.getId());
                                clienteRepository.save(newClient);
                                return ResponseEntity.noContent().build();
                        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        }


}


