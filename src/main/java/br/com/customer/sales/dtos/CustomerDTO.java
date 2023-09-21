package br.com.customer.sales.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinitions;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String name;

    private Long age;

    private Long accountBalance;


}
