package com.mcsv.orderservice.service;

import com.mcsv.orderservice.dto.InventarioResponse;
import com.mcsv.orderservice.dto.OrderLineItemsDto;
import com.mcsv.orderservice.dto.OrderRequest;
import com.mcsv.orderservice.model.Order;
import com.mcsv.orderservice.model.OrderLineItems;
import com.mcsv.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient webClient;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setNumeroPedido(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItemsList);

        List<String> codSkuList = order.getOrderLineItemsList().stream()
                        .map(OrderLineItems::getCodigoSku)
                                .collect(Collectors.toList());
        System.out.println("codSkuList: "+codSkuList);
        InventarioResponse[] inventarioResponseArray = webClient.get()
                        .uri("http://localhost:8083/api/inventrio", uriBuilder -> uriBuilder.queryParam("codigoSku", codSkuList).build())
                                .retrieve()
                                        .bodyToMono(InventarioResponse[].class)
                                                .block();
        System.out.println("inventarioResponseArray " + inventarioResponseArray);
        boolean allProductsInStock = Arrays.stream(inventarioResponseArray).allMatch(InventarioResponse::isInStock);
        if (allProductsInStock){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("El producto no esta en stock");
        }
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrecio(orderLineItemsDto.getPrecio());
        orderLineItems.setCantidad(orderLineItemsDto.getCantidad());
        orderLineItems.setCodigoSku(orderLineItemsDto.getCodigoSku());
        return orderLineItems;
    }



}
