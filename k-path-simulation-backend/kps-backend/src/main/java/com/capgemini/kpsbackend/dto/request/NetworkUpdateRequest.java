package com.capgemini.kpsbackend.dto.request;

import com.capgemini.kpsbackend.entities.Network;
import com.capgemini.kpsbackend.entities.circuit.Circuit;
import com.capgemini.kpsbackend.entities.link.Link;
import com.capgemini.kpsbackend.entities.node.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NetworkUpdateRequest {
    private Network updatedNetwork;
    private Network removedNetwork;
}
