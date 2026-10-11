package co.edu.uniremington.ordenes.repository;
import co.edu.uniremington.ordenes.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {}