package com.capgemini.link.pojos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkRegisterRequest {
    private String label;
    private  String sourceNode;
    private String targetNode;
    private Integer length;
    private Integer noOfSpaces;
}
