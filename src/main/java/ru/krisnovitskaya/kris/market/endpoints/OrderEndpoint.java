package ru.krisnovitskaya.kris.market.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.krisnovitskaya.kris.market.services.OrderService;
import ru.krisnovitskaya.kris.market.soap.GetUserOrdersRequest;
import ru.krisnovitskaya.kris.market.soap.GetUserOrdersResponse;

@Endpoint
public class OrderEndpoint {
    private static final String NAMESPACE_URI = "http://www.krisnovitskaya.com/ws/market";
    private OrderService orderService;

    @Autowired
    public OrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * Return info about all orders by input username
     * @param request GetUserOrdersRequest soap
     * @return GetUserOrdersResponse
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserOrdersRequest")
    @ResponsePayload
    public GetUserOrdersResponse getOrdersByUsername(@RequestPayload GetUserOrdersRequest request) {

        GetUserOrdersResponse response = new GetUserOrdersResponse();
        response.getOrder().addAll(orderService.findAllUserOrderXMLByUsername(request.getUsername()));
        return response;
    }
}
