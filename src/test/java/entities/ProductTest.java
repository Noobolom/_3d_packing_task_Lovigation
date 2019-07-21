package entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductTest {

    List<Product> Products = new ArrayList<Product>();

    @Before
    public void setUp(){
        Products.add(new Product(1, 10, 25, 10));
        Products.add(new Product(2, 11, 65, 54));
        Products.add(new Product(3, 47, 29, 12));
        Products.add(new Product(4, 8, 9, 7));
        Products.add(new Product(5, 14, 43, 21));
    }

    @Test
    public void getProductById() {
        Product expectedProduct = new Product(4, 8, 9, 7);
        Assert.assertEquals(expectedProduct, Product.getProductById(Products, 4));
        Assert.assertNull(Product.getProductById(Products, 6));
    }
}