package com.springbr.demo.controllers;


import com.springbr.demo.entities.Produto;
import com.springbr.demo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    //SAVE
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save( @RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    //FIND ALL
    @GetMapping("/")
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    //FIND BY ID
    @GetMapping("/{id}")
    public Produto findById(@PathVariable Integer id){
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        produtoRepository.findById(id)
                .map(produto -> {produtoRepository.delete(produto);
                    return produto;})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    //UPDATE
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Produto newProduto){
        produtoRepository.findById(id)
                .map(produto -> {newProduto.setId(produto.getId());
                    produtoRepository.save(newProduto);
                    return newProduto;})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }


}
