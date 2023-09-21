package br.com.customer.sales.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "customer-collection")
public class CustomerEntity {

    @Id
    private String name;

    private Long age;

    private Long accountBalance;
}
