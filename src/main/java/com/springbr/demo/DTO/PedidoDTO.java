package com.springbr.demo.DTO;


import com.springbr.demo.entities.Cliente;
import com.springbr.demo.entities.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
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
















