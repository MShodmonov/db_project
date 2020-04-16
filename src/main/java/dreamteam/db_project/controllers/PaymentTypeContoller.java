package dreamteam.db_project.controllers;

import dreamteam.db_project.model.PaymentTypes;
import dreamteam.db_project.repository.PaymentTypesRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/api")
public class PaymentTypeContoller {
    private final PaymentTypesRepo paymentTypesRepo;

    public PaymentTypeContoller(PaymentTypesRepo paymentTypesRepo) {
        this.paymentTypesRepo = paymentTypesRepo;
    }


    @GetMapping("/payment_types/{id}")
    public HttpEntity<PaymentTypes> getPaymentTypeById(@PathVariable(name = "id") String id) {
        Optional<PaymentTypes> optionalPaymentTypes = paymentTypesRepo.findById(UUID.fromString(id));
        if (optionalPaymentTypes.isPresent()) {
            return ResponseEntity.ok(optionalPaymentTypes.get());
        } else return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/payment_types/{id}")
    HttpEntity<PaymentTypes> deletePaymentTypeBYId(@PathVariable String id) {
        Optional<PaymentTypes> optionalPaymentTypes = paymentTypesRepo.findById(UUID.fromString(id));
        if (optionalPaymentTypes.isPresent()) {
            paymentTypesRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping("/payment_types")
    HttpEntity<PaymentTypes> savePaymentTypes(@RequestBody PaymentTypes paymentTypes) {
        if (paymentTypes.getName() == null) {
            return ResponseEntity.noContent().build();
        } else {
            PaymentTypes model = new PaymentTypes(paymentTypes.getName());
            PaymentTypes save = paymentTypesRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }

    @PutMapping("/payment_types/{id}")
    HttpEntity<PaymentTypes> updatePaymentTypes(@RequestBody PaymentTypes paymentTypes, @PathVariable String id) {
        Optional<PaymentTypes> optionalPaymentTypes = paymentTypesRepo.findById(UUID.fromString(id));
        if (!optionalPaymentTypes.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            paymentTypes.setId(UUID.fromString(id));
            PaymentTypes save = paymentTypesRepo.save(paymentTypes);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }

    @GetMapping("/payment_types")
    HttpEntity<List<PaymentTypes>> getPaymentTypesList() {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentTypesRepo.findAll());
    }

    @GetMapping("/payment_types/fullname/{fullName}")
    HttpEntity<PaymentTypes> findPaymentTypesByName(@PathVariable(name = "name") String name) {
        Optional<PaymentTypes> optionalPaymentTypes = paymentTypesRepo.findByName(name);
        return optionalPaymentTypes.<HttpEntity<PaymentTypes>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
