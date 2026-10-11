package co.edu.uniremington.ordenes.repository;
import co.edu.uniremington.ordenes.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientRepository extends JpaRepository<Client, Long> {}