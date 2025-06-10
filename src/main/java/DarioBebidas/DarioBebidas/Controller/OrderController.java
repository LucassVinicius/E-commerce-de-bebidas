package DarioBebidas.DarioBebidas.Controller;

import DarioBebidas.DarioBebidas.Dto.OrderRequest;
import DarioBebidas.DarioBebidas.Repository.DrinkRepository;
import DarioBebidas.DarioBebidas.model.Drink;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final DrinkRepository drinkRepository;

    public OrderController(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            System.out.println("Recebido: " + orderRequest);
            List<Drink> requestedDrinks = orderRequest.getDrinks();
            List<Drink> drinks = requestedDrinks.stream()
                    .map(d -> drinkRepository.findById(d.getId())
                            .orElseThrow(() -> new RuntimeException("Drink não encontrado: " + d.getId())))
                    .toList();

            if (drinks == null || drinks.isEmpty()) {
                return ResponseEntity.badRequest().body("Pedido vazio.");
            }

            System.out.println("Novo pedido com " + drinks.size() + " bebidas:");
            drinks.forEach(d -> System.out.println("- " + d.getName()));

            return ResponseEntity.ok(Map.of("message", "Pedido criado com sucesso"));

        } catch (Exception e) {
            e.printStackTrace(); // Isso vai mostrar o erro exato no terminal
            return ResponseEntity.status(500).body("Erro interno ao processar pedido.");
        }
    }
}
