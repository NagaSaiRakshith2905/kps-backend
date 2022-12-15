package com.capgemini.kpsbackend.service;

import com.capgemini.kpsbackend.dto.request.NetworkRequest;
import com.capgemini.kpsbackend.entities.Network;
import com.capgemini.kpsbackend.repository.NetworkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NetworkService implements ServiceInterface<NetworkRequest, Network> {

    @Autowired
    private NetworkRepository networkRepository;
    @Autowired
    private NodeService nodeService;

    @Autowired
    private LinkService linkService;

    @Override
    public Network add(NetworkRequest networkRequest) {
        Network network = Network.builder()
                .networkName(networkRequest.getNetworkName())
                .username(networkRequest.getUsername())
                .build();
        Network savedNetwork = networkRepository.save(network);

        networkRequest.getNodes().forEach(nodeRequest -> {
            nodeRequest.setNetwork(savedNetwork);
            nodeService.add(nodeRequest);
        });

        networkRequest.getLinks().forEach(linkRequest -> {
            linkRequest.setNetwork(savedNetwork);
            linkService.add(linkRequest);
        });
        return savedNetwork;
    }

    @Override
    public List<Network> getAll() {
        return networkRepository.findAll();
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public Object update(Network link) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

}
