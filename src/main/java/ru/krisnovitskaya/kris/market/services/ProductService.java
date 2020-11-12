package ru.krisnovitskaya.kris.market.services;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.repositories.ProductRepository;
import ru.krisnovitskaya.kris.market.soap.ProductXML;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public List<Product> getAll(Specification<Product> spec) {
        return productRepository.findAll(spec);
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }
    public ProductXML getXMLProductById(long id) {
        ProductXML p = new ProductXML();
        Product product = productRepository.findById(id).get();
        p.setId(product.getId());
        p.setName(product.getTitle());
        p.setPrice(product.getPrice());
        return p;
    }

    public ArrayList<ProductXML> getAllinXML(Specification<Product> spec) {
        List<Product> products = productRepository.findAll(spec);
        ArrayList<ProductXML> productList = new ArrayList<>();
        for (Product product : products) {
            ProductXML p = new ProductXML();
            p.setId(product.getId());
            p.setName(product.getTitle());
            p.setPrice(product.getPrice());
            productList.add(p);
        }
        return productList;
    }
}
