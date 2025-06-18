package DarioBebidas.DarioBebidas.Dto;

import DarioBebidas.DarioBebidas.model.Drink;

import java.util.List;

public class OrderRequest {
    private List<Drink> drinks;
    private String paymentMethod;
    private String deliveryAddress; // 🆕 Endereço de entrega

    public OrderRequest() {}

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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}

