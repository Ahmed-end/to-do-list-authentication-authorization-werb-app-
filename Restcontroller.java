import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepo;
    private User loggedInUser = null; // simple in-memory "session"

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return "Username already exists!";
        }
        userRepo.save(user);
        return "User registered!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User found = userRepo.findByUsername(user.getUsername());
        if (found != null && found.getPassword().equals(user.getPassword())) {
            loggedInUser = found;
            return "Login successful!";
        }
        return "Invalid credentials!";
    }

    @PostMapping("/logout")
    public String logout() {
        loggedInUser = null;
        return "Logged out!";
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}

