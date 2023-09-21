package br.com.customer.sales.controller;

import br.com.customer.sales.dtos.BikeDTO;
import br.com.customer.sales.dtos.CustomerDTO;
import br.com.customer.sales.dtos.StandardResponse;
import br.com.customer.sales.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("V1/client")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<StandardResponse> addClient(@RequestBody CustomerDTO customerDTO) {

        var result = customerService.addCustomer(customerDTO);

        return ResponseEntity.status(result.getCode()).body(result);

    }

    @PutMapping("/edit")
    public ResponseEntity<StandardResponse> editClient(@RequestBody CustomerDTO customerDTO) {
        var edited = customerService.editCustomerAccountBalance(customerDTO);

        return ResponseEntity.status(edited.getCode()).body(edited);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDTO>> getAll() {
        var list = customerService.getAllCustomers();

        return list.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }

    @PostMapping("/purchase/{clientId}/{bikeId}")
    public ResponseEntity<StandardResponse> purchase(@PathVariable("clientId") String clientId,
                                                     @PathVariable("bikeId") String bikeId) {

        var sale = customerService.bikeSale(bikeId, clientId);

        return ResponseEntity.status(sale.getCode()).body(sale);

    }

    @GetMapping("/listAllBikes")
    public ResponseEntity<List<BikeDTO>> listAllBikes() {
        var allBikes = customerService.getAllBikes();

        return  allBikes.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(allBikes);
    }

    @PutMapping("/editBike/{bikeId}")
    public ResponseEntity<StandardResponse> editBike(@PathVariable ("bikeId") String bikeId) {
        var editedBike = customerService.editBike(bikeId);

        return ResponseEntity.ok(editedBike);
    }

    @DeleteMapping("/deleteBike/{bikeId}")
    public ResponseEntity<StandardResponse> deleteBike(@PathVariable ("bikeId") String bikeId) {
        var deletedBike = customerService.deleteBike(bikeId);

        return ResponseEntity.ok(deletedBike);
    }

}
