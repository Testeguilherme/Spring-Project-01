package com.springbr.demo.services;


import com.springbr.demo.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //O Service é utizado para estabelecer as regras de negócio
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;





}
