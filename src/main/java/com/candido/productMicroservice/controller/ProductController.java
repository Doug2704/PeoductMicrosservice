package com.candido.productMicroservice.controller;

import com.candido.productMicroservice.entity.Product;
import com.candido.productMicroservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "MICROSSERVIÇOS DE PRODUTOS")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    @Operation(summary = "CRIAR PRODUTO", description = "ARMAZENA UM NOVO PRODUTO NO BANCO DE DADOS")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product createdProduct = service.create(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "LOCALIZAR PRODUTO", description = "MOSTRA UM PRODUTO PELO ID INFORMADO")
    public ResponseEntity<?> findById(@PathVariable Long id) throws RuntimeException {

        try {
            Product p = service.findById(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "VER TODOS OS PRODUTOS", description = "MOSTRA TODOS OS PRODUTOS ARMAZENADOS")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAllproducts();
        return new ResponseEntity<>(products, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "ALTERAR DADOS DO PRODUTO", description = "ALTERA INFORMAÇÕES DE UM PRODUTO ESPECÍFICO")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) throws RuntimeException {
        try {
            Product p = service.update(id, product);
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "APAGAR PRODUTO", description = "APAGA UM PRODUTO DO BANCO DE DADOS")
    public ResponseEntity<?> delete(@PathVariable Long id) throws RuntimeException {
        try {
            service.delete(id);
            return new ResponseEntity<>("Produto deletado", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
