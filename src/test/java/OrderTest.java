import entities.Case;
import entities.OrderLine;
import entities.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderTest {

    private List<Product> products = new ArrayList<Product>();
    private List<Case> cases = new ArrayList<Case>();
    private List<OrderLine> orderLine = new ArrayList<OrderLine>();
    private List<Position> positions = new ArrayList<Position>();
    private List<Position> positions1 = new ArrayList<Position>();
    private List<Position> positions2 = new ArrayList<Position>();
    private List<Pack> ExpectedPacks = new ArrayList<Pack>();
    private Order order = new Order();
    private Pack ExpectedTheMostOptimalPack = new Pack();

    @Before
    public void setUp(){

        products.add(new Product(1, 30, 15, 15));
        products.add(new Product(2, 5, 5, 5));
        products.add(new Product(3, 10, 1, 4));

        cases.add(new Case(1, 30, 10, 5));
        cases.add(new Case(2, 70, 15, 15));
        cases.add(new Case(3, 60, 15, 15));      // order 1 use this case
        cases.add(new Case(4, 5,5,10));          // order 2 use this case
        cases.add(new Case(5, 11,3,8));          // order 3 use this case

        orderLine.add(new OrderLine(1, 2, 1));   // order 1
        orderLine.add(new OrderLine(2, 2, 2));   // order 2
        orderLine.add(new OrderLine(3, 6, 3));   // order 3

        positions.add(new Position(30, 15, 15));
        positions.add(new Position(60, 15, 15));

        positions1.add(new Position(5, 5, 5));
        positions1.add(new Position(5, 5, 10));

        positions2.add(new Position(10, 1, 4));
        positions2.add(new Position(10, 2, 4));
        positions2.add(new Position(10, 3, 4));
        positions2.add(new Position(10, 1, 8));
        positions2.add(new Position(10, 2, 8));
        positions2.add(new Position(10, 3, 8));

        ExpectedPacks = new ArrayList<Pack>();

        float UsedVolume = (2f * (products.get(0).getVolume() / cases.get(2).getVolume())) * 100f;
        float UsedVolume1 = (2f * (products.get(1).getVolume() / cases.get(3).getVolume())) * 100f;
        float UsedVolume2 = (6f * (products.get(2).getVolume() / cases.get(4).getVolume())) * 100f;

        ExpectedPacks.add(new Pack(1, 2, positions, UsedVolume, cases.get(2), products.get(0)));
        ExpectedPacks.add(new Pack(2, 2, positions1, UsedVolume1, cases.get(3), products.get(1)));
        ExpectedPacks.add(new Pack(3, 6, positions2, UsedVolume2, cases.get(4), products.get(2)));

        ExpectedTheMostOptimalPack = ExpectedPacks.get(0);
    }

    @Test
    public void pack() {
        Assert.assertEquals(ExpectedPacks, order.Pack(products, cases, orderLine));
    }

    @Test()
    public void getTheMostOptimalPack(){
        Assert.assertEquals(ExpectedTheMostOptimalPack, order.getTheMostOptimalPack(ExpectedPacks));
    }
}