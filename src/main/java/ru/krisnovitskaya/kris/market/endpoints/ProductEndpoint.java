package ru.krisnovitskaya.kris.market.endpoints;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.krisnovitskaya.kris.market.repositories.specifications.ProductSpecifications;
import ru.krisnovitskaya.kris.market.services.ProductService;
import ru.krisnovitskaya.kris.market.soap.GetProductByIdRequest;
import ru.krisnovitskaya.kris.market.soap.GetProductByIdResponse;
import ru.krisnovitskaya.kris.market.soap.GetProductsRequest;
import ru.krisnovitskaya.kris.market.soap.GetProductsResponse;
import ru.krisnovitskaya.kris.market.utils.ProductFilter;

import java.util.*;
import java.util.stream.Collectors;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.krisnovitskaya.com/ws/market";
    private ProductService productService;


    @Autowired
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Return info about product with input id
     * @param request GetProductByIdRequest
     * @return GetProductByIdResponse
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProduct(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productService.getXMLProductById(request.getId()));
        return response;
    }


    /**
     * Return info about product matching input params
     * @param request GetProductsRequest
     * @return GetProductsResponse
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {

        MultiValueMap<String,String> valueMap = new LinkedMultiValueMap<>(){{
            if(request.getTitlePart() != null) put("title", new ArrayList<>(Collections.singletonList(request.getTitlePart())));
            if(request.getMinPrice() != null) put("min_price", new ArrayList<>(Collections.singletonList(request.getMinPrice().toString())));
            if(request.getMaxPrice() != null) put("max_price", new ArrayList<>(Collections.singletonList(request.getMaxPrice().toString())));
            if(request.getCategory() != null && request.getCategory().size() >= 1){
                put("categories", request.getCategory().stream().map(aLong -> String.valueOf(aLong)).collect(Collectors.toList()));
            }
        }};

        ProductFilter productFilter = new ProductFilter(valueMap);
        GetProductsResponse response = new GetProductsResponse();
        response.getProduct().addAll(productService.getAllinXML(productFilter.getSpec().and(ProductSpecifications.fetchCategory())));
        return response;
    }
}
