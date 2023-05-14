package com.springbr.demo.DTO;

/*
{
    "cliente": 1,
    "total": 100,
    "itens": [{ "produto": 1, "quantidade": 10 }]
}
 */

import com.springbr.demo.entities.ItemPedido;
import com.springbr.demo.entities.Produto;

public class ItemPedidoDTO {

    private Integer produtoId;

    private Integer quantidade;


    public ItemPedidoDTO(Produto ent, ItemPedido entity) {
        this.produtoId = entity.getId();
        this.quantidade = entity.getQuantidade();
    }


}
