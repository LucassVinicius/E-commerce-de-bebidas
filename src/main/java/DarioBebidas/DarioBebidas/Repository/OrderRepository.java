package DarioBebidas.DarioBebidas.Repository;

import DarioBebidas.DarioBebidas.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {}
