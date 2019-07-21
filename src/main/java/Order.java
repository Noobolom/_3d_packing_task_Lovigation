import entities.Case;
import entities.OrderLine;
import entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Pack> Packs;

    public Order() {
        Packs = new ArrayList<Pack>();
    }

    public Pack getTheMostOptimalPack (List<Pack> packs){
        float TheBiggestPercentOfUsedSpace = 0;
        int index = 0;
        for (int i = 0; i < packs.size(); i++){
            if (packs.get(i).getUsedSpace() > TheBiggestPercentOfUsedSpace) {
                TheBiggestPercentOfUsedSpace = packs.get(i).getUsedSpace();
                index = i;
            }
        }
        return packs.get(index);
    }

    public List<Pack> Pack(List<Product> Products, List<Case> Cases, List<OrderLine> Orders){
        // Iterating orders
        for (OrderLine orderline: Orders) {
            Product ProductToPack = Product.getProductById(Products, orderline.getProductId());
            if (ProductToPack == null){
                System.out.println("Order with id " + orderline.getId() + " is cancelled, because product with id " + orderline.getProductId() + " doesn't exist");
                continue;
            }
            // Creating list of fit cases
            List<Case> FitCases = new ArrayList<Case>();
            for (Case case_ : Cases) {
                if (case_.getSizeX() >= ProductToPack.getSizeX() &&
                        case_.getSizeY() >= ProductToPack.getSizeY() &&
                        case_.getSizeZ() >= ProductToPack.getSizeZ() &&
                        case_.getVolume() >= ProductToPack.getVolume())
                    FitCases.add(case_);
            }

            // Temporary list to put in all variants of pack
            List<Pack> TempPacks = new ArrayList<Pack>();
            // if no fit cases, then this product isn't packable
            if (!FitCases.isEmpty()){
                for (Case case_ : FitCases) {
                    Pack Package = new Pack();
                    int levelX = 0;
                    int levelY = 0;
                    int levelZ = 0;
                    // packing the first product
                    levelX += ProductToPack.getSizeX();
                    levelY += ProductToPack.getSizeY();
                    levelZ += ProductToPack.getSizeZ();

                    Package.setOrderId(orderline.getId());
                    Package.setCaseToPack(case_);
                    Package.setProductToPack(ProductToPack);
                    Package.addNewPosition(new Position(levelX, levelY, levelZ));

                    int NumberOfPackedProducts = 1;

                    // packing other products
                    while (NumberOfPackedProducts < orderline.getNumberOfProducts()) {
                        if (levelX + ProductToPack.getSizeX() <= case_.getSizeX()) {
                            levelX += ProductToPack.getSizeX();
                            Package.addNewPosition(new Position(levelX, levelY, levelZ));
                            NumberOfPackedProducts++;
                        } else if (levelY + ProductToPack.getSizeY() <= case_.getSizeY()) {
                            levelY += ProductToPack.getSizeY();
                            levelX = ProductToPack.getSizeX();
                            Package.addNewPosition(new Position(levelX, levelY, levelZ));
                            NumberOfPackedProducts++;
                        } else if (levelZ + ProductToPack.getSizeZ() <= case_.getSizeZ()) {
                            levelZ += ProductToPack.getSizeZ();
                            levelX = ProductToPack.getSizeX();
                            levelY = ProductToPack.getSizeY();
                            Package.addNewPosition(new Position(levelX, levelY, levelZ));
                            NumberOfPackedProducts++;
                        } else break;
                    }
                    // if all products don't fit in case, then this case will be not added to the main list
                    if (NumberOfPackedProducts != orderline.getNumberOfProducts()) {
                        continue;
                    }
                    Package.setNumberOfProducts(NumberOfPackedProducts);
                    float PackedVolume = ProductToPack.getVolume() * NumberOfPackedProducts;
                    float GeneralVolume = case_.getVolume();
                    float PercentOfUsedSpace = (PackedVolume / GeneralVolume) * 100;
                    Package.setUsedSpace(PercentOfUsedSpace);
                    TempPacks.add(Package);
                }
                // Putting the most optimal variant of pack in main list
                Packs.add(getTheMostOptimalPack(TempPacks));
                // Remove used case
                Cases.remove(getTheMostOptimalPack(TempPacks).getCaseToPack());

            } else System.out.println("There is no fit cases for product with id: " + ProductToPack.getId());
        }
        return Packs;
    }
}
