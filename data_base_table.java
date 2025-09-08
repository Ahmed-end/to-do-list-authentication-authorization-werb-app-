import jakarta.presistence.*;
@Entity 
public class User {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDETITY)
  private Long id ; 


 @column(uniuqe = true)
  private string username ; 
  private string password ; 
  private String role = "USER" ; 
}
