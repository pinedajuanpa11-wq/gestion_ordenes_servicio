package co.edu.uniremington.ordenes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipos")
public class Equipment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String description;
    @Column(nullable = false, length = 60)
    private String serialNumber;
    @Column(nullable = false, length = 30)
    private String status = "RECIBIDO";
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Equipment() {}
    public Equipment(String description, String serialNumber, String status, Client client) {
        this.description=description; this.serialNumber=serialNumber; this.status=status; this.client=client;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public String getSerialNumber(){return serialNumber;} public void setSerialNumber(String serialNumber){this.serialNumber=serialNumber;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
    public Client getClient(){return client;} public void setClient(Client client){this.client=client;}
}