package co.edu.uniremington.ordenes.controller;

import co.edu.uniremington.ordenes.model.Client;
import co.edu.uniremington.ordenes.model.Equipment;
import co.edu.uniremington.ordenes.model.ServiceOrder;
import co.edu.uniremington.ordenes.repository.ClientRepository;
import co.edu.uniremington.ordenes.repository.EquipmentRepository;
import co.edu.uniremington.ordenes.repository.ServiceOrderRepository;
import co.edu.uniremington.ordenes.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class WebController {
    private final ClientRepository clients;
    private final EquipmentRepository equipment;
    private final ServiceOrderRepository orders;
    private final OrderService orderService;

    public WebController(ClientRepository clients, EquipmentRepository equipment, ServiceOrderRepository orders, OrderService orderService) {
        this.clients=clients; this.equipment=equipment; this.orders=orders; this.orderService=orderService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("clients", clients.findAll());
        model.addAttribute("equipment", equipment.findAll());
        model.addAttribute("orders", orders.findAll());
        return "index";
    }

    @PostMapping("/clientes")
    public String addClient(@RequestParam String name, @RequestParam String email, @RequestParam(required=false) String phone) {
        clients.save(new Client(name, email, phone)); return "redirect:/";
    }

    @PostMapping("/equipos")
    public String addEquipment(@RequestParam String description, @RequestParam String serialNumber, @RequestParam Long clientId) {
        Client c = clients.findById(clientId).orElseThrow();
        equipment.save(new Equipment(description, serialNumber, "RECIBIDO", c)); return "redirect:/";
    }

    @PostMapping("/ordenes")
    public String addOrder(@RequestParam String description, @RequestParam Long equipmentId) {
        Equipment e = equipment.findById(equipmentId).orElseThrow();
        e.setStatus("EN_SERVICIO"); equipment.save(e);
        orders.save(new ServiceOrder(description, "ABIERTA", LocalDate.now(), e)); return "redirect:/";
    }

    @PostMapping("/ordenes/{id}/cerrar")
    public String closeOrder(@PathVariable Long id) {
        orderService.updateStatus(id, "CERRADA"); return "redirect:/";
    }
}