import org.springframework.data.jpa.repository.JpaRepository; 
puplic interface UserRepository extends JpaRepository <User , Long> {
  User findByUsername(String username );
}
