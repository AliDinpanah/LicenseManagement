package teck.me.license.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.me.license.model.dto.CreateCustomerDto;
import teck.me.license.model.dto.CustomerDto;
import teck.me.license.model.dto.ListCustomerDto;
import teck.me.license.service.imp.CustomerServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerServiceImp customerService;


    public CustomerController(CustomerServiceImp customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<ListCustomerDto>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int number) {
        List<ListCustomerDto> customers = customerService.getAllCustomers(page, number);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String name) {
        CustomerDto customer = customerService.getCustomerById(name);
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CreateCustomerDto> createCustomer(@RequestBody CreateCustomerDto customerDto){
        CreateCustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{name}")
    public ResponseEntity<CreateCustomerDto> updateCustomer(@PathVariable String name, @RequestBody CreateCustomerDto updatedCustomer) {
        CreateCustomerDto customer = customerService.updateCustomer(name, updatedCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String name) {
        customerService.deleteCustomer(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
