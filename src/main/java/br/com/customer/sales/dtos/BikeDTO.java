package br.com.customer.sales.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BikeDTO {

    private String id;

    private String name;

    private String type;

    private Long rimSize;

    private Long price;

    private String owner;

}
