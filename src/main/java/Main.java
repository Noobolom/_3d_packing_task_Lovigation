import entities.Case;
import entities.OrderLine;
import entities.Product;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Product> Products = Product.addListOfProducts();
        List<Case> Cases = Case.addListOfCases();
        List<OrderLine> Orders = OrderLine.addListOfOrders();
        Order order = new Order();
        List <Pack> Packs = order.Pack(Products, Cases, Orders);
        for (Pack pack: Packs) {
            System.out.println();
            System.out.println("Order №" + pack.getOrderId());
            System.out.println("Product Id: " + pack.getProductToPack().getId());
            System.out.println(pack.getCaseToPack());
            System.out.println(pack.getPositionsInCase());
            System.out.println("Used space " + pack.getUsedSpace());
        }
    }
}
