package DarioBebidas.DarioBebidas.Controller;
import DarioBebidas.DarioBebidas.Service.DrinkService;
import DarioBebidas.DarioBebidas.model.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/drinks")
@CrossOrigin(origins = "http://localhost:3000")
public class DrinkController {
    @Autowired
    private final DrinkService drinkService;
    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public List<Drink> getAllDrinks() {
        return drinkService.getAllDrinks();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Drink> addDrink(
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("imageURL") MultipartFile imageURL
    ) {
        File uploadDir = new File("uploads");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = Paths.get(imageURL.getOriginalFilename()).getFileName().toString();
        Path imagePath = Paths.get("uploads", fileName);
        try {
            Files.copy(imageURL.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        Drink drink = new Drink();
        drink.setName(name);
        drink.setPrice(price);
        drink.setImageURL("/uploads/" + fileName);

        return ResponseEntity.ok(drinkService.addDrink(drink));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable Long id) {
        boolean deleted = drinkService.deleteDrink(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Drink> atualizar(@PathVariable Long id, @RequestBody Drink novoDrink) {
        Drink drink = drinkService.buscarPorId(id);

        drink.setName(novoDrink.getName());
        drink.setPrice(novoDrink.getPrice());
        drink.setImageURL(novoDrink.getImageURL());

        return ResponseEntity.ok(drinkService.salvar(drink));
    }

}
