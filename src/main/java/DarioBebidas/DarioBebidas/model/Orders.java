package DarioBebidas.DarioBebidas.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToMany
    private List<Drink> drinks;
    private String paymentMethod;
    private String status;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime orderTime;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime estimatedDeliveryTime;
    private String deliveryAddress;
    private String phone;

    public Orders(List<Drink> drinks, String paymentMethod, String deliveryAddress, String phone) {
        this.drinks = drinks;
        this.paymentMethod = paymentMethod;
        this.deliveryAddress = deliveryAddress;
        this.status = "AGUARDANDO";
        this.orderTime = LocalDateTime.now();
        this.estimatedDeliveryTime = this.orderTime.plusMinutes(30);
        this.phone = phone;
    }
}

