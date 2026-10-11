package co.edu.uniremington.ordenes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 120)
    private String email;
    @Column(length = 25)
    private String phone;

    public Client() {}
    public Client(String name, String email, String phone) { this.name=name; this.email=email; this.phone=phone; }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getPhone(){return phone;} public void setPhone(String phone){this.phone=phone;}
}