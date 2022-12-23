package com.capgemini.kpsbackend.service;

import com.capgemini.kpsbackend.dto.request.NetworkRequest;
import com.capgemini.kpsbackend.dto.request.NetworkUpdateRequest;
import com.capgemini.kpsbackend.entities.Network;

import java.util.List;

public interface ServiceInterface {
    Object add(NetworkRequest object);
    List<Network> getAll();
    Object getById(int id);
    Object update(NetworkUpdateRequest object);
    void deleteById(int id);

    List<Network> getAllNetworkForUser(String username);
}
