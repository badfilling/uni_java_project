package entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class PurchaseElement {

    Integer productId;
    Integer count;

    public PurchaseElement() {

    }
    
    public PurchaseElement(Integer productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PurchaseElement [count=" + count + ", productId=" + productId + "]";
    }
}
