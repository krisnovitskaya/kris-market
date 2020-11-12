package ru.krisnovitskaya.kris.market.endpoints;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.krisnovitskaya.kris.market.services.ProductService;
import ru.krisnovitskaya.kris.market.soap.GetProductByIdRequest;
import ru.krisnovitskaya.kris.market.soap.GetProductByIdResponse;
import ru.krisnovitskaya.kris.market.soap.GetProductsRequest;
import ru.krisnovitskaya.kris.market.soap.GetProductsResponse;
import ru.krisnovitskaya.kris.market.utils.ProductFilter;

import java.util.*;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.krisnovitskaya.com/ws/market";
    private ProductService productService;


    @Autowired
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProduct(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productService.getXMLProductById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {

        MultiValueMap<String,String> valueMap = new LinkedMultiValueMap<>(){{
            if(request.getTitlePart() != null) put("title", new ArrayList<>(Collections.singletonList(request.getTitlePart())));
            if(request.getMinPrice() != null) put("min_price", new ArrayList<>(Collections.singletonList(request.getMinPrice().toString())));
            if(request.getMaxPrice() != null) put("max_price", new ArrayList<>(Collections.singletonList(request.getMaxPrice().toString())));
        }};
//        Map<String, String> params = new HashMap<>(){{
//            if(request.getTitlePart() != null) put("title", request.getTitlePart());
//            if(request.getMinPrice() != null) put("min_price", request.getMinPrice().toString());
//            if(request.getMaxPrice() != null) put("max_price", request.getMaxPrice().toString());
//        }};
        ProductFilter productFilter = new ProductFilter(valueMap);
        GetProductsResponse response = new GetProductsResponse();
        response.getProduct().addAll(productService.getAllinXML(productFilter.getSpec()));
        return response;
    }
}
