package DarioBebidas.DarioBebidas.Service;
import DarioBebidas.DarioBebidas.Dto.OrderRequest;
import DarioBebidas.DarioBebidas.model.Orders;
import DarioBebidas.DarioBebidas.Repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Orders createOrder(OrderRequest request) {
        Orders order = new Orders(
                request.getDrinks(),
                request.getPaymentMethod(),
                request.getDeliveryAddress(),
                request.getPhone()
        );

        return orderRepository.save(order);
    }

    public List<Orders> listOrders() {
        return orderRepository.findAll();
    }

    public Orders updateStatus(Long id, String status) {
        Orders order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
