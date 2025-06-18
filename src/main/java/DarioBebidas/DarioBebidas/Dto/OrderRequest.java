package DarioBebidas.DarioBebidas.Dto;

import DarioBebidas.DarioBebidas.model.Drink;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequest {
    private List<Drink> drinks;
    private String paymentMethod;
    private String deliveryAddress;
    private String phone;
}

