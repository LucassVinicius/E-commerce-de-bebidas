package DarioBebidas.DarioBebidas.Controller;

import DarioBebidas.DarioBebidas.Dto.OrderRequest;
import DarioBebidas.DarioBebidas.Repository.DrinkRepository;
import DarioBebidas.DarioBebidas.Repository.OrderRepository;
import DarioBebidas.DarioBebidas.model.Drink;
import DarioBebidas.DarioBebidas.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final DrinkRepository drinkRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(DrinkRepository drinkRepository, OrderRepository orderRepository) {
        this.drinkRepository = drinkRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            List<Drink> drinks = orderRequest.getDrinks().stream()
                    .map(d -> drinkRepository.findById(d.getId())
                            .orElseThrow(() -> new RuntimeException("Drink não encontrado: " + d.getId())))
                    .toList();

            Orders order = new Orders(drinks, orderRequest.getPaymentMethod());
            orderRepository.save(order);

            // 🖨️ Logs no console
            System.out.println("🛒 Novo pedido recebido!");
            System.out.println("📦 Bebidas:");
            drinks.forEach(d -> System.out.println("- " + d.getName()));
            System.out.println("💳 Forma de pagamento: " + order.getPaymentMethod());
            System.out.println("🕐 Pedido feito às: " + order.getOrderTime());
            System.out.println("📌 Status: " + order.getStatus());

            return ResponseEntity.ok(order);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno ao processar pedido.");
        }
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Pedido não encontrado"));
    }

}
