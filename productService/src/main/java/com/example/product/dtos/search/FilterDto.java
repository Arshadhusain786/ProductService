package com.example.product.dtos.search;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto
{
    private String key;
    private List<String> values;

    public class TestLombok {
        public static void main(String[] args) {
            FilterDto dto = new FilterDto();
            dto.setKey("ram");
            System.out.println(dto.getKey());
        }
    }


}

