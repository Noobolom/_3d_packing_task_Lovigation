import entities.Case;
import entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Pack {

        private int OrderId;
        private float UsedSpace;
        private int NumberOfProducts;
        private List<Position> PositionsInCase = new ArrayList<Position>();
        private Case CaseToPack;
        private Product ProductToPack;

    public Pack(int orderId, int numberOfProducts, List<Position> positionsInCase, float usedSpace, Case caseToPack, Product productToPack) {
        OrderId = orderId;
        UsedSpace = usedSpace;
        NumberOfProducts = numberOfProducts;
        PositionsInCase = positionsInCase;
        CaseToPack = caseToPack;
        ProductToPack = productToPack;
    }

    public Pack() {
    }

    public int getOrderId() {
        return OrderId;
    }

    public float getUsedSpace() {
        return UsedSpace;
    }

    public void setUsedSpace(float UsedSpace) {
        this.UsedSpace = UsedSpace;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public int getNumberOfProducts() {
        return NumberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        NumberOfProducts = numberOfProducts;
    }

    public List<Position> getPositionsInCase() {
        return PositionsInCase;
    }

    public void setPositionsInCase(List<Position> positionsInCase) {
        PositionsInCase = positionsInCase;
    }

    public void addNewPosition(Position position){
        PositionsInCase.add(position);
    }

    public Case getCaseToPack() {
        return CaseToPack;
    }

    public void setCaseToPack(Case caseToPack) {
        CaseToPack = caseToPack;
    }

    public Product getProductToPack() {
        return ProductToPack;
    }

    public void setProductToPack(Product productToPack) {
        ProductToPack = productToPack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pack)) return false;
        Pack pack = (Pack) o;
        return OrderId == pack.OrderId &&
                NumberOfProducts == pack.NumberOfProducts &&
                PositionsInCase.equals(pack.PositionsInCase) &&
                CaseToPack.equals(pack.CaseToPack) &&
                ProductToPack.equals(pack.ProductToPack);
    }

    @Override
    public String toString() {
        return "Pack{" +
                "OrderId=" + OrderId +
                ", UsedSpace=" + UsedSpace +
                "%, NumberOfProducts=" + NumberOfProducts +
                ", PositionsInCase=" + PositionsInCase +
                ", CaseToPack=" + CaseToPack +
                ", ProductToPack=" + ProductToPack +
                '}';
    }
}
