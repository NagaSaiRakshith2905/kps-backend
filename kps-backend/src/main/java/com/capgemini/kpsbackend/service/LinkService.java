package com.capgemini.kpsbackend.service;

import com.capgemini.kpsbackend.dto.request.link.LinkRequest;
import com.capgemini.kpsbackend.entities.link.Link;
import com.capgemini.kpsbackend.entities.link.Space;
import com.capgemini.kpsbackend.entities.node.Node;
import com.capgemini.kpsbackend.exception.EntityNotFoundException;
import com.capgemini.kpsbackend.repository.link.LinkRepository;
import com.capgemini.kpsbackend.repository.link.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService implements ServiceInterface<LinkRequest, Link> {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @Override
    public Link add(LinkRequest linkRequest) {
        Link link = Link.builder()
                .fromNode(linkRequest.getFromNode())
                .toNode(linkRequest.getToNode())
                .label(linkRequest.getLabel())
                .length(linkRequest.getLength())
                .noOfSpaces(linkRequest.getNoOfSpaces())
                .network(linkRequest.getNetwork())
                .weight((int) (linkRequest.getLength() * 0.25))
                .build();
        Link savedLink = linkRepository.save(link);

        createSpaces(linkRequest.getNoOfSpaces(), savedLink);

        return savedLink;
    }

    private void createSpaces(Integer noOfSpaces, Link savedLink) {
        for (int i = 0; i < noOfSpaces; i++) {
            spaceRepository.save(Space.builder()
                    .isAvailable(true)
                    .spaceNumber(i)
                    .link(savedLink)
                    .build());
        }
    }

    @Override
    public List<Link> getAll() {
        return linkRepository.findAll();
    }

    @Override
    public Object getById(int id) {
        Optional<Link> link = linkRepository.findById(id);
        if(link.isEmpty()){
            throw new EntityNotFoundException("Link doesn't exists");
        }
        return link;
    }

    @Override
    @Transactional
    public Object update(Link object) {
        Optional<Link> link = linkRepository.findById(object.getId());
        if(link.isEmpty()){
            throw new EntityNotFoundException("Link doesn't exists");
        }
        String label = link.get().getLabel();
        String fromNode = link.get().getFromNode();
        String toNode = link.get().getToNode();
        int length = link.get().getLength();
        int weight = link.get().getWeight();
        int noOfSpaces = link.get().getNoOfSpaces();
        List <Space> spacesList = link.get().getSpaces();

        String objectLabel = object.getLabel();
        String objectFromNode = object.getFromNode();
        String objectToNode = object.getToNode();
        int objectLength = object.getLength();
        int objectWeight = object.getWeight();
        int objectNoOfSpaces = object.getNoOfSpaces();
        List <Space> objectSpacesList = object.getSpaces();

        if(!label.equals(objectLabel)){
            link.get().setLabel(objectLabel);
        }
        else if (!fromNode.equals(objectFromNode)) {
            link.get().setFromNode(objectFromNode);
        }
        else if(!toNode.equals(objectToNode)){
            link.get().setToNode(objectToNode);
        }
        else if(length!=objectLength){
            link.get().setLength(objectLength);
        }
        else if(weight!=objectWeight){
            link.get().setWeight(objectWeight);
        }
        else if(noOfSpaces!=objectNoOfSpaces){
            link.get().setNoOfSpaces(objectNoOfSpaces);
        }
        else if(!spacesList.equals(objectSpacesList)){
            //Todo
        }
        return link;
    }

    @Override
    public void deleteById(int id) {
        Optional<Link> link = linkRepository.findById(id);
        if(link.isEmpty()){
            throw new EntityNotFoundException("Link doesn't exists");
        }
        linkRepository.deleteById(id);
    }
}
