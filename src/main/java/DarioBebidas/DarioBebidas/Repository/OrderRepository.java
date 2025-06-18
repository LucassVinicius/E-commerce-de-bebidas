package DarioBebidas.DarioBebidas.Repository;
import DarioBebidas.DarioBebidas.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);
}
