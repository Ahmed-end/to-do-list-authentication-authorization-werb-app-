import org.sprongframework.web.bind.annotation.*;
@RestController 
@RestMapping 
  public class authcontroller {
  private final UseRepository userRepo;
  private final PasswordEncoder encoder;

  public authcontroller (UserRepository userRepo , PasswordEncoder encoder) {
    this.userRepo = userRepo;
    this.encoder = encoder;
    @postMapping("/register")
      public string register(@requestBody User user) { 
       user .setPassword(encoder.encode(user.getPassword()));
       userRepo.save(user); 
       return "User registered" ; 
    }
  }
