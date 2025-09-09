import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoRepository todoRepo;
    private final AuthController authController;

    public TodoController(TodoRepository todoRepo, AuthController authController) {
        this.todoRepo = todoRepo;
        this.authController = authController;
    }

    @GetMapping
    public List<Todo> getTodos() {
        User user = authController.getLoggedInUser();
        if (user == null) throw new RuntimeException("Not logged in!");
        return todoRepo.findByUser(user);
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        User user = authController.getLoggedInUser();
        if (user == null) throw new RuntimeException("Not logged in!");
        todo.setUser(user);
        return todoRepo.save(todo);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody Todo todoDetails) {
        User user = authController.getLoggedInUser();
        if (user == null) throw new RuntimeException("Not logged in!");

        Todo todo = todoRepo.findById(id).orElseThrow();
        if (!todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not your todo!");
        }

        todo.setTitle(todoDetails.getTitle());
        todo.setCompleted(todoDetails.isCompleted());
        return todoRepo.save(todo);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        User user = authController.getLoggedInUser();
        if (user == null) throw new RuntimeException("Not logged in!");

        Todo todo = todoRepo.findById(id).orElseThrow();
        if (!todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not your todo!");
        }

        todoRepo.delete(todo);
        return "Deleted!";
    }
}
