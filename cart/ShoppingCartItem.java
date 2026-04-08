package cart;

import entity.Product;

/**
 *
 * @author juanluis
 */
public class ShoppingCartItem {

    private Product product;
    private int amount;
    
    public ShoppingCartItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public void incrementAmount() {
        this.amount++;
    }
}