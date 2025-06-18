package DarioBebidas.DarioBebidas.Service;
import DarioBebidas.DarioBebidas.Repository.DrinkRepository;
import DarioBebidas.DarioBebidas.model.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {
    @Autowired
    private final DrinkRepository repository;

    public DrinkService(DrinkRepository repository) {
        this.repository = repository;
    }

    public List<Drink> getAllDrinks() {
        return repository.findAll();
    }
    public Drink addDrink(Drink drink) {
        return repository.save(drink);
    }
    public Drink buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bebida não encontrada com ID: " + id));
    }

    public Drink salvar(Drink drink) {
        return repository.save(drink);
    }
    public boolean deleteDrink(Long id) {
        Optional<Drink> optionalDrink = repository.findById(id);
        if (optionalDrink.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
