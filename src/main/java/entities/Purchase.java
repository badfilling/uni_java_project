package entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Purchase")
public class Purchase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "totalPrice")
    protected Double totalPrice;

    @ElementCollection(fetch = FetchType.EAGER)
    protected List<PurchaseElement> items;

    public Purchase() {

    }

    public Purchase(int id, Double totalPrice, List<PurchaseElement> items) {
        super();
        this.id = id;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Purchase( Double totalPrice, List<PurchaseElement> items) {
        super();
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<PurchaseElement> getItems() {
        return items;
    }

    public void setItems(List<PurchaseElement> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Purchase [id=" + id + ", items=" + items + ", totalPrice=" + totalPrice + "]";
    }
}
