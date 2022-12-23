package com.capgemini.kpsbackend.dto.request;

import com.capgemini.kpsbackend.entities.Network;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NetworkUpdateRequest {
    private Network updatedNetwork;
    private Network removedNetwork;
}

