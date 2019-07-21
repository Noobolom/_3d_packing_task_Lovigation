package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product {

    private int id;
    private int sizeX;
    private int sizeY;
    private int sizeZ;

    public Product(int id, int sizeX, int sizeY, int sizeZ) {
        this.id = id;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    public int getId() {
        return id;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSizeZ() {
        return sizeZ;
    }

    public float getVolume(){
        return (sizeX * sizeY) * sizeZ;
    }

    public static Product getProductById(List<Product> Products, int Id){
        for (Product product: Products) {
            if (product.getId() == Id) return product;
        }
        return null;
    }

    public static List<Product> addListOfProducts() {
        Scanner in = new Scanner(System.in);
        List<Product> Products = new ArrayList<Product>();
        while (true) {
            System.out.println("Enter product parameters");
            System.out.print("Id: ");
            int id = in.nextInt();
            if(Product.getProductById(Products, id) != null){
                System.out.println("Product with id " + id + " already exist");
                continue;
            }
            System.out.print("sizeX: ");
            int sizeX = in.nextInt();
            System.out.print("sizeY: ");
            int sizeY = in.nextInt();
            System.out.print("sizeZ: ");
            int sizeZ = in.nextInt();
            Products.add(new Product(id, sizeX, sizeY, sizeZ));
            System.out.println("One more?(y/n)");
            String decision = in.next();
            if (decision.equals("n")) break;
        }
        return Products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                this.sizeX == product.sizeX &&
                this.sizeY == product.sizeY &&
                this.sizeZ == product.sizeZ;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", sizeZ=" + sizeZ +
                '}';
    }
}
