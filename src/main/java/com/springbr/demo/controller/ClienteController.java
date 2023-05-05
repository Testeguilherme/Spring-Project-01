package com.springbr.demo.controller;

import com.springbr.demo.entities.Cliente;
import com.springbr.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        @GetMapping("/{id}")
        public ResponseEntity getClientById(@PathVariable int id){
                Optional<Cliente> client =  clienteRepository.findById(id);
                if(client.isPresent()){
                        return ResponseEntity.ok(client.get());
                }
                return ResponseEntity.notFound().build();
        }

        @GetMapping("/all")
        public List<Cliente> list(){
                List<Cliente> listOfClients = clienteRepository.findAll();
                return listOfClients;
        }



}
