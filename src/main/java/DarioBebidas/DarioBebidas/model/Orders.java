package DarioBebidas.DarioBebidas.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZoneId;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime orderTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime estimatedDeliveryTime;
    private String deliveryAddress;
    private String phone;
    public Orders(List<Drink> drinks, String paymentMethod, String deliveryAddress, String phone) {
        this.drinks = drinks;
        this.paymentMethod = paymentMethod;
        this.deliveryAddress = deliveryAddress;
        this.status = "AGUARDANDO";

        ZoneId saoPauloZone = ZoneId.of("America/Sao_Paulo");
        this.orderTime = OffsetDateTime.now(saoPauloZone);
        this.estimatedDeliveryTime = this.orderTime.plusMinutes(30);
        this.phone = phone;
    }
}


