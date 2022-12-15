package com.capgemini.kpsbackend.service;

import com.capgemini.kpsbackend.dto.request.link.LinkRequest;
import com.capgemini.kpsbackend.entities.link.Link;
import com.capgemini.kpsbackend.entities.link.Space;
import com.capgemini.kpsbackend.repository.link.LinkRepository;
import com.capgemini.kpsbackend.repository.link.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public Object update(Link object) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
