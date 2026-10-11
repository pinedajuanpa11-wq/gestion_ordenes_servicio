package co.edu.uniremington.ordenes.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ordenes_servicio")
public class ServiceOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 250)
    private String description;
    @Column(nullable = false, length = 30)
    private String status = "ABIERTA";
    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    public ServiceOrder() {}
    public ServiceOrder(String description, String status, LocalDate createdAt, Equipment equipment) {
        this.description=description; this.status=status; this.createdAt=createdAt; this.equipment=equipment;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
    public LocalDate getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDate createdAt){this.createdAt=createdAt;}
    public Equipment getEquipment(){return equipment;} public void setEquipment(Equipment equipment){this.equipment=equipment;}
}