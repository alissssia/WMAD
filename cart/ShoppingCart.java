package cart;

import entity.Product;
import java.util.*;

/**
 *
 * @author juanluis
 */
public class ShoppingCart {
    private List<ShoppingCartItem> items;
    
    public ShoppingCart() {
        items = new ArrayList<ShoppingCartItem>();
    }
    
    public void add(Product product) {
        for (ShoppingCartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.incrementAmount();
                return;
            }
        }
        items.add(new ShoppingCartItem(product, 1));
    }
    
    public List<ShoppingCartItem> getItems() {
        return items;
    }
    
    public int getNumberOfItems() {
        int total = 0;
        for (ShoppingCartItem item : items) {
            total += item.getAmount();
        }
        return total;
    }
    
    public double getTotal() {
        double total = 0.0;
        for (ShoppingCartItem item : items) {
            total += item.getProduct().getPrice() * item.getAmount();
        }
        return total;
    }
    
    public void clear(){
        items.clear();
    }

    public void update(int productId, int quantity) {
        for (ShoppingCartItem item : items) {
            if (item.getProduct().getId() == productId) {
                item.setAmount(quantity);
                return;
            }
        }
    }

    public void remove(int productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId() == productId) {
                items.remove(i);
                return;
            }
        }
    }
    
    public int getAmountOfProduct(int productId) {
    for (ShoppingCartItem item : items) {
        if (item.getProduct().getId() == productId) {
            return item.getAmount();
        }
    }
    return 0;
}

}