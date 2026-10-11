package co.edu.uniremington.ordenes.repository;
import co.edu.uniremington.ordenes.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {}