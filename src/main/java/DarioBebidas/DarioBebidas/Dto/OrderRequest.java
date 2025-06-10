package DarioBebidas.DarioBebidas.Dto;

import DarioBebidas.DarioBebidas.model.Drink;

import java.util.List;

public class OrderRequest {
    private List<Drink> drinks;

    public OrderRequest() {}

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
