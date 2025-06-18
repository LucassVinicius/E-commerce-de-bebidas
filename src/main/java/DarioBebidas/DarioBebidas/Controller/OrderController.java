package DarioBebidas.DarioBebidas.Controller;
import DarioBebidas.DarioBebidas.Dto.OrderRequest;
import DarioBebidas.DarioBebidas.Repository.DrinkRepository;
import DarioBebidas.DarioBebidas.Repository.OrderRepository;
import DarioBebidas.DarioBebidas.Repository.UserRepository;
import DarioBebidas.DarioBebidas.model.Drink;
import DarioBebidas.DarioBebidas.model.Orders;
import DarioBebidas.DarioBebidas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    private final DrinkRepository drinkRepository;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public OrderController(
            DrinkRepository drinkRepository,
            OrderRepository orderRepository,
            UserRepository userRepository
    ) {
        this.drinkRepository = drinkRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody OrderRequest orderRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        try {

            User user = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            List<Drink> drinks = orderRequest.getDrinks().stream()
                    .map(d -> drinkRepository.findById(d.getId())
                            .orElseThrow(() -> new RuntimeException("Drink não encontrado: " + d.getId())))
                    .toList();

            Orders order = new Orders(
                    drinks,
                    orderRequest.getPaymentMethod(),
                    orderRequest.getDeliveryAddress(),
                    orderRequest.getPhone()
            );
            order.setUserId(user.getId());
            orderRepository.save(order);

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
    @PutMapping("/{orderId}/cancel")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> cancelOrder(
            @PathVariable Long orderId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        Orders order = optionalOrder.get();

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!order.getUserId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para cancelar este pedido");
        }

        if (!order.getStatus().equalsIgnoreCase("AGUARDANDO") &&
                !order.getStatus().equalsIgnoreCase("EM PREPARACAO")) {
            return ResponseEntity.badRequest().body("Não é possível cancelar pedidos que já saíram para entrega");
        }

        order.setStatus("CANCELADO");
        orderRepository.save(order);

        return ResponseEntity.ok(order); //
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

    @GetMapping("/user")
    public List<Orders> getOrdersByUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return orderRepository.findByUserId(user.getId());
    }
    @PutMapping("/{orderId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId, @RequestBody Map<String, String> request) {
        String newStatus = request.get("status");

        Optional<Orders> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        Orders order = optionalOrder.get();
        order.setStatus(newStatus);
        orderRepository.save(order);

        return ResponseEntity.ok("Status atualizado para: " + newStatus);
    }
}


