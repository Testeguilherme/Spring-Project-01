package com.springbr.demo.controller;

import com.springbr.demo.entities.Cliente;
import com.springbr.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        @GetMapping("/all")
        public List<Cliente> findAll(){
                List<Cliente> allClients = clienteRepository.findAll();
                return allClients;
        }

        //FIND BY ID
        @GetMapping("/{id}")
        public ResponseEntity getClientById(@PathVariable int id){
                Optional<Cliente> client =  clienteRepository.findById(id);
                if(client.isPresent()){
                        return ResponseEntity.ok(client.get());
                }
                return ResponseEntity.notFound().build();
        }

        //ADD VALUES TO YOUR DATABASE
        @PostMapping("/register")
        public ResponseEntity saveClient(@RequestBody Cliente cliente){
                Cliente clientSaved = clienteRepository.save(cliente);
                return ResponseEntity.ok(clientSaved);
        }

        //DELETE
        @GetMapping("/delete/{id}")
        public ResponseEntity deleteCliente(@PathVariable Integer id){
                Optional<Cliente> cliente = clienteRepository.findById(id);
                if(cliente.isPresent()){
                        clienteRepository.deleteById(id);
                        return ResponseEntity.noContent().build();
                } else{
                        return ResponseEntity.notFound().build();
                }
        }

        //UPDATE
        @PutMapping("/update/{id}")
        public ResponseEntity updateClient(@PathVariable Integer id, @RequestBody Cliente newClient){
                Optional<Cliente> client = clienteRepository.findById(id);
                if(client.isPresent()){
                        client.get().setNome(newClient.getNome());
                        Cliente updCliente = clienteRepository.save(client.get());
                        return ResponseEntity.noContent().build();
                }
                return ResponseEntity.notFound().build();
        }

        //ANOTHER WAY TO UPDATE
        @PutMapping("/update2/{id}")
        public ResponseEntity update2(@PathVariable Integer id, @RequestBody Cliente newClient){
                return clienteRepository
                        .findById(id)
                        .map(cliente -> {
                                newClient.setId(cliente.getId());
                                clienteRepository.save(newClient);
                                return ResponseEntity.noContent().build();
                        }).orElseGet( () -> ResponseEntity.notFound().build());
        }

}


