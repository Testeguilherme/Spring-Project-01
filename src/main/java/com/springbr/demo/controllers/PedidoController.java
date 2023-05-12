package com.springbr.demo.controllers;


import com.springbr.demo.entities.Pedido;
import com.springbr.demo.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    //SAVE
    @PostMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pedido save(@RequestBody Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    //FIND ALL
    @GetMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    //FIND BY ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pedido findById(@PathVariable Integer id){
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        pedidoRepository.findById(id)
                .map(pedido -> {pedidoRepository.delete(pedido);
                return pedido;})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    //UPDATE
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Pedido newPedido){
        pedidoRepository.findById(id)
                .map(pedido -> {newPedido.setId(pedido.getId());
                pedidoRepository.save(newPedido);
                return newPedido;})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

}
