package co.edu.uniremington.ordenes.service;

import co.edu.uniremington.ordenes.model.Equipment;
import co.edu.uniremington.ordenes.model.ServiceOrder;
import co.edu.uniremington.ordenes.repository.EquipmentRepository;
import co.edu.uniremington.ordenes.repository.ServiceOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final ServiceOrderRepository orderRepository;
    private final EquipmentRepository equipmentRepository;

    public OrderService(ServiceOrderRepository orderRepository, EquipmentRepository equipmentRepository) {
        this.orderRepository = orderRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public List<ServiceOrder> findAll() { return orderRepository.findAll(); }

    @Transactional
    public ServiceOrder updateStatus(Long orderId, String newStatus) {
        ServiceOrder order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("No existe la orden " + orderId));
        order.setStatus(newStatus);
        Equipment equipment = order.getEquipment();
        equipment.setStatus(newStatus.equalsIgnoreCase("CERRADA") ? "REPARADO" : "EN_SERVICIO");
        equipmentRepository.save(equipment);
        return orderRepository.save(order);
    }
}