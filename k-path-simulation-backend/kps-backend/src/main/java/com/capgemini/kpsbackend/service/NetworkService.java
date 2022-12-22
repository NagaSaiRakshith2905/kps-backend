package com.capgemini.kpsbackend.service;

import com.capgemini.kpsbackend.dto.request.NetworkRequest;
import com.capgemini.kpsbackend.dto.request.NetworkUpdateRequest;
import com.capgemini.kpsbackend.entities.Network;
import com.capgemini.kpsbackend.entities.link.Link;
import com.capgemini.kpsbackend.entities.node.Node;
import com.capgemini.kpsbackend.exception.EntityNotFoundException;
import com.capgemini.kpsbackend.repository.NetworkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NetworkService implements ServiceInterface<NetworkRequest, Network,NetworkUpdateRequest> {

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
        List<Network> network = networkRepository.findAll();
        return networkRepository.findAll();
    }

    @Override
    public Network getById(int id) {
        Optional<Network> network = networkRepository.findById(id);
        if(network.isEmpty()){
            throw new EntityNotFoundException("Network doesn't exists");
        }
        return network.get();
    }

    @Override
    @Transactional
    public Network update(NetworkUpdateRequest object) {
        int id = object.getUpdatedNetwork().getId();
        Optional<Network> network = networkRepository.findById(id);
        if(network.isEmpty()){
            throw new EntityNotFoundException("Network doesn't exists");
        }
        object.getRemovedNetwork().getNodes().forEach(node -> {
            nodeService.deleteById(node.getId());
        });
        object.getRemovedNetwork().getLinks().forEach(link -> {
            linkService.deleteById(link.getId());
        });

        String name = network.get().getNetworkName();
        String username = network.get().getUsername();

        Optional<String> objectName = Optional.of(object.getUpdatedNetwork().getNetworkName());
        Optional<String> objectUsername =  Optional.of(object.getUpdatedNetwork().getUsername());
        Optional<List<Node>> objectNode = Optional.of(object.getUpdatedNetwork().getNodes());
        Optional<List<Link>> objectLink = Optional.of(object.getUpdatedNetwork().getLinks());

        if(objectName.isPresent()){
            if(!name.equals(objectName.get())){
                network.get().setNetworkName(objectName.get());
            }
        }
        if(objectUsername.isPresent()){
            if(!username.equals(objectUsername.get())){
                network.get().setUsername(objectUsername.get());
            }
        }
        if(objectNode.isPresent()){
            objectNode.get().forEach(node1 -> nodeService.update(node1));
        }

        if(objectLink.isPresent()){
                objectLink.get().forEach(link1 -> linkService.update(link1));
        }
        return network.get();
    }

    @Override
    public void deleteById(int id) {
        Optional<Network> network = networkRepository.findById(id);
        if(network.isEmpty()){
            throw new EntityNotFoundException("Network doesn't exists");
        }
        networkRepository.deleteById(id);
    }

}
