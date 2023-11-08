package tn.esprit.devops_project.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
class ProductServiceImplTest {
    @Autowired
    ProductServiceImpl productService;

    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    void addProduct() {
        Product product = new Product();
        product.setTitle("Produit 1");
        product.setPrice(25.00F);
        product.setQuantity(50);
        product.setCategory(ProductCategory.ELECTRONICS);
        Long stockId = 1L;
        Product savedProduct = productService.addProduct(product, stockId);
        //assertNotNull(savedProduct);
        //assertNotNull(savedProduct.getIdProduct());
        assertEquals("Produit 1", savedProduct.getTitle());
//        assertEquals(25.00, savedProduct.getPrice(), 0.01); // Utilisez delta pour les comparaisons de valeurs flottantes
//        assertEquals(50, savedProduct.getQuantity());
//        assertEquals(ProductCategory.ELECTRONICS, savedProduct.getCategory());
//        assertNotNull(savedProduct.getStock());
//        assertEquals(stockId, savedProduct.getStock().getIdStock());
    }

    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    void retrieveProduct() {
        Long productId = 1L;
        Product product = productService.retrieveProduct(productId);
        assertNotNull(product);
        assertEquals(productId, product.getIdProduct());
    }

    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    void retreiveAllProduct() {
        List<Product> products = productService.retreiveAllProduct();
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    void retrieveProductByCategory() {
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> products = productService.retrieveProductByCategory(category);
        assertNotNull(products);
        assertFalse(products.isEmpty());
        for (Product product : products) {
            assertEquals(category, product.getCategory());
        }
    }

    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    void deleteProduct() {
        productService.deleteProduct(1L);
        List<Product> products=this.productService.retreiveAllProduct();
        assertEquals(products.size(),0);

    }

    @Test
    @DatabaseSetup("/data-set/product-data.xml")
    void retreiveProductStock() {
        Long stockId = 1L;

        List<Product> products = productService.retreiveProductStock(stockId);
        assertNotNull(products);
        assertFalse(products.isEmpty());
        for (Product product : products) {
            assertEquals(stockId, product.getStock().getIdStock());
        }

    }
}