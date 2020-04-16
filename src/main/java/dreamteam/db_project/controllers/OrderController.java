package dreamteam.db_project.controllers;

import dreamteam.db_project.model.Management;
import dreamteam.db_project.model.Orders;
import dreamteam.db_project.repository.OrdersRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/api")
public class OrderController {

   private final OrdersRepo ordersRepo;

    public OrderController(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }

    @GetMapping("/orders/{id}")
    public HttpEntity<Orders> getOrderBYId(@PathVariable(name = "id") String id)
    {
        Optional<Orders> optionalOrders = ordersRepo.findById(UUID.fromString(id));
        if (optionalOrders.isPresent())
        {
            return ResponseEntity.ok(optionalOrders.get());
        }
        else return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/orders/{id}")
    HttpEntity<Orders> deleteOrderById(@PathVariable String id)
    {
        Optional<Orders> optionalOrders = ordersRepo.findById(UUID.fromString(id));
        if (optionalOrders.isPresent())
        {
            ordersRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/orders")
    HttpEntity<Orders> saveOrder(@RequestBody Orders order)
    {
        if (order.getDate()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            Orders model=new Orders(order);
            Orders save = ordersRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/orders/{id}")
    HttpEntity<Orders> updateOrder(@RequestBody Orders orders,@PathVariable String id)
    {
        Optional<Orders> optionalOrders = ordersRepo.findById(UUID.fromString(id));
        if (!optionalOrders.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            orders.setId(UUID.fromString(id));
            Orders save = ordersRepo.save(orders);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/orders")
    HttpEntity<List<Orders>> getOrdersList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersRepo.findAll());
    }

    // UNDERDEVELOPMENT AND PLANNING PHASE

//    @GetMapping("/orders/date/{date}")
//    HttpEntity<List<Orders>> findOrderByDate(@PathVariable(name = "date") String date)
//    {
//        Timestamp valueOf = Timestamp.valueOf(date);
//        return ResponseEntity.ok(ordersRepo.fi(name));
//    }


}
