package DarioBebidas.DarioBebidas.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Drink> drinks;

    private String paymentMethod;
    private String status;
    private LocalDateTime orderTime;
    private LocalDateTime estimatedDeliveryTime;

    public Orders() {}

    public Orders(List<Drink> drinks, String paymentMethod) {
        this.drinks = drinks;
        this.paymentMethod = paymentMethod;
        this.status = "AGUARDANDO";
        this.orderTime = LocalDateTime.now();
        this.estimatedDeliveryTime = this.orderTime.plusMinutes(30); // simula 30 min
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(LocalDateTime estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }
}
