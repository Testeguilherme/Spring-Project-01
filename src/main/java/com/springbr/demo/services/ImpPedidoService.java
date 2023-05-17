package com.springbr.demo.services;

import com.springbr.demo.DTO.ItemPedidoDTO;
import com.springbr.demo.DTO.PedidoDTO;
import com.springbr.demo.entities.Cliente;
import com.springbr.demo.entities.ItemPedido;
import com.springbr.demo.entities.Pedido;
import com.springbr.demo.entities.Produto;
import com.springbr.demo.exception.RegraNegocioException;
import com.springbr.demo.repositories.ClienteRepository;
import com.springbr.demo.repositories.ItemPedidoRepository;
import com.springbr.demo.repositories.PedidoRepository;
import com.springbr.demo.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImpPedidoService implements PedidoService{

    @Autowired
    private final PedidoRepository pedidoRepository;

    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final ProdutoRepository produtoRepository;

    @Autowired
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Pedido pedido = new Pedido();

        Integer clienteId = dto.getClienteId();
        Cliente cliente = clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }


    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RegraNegocioException("Lista vazia");
        }
        return itens.stream().map(dto -> {
            Integer produtoId = dto.getProdutoId();
            Produto produto = produtoRepository
                    .findById(produtoId)
                    .orElseThrow(() -> new RegraNegocioException("Código de produto inválido " +  produtoId));
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }




}




































