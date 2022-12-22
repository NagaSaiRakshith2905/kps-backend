package com.capgemini.kpsbackend.dto.request.link;

import com.capgemini.kpsbackend.entities.Network;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkRequest {
    private String label;
    private String fromNode;
    private String toNode;
    private Integer length;
    private Integer weight;
    private Integer noOfSpaces;
    private Network network;
}
