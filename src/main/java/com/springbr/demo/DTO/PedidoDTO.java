package com.springbr.demo.DTO;

/*
{
    "cliente": 1,
    "total": 100,
    "itens": [
        {
            "produto": 1,
            "quantidade": 10
        }
    ]
}
 */

import com.springbr.demo.entities.Cliente;
import com.springbr.demo.entities.Pedido;

import java.util.List;

public class PedidoDTO {

    private Integer clienteId;
    private Double total;
    private List<ItemPedidoDTO> itens;

    public PedidoDTO(Cliente clienteId, Pedido total, List<ItemPedidoDTO> itens) {
        this.clienteId = clienteId.getId();
        this.total = total.getTotal();
        this.itens = itens;
    }
}
















