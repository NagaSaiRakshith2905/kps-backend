package com.capgemini.link.pojos;


import com.capgemini.link.model.Space;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkResponse {
    private Integer id;
    private String label;
    private  String sourceNode;
    private String targetNode;
    private Integer length;
    private Integer noOfSpaces;
    private List<Space> spaces;
}

