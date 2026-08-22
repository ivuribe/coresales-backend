package com.coresales.service.customer.controller;

import com.coresales.service.customer.model.Customer;
import com.coresales.service.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    private final CustomerService clienteService;

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public CustomerController(CustomerService clienteService) {
        this.clienteService = clienteService;
    }

    //==========================================
    // GET /api/clientes
    //==========================================
    @GetMapping("/listar")
    public /*ResponseEntity<*/List<Customer>/*>*/ listar() {
        return /*ResponseEntity.ok(*/clienteService.listar()/*)*/;
    }

    //==========================================
    // GET /api/clientes/{id}
    //==========================================
    @GetMapping("/{id}")
    public ResponseEntity<Customer> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    //==========================================
    // POST /api/clientes
    //==========================================
    @PostMapping
    public ResponseEntity<Customer> crear(@RequestBody Customer request) {
        Customer response = clienteService.crear(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    //==========================================
    // PUT /api/clientes/{id}
    //==========================================
    @PutMapping("/{id}")
    public ResponseEntity<Customer> actualizar(@PathVariable Long id,@RequestBody Customer request) {
        Customer response = clienteService.actualizar(id, request);
        return ResponseEntity.ok(response);
    }

    //==========================================
    // DELETE /api/clientes/{id}
    //==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
