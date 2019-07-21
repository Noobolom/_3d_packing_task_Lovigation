package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderLine {

    private int id;
    private int numberOfProducts;
    private int ProductId;

    public OrderLine() {
    }

    public OrderLine(int id, int numberOfProducts, int productId) {
        this.id = id;
        this.numberOfProducts = numberOfProducts;
        this.ProductId = productId;
    }

    public static OrderLine getOrderById(List<OrderLine> Orders, int id){
        for (OrderLine order: Orders) {
            if (order.getId() == id) return order;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public int getProductId() {
        return ProductId;
    }

    public static List<OrderLine> addListOfOrders() {
        Scanner in = new Scanner(System.in);
        List<OrderLine> orders = new ArrayList<OrderLine>();
        while (true) {
            System.out.println("Enter order parameters");
            System.out.print("Id: ");
            int id = in.nextInt();
            if(OrderLine.getOrderById(orders, id) != null){
                System.out.println("Order with id " + id + " already exist");
            }
            System.out.print("Number of products: ");
            int NumberOfProducts = in.nextInt();
            System.out.print("Product Id: ");
            int ProductId = in.nextInt();
            orders.add(new OrderLine(id, NumberOfProducts, ProductId));
            System.out.println("One more?(y/n)");
            String decision = in.next();
            if (decision.equals("n")) break;
        }
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine)) return false;
        OrderLine orderLine = (OrderLine) o;
        return id == orderLine.id &&
                numberOfProducts == orderLine.numberOfProducts &&
                ProductId == orderLine.ProductId;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", numberOfProducts=" + numberOfProducts +
                ", ProductId=" + ProductId +
                '}';
    }
}
