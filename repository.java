import jakarta.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo() {}
    public Todo(String title, User user) {
        this.title = title;
        this.user = user;
    }
    // getters & setters
}

