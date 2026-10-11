package co.edu.uniremington.ordenes.controller;

import co.edu.uniremington.ordenes.model.*;
import co.edu.uniremington.ordenes.repository.*;
import co.edu.uniremington.ordenes.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final ClientRepository clients;
    private final EquipmentRepository equipment;
    private final ServiceOrderRepository orders;
    private final OrderService orderService;

    public ApiController(ClientRepository clients, EquipmentRepository equipment,
                         ServiceOrderRepository orders, OrderService orderService) {
        this.clients=clients; this.equipment=equipment; this.orders=orders; this.orderService=orderService;
    }

    @GetMapping("/clientes") public List<Client> getClients(){ return clients.findAll(); }
    @PostMapping("/clientes") @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody Client client) {
        client.setId(null);
        if (client.getName()==null || client.getName().isBlank() || client.getEmail()==null || client.getEmail().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nombre y correo son obligatorios");
        return clients.save(client);
    }

    @GetMapping("/equipos") public List<Equipment> getEquipment(){ return equipment.findAll(); }
    @PostMapping("/equipos") @ResponseStatus(HttpStatus.CREATED)
    public Equipment createEquipment(@RequestBody Map<String,Object> body) {
        Long clientId = Long.valueOf(body.get("clientId").toString());
        Client client = clients.findById(clientId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        Equipment e = new Equipment(body.get("description").toString(), body.get("serialNumber").toString(), "RECIBIDO", client);
        return equipment.save(e);
    }

    @GetMapping("/ordenes") public List<ServiceOrder> getOrders(){ return orderService.findAll(); }
    @PostMapping("/ordenes") @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrder createOrder(@RequestBody Map<String,Object> body) {
        Long equipmentId = Long.valueOf(body.get("equipmentId").toString());
        Equipment e = equipment.findById(equipmentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado"));
        String description = body.get("description").toString();
        ServiceOrder order = new ServiceOrder(description, "ABIERTA", LocalDate.now(), e);
        e.setStatus("EN_SERVICIO"); equipment.save(e);
        return orders.save(order);
    }

    @PatchMapping("/ordenes/{id}/estado")
    public ServiceOrder updateStatus(@PathVariable Long id, @RequestBody Map<String,String> body) {
        try { return orderService.updateStatus(id, body.getOrDefault("status", "ABIERTA")); }
        catch (IllegalArgumentException ex) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage()); }
    }
}