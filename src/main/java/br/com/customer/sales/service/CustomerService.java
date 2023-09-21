package br.com.customer.sales.service;

import br.com.customer.sales.dtos.*;
import br.com.customer.sales.entity.CustomerEntity;
import br.com.customer.sales.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    public StandardResponse addCustomer(CustomerDTO customerDTO) {

        var customerCreated = customerRepository.save(CustomerEntity.builder()
                .name(customerDTO.getName())
                .age(customerDTO.getAge())
                .accountBalance(customerDTO.getAccountBalance())
                .build());


        return customerCreated == null ? StandardResponse.builder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message("não-cadastrado")
                .build() : StandardResponse.builder()
                .code(HttpStatus.CREATED.value())
                .message("cadastro realizado com sucesso")
                .build();

    }

    public StandardResponse editCustomerAccountBalance(CustomerDTO customerDTO) {

        var customerFound = customerRepository.findById(customerDTO.getName());
        if (customerFound.isPresent()) {
            customerFound.get().setAccountBalance(customerDTO.getAccountBalance());
            var savedCustomer = customerRepository.save(customerFound.get());

            return StandardResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("saldo atualizado com sucesso")
                    .build();
        }
        return StandardResponse.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("cliente não encontrado")
                .build();
    }

    public List<CustomerDTO> getAllCustomers() {
        var header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        var entity = new HttpEntity<>(header);
        var restTemplateResponse = restTemplate.exchange(
                "http://localhost:8080/V1/customer/customerList/", HttpMethod.GET, entity,
                CustomerListDTO.class
        );

        /*
        var dataReceived = customerRepository.findAll();
        var dtoList = new ArrayList<CustomerDTO>();
        dataReceived.listIterator().forEachRemaining(customerEntity -> {
            dtoList.add(CustomerDTO.builder()
                    .name(customerEntity.getName())
                    .age(customerEntity.getAge())
                    .accountBalance(customerEntity.getAccountBalance())
                    .build());

        });
        return dtoList;
    }


 */
        return restTemplateResponse.getBody().getData();
    }

    public StandardResponse bikeSale(String bikeId, String customerId) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var entity = new HttpEntity<StandardResponse>(headers);
        var restTemplateResult = restTemplate.exchange(
                "http://localhost:8080/V1/Bike/purchase/" + bikeId + "/" + customerId ,
                HttpMethod.POST, entity, StandardResponse.class);

        return restTemplateResult.getBody();
    }

    public StandardResponse editBike(String bikeId) {
        var header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        var entity = new HttpEntity<StandardResponse>(header);
        var restTemplateResult = restTemplate.exchange(
                "http://localhost:8080/V1/Bike/change/" + bikeId ,
                HttpMethod.PUT, entity, StandardResponse.class
        );
        return restTemplateResult.getBody();
    }

    public StandardResponse deleteBike(String bikeId) {
        var header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        var entity = new HttpEntity<StandardResponse>(header);
        var restTemplateResult = restTemplate.exchange(
                "http://localhost:8080/V1/Bike/delete/" + bikeId, HttpMethod.DELETE, entity,
                StandardResponse.class
        );
        return restTemplateResult.getBody();

    }

    public List<BikeDTO> getAllBikes() {
        var header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        var httpEntity = new HttpEntity<>(header);
        var restTemplateResponse = restTemplate.exchange(
                "http://localhost:8080/V1/Bike/bikeList/", HttpMethod.GET, httpEntity, BikeListDTO.class);

        return restTemplateResponse.getBody().getData();

    }


}
