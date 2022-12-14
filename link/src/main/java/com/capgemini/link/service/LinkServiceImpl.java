package com.capgemini.link.service;

import com.capgemini.link.exception.LinkNotFoundException;
import com.capgemini.link.model.Link;
import com.capgemini.link.model.Space;
import com.capgemini.link.pojos.LinkRegisterRequest;
import com.capgemini.link.pojos.LinkResponse;
import com.capgemini.link.repository.LinkRepository;
import com.capgemini.link.repository.SpaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private SpaceRepository spaceRepository;

    private static final String INVALID_ID = "invalid id";

    @Override
    public LinkResponse getLinkById(int id) {
        Optional<Link> link = linkRepository.findById(id);
        if (link.isEmpty())
            throw new LinkNotFoundException(INVALID_ID);
        return mapToLinkResponse(link.get());
    }

    @Override
    public Link getLink(int id) {
        Optional<Link> link = linkRepository.findById(id);
        if (link.isEmpty())
            throw new LinkNotFoundException(INVALID_ID);
        return link.get();
    }

    @Override
    public LinkResponse getLinkByLabel(String label) {
        Optional<Link> link = linkRepository.findByLabel(label);
        if (link.isEmpty())
            throw new LinkNotFoundException(INVALID_ID);
        return mapToLinkResponse(link.get());
    }

    @Override
    public List<LinkResponse> getAllLinks() {
        List<Link> links = linkRepository.findAll();
        return links.stream().map(this::mapToLinkResponse).collect(Collectors.toList());
    }

    private LinkResponse mapToLinkResponse(Link link) {
        return LinkResponse.builder()
                .id(link.getId())
                .label(link.getLabel())
                .length(link.getLength())
                .noOfSpaces(link.getNoOfSpaces())
                .sourceNode(link.getSourceNode())
                .targetNode(link.getTargetNode())
                .spaces(link.getSpaces())
                .build();
    }

    @Override
    public Link addLink(LinkRegisterRequest linkRegisterRequest) {
        Link link = Link.builder()
                .label(linkRegisterRequest.getLabel())
                .sourceNode(linkRegisterRequest.getSourceNode())
                .length(linkRegisterRequest.getLength())
                .noOfSpaces(linkRegisterRequest.getNoOfSpaces())
                .targetNode(linkRegisterRequest.getTargetNode())
                .build();
        Link savedLink = linkRepository.save(link);
        log.info(savedLink.getId().toString());
        for (int i = 0; i < link.getNoOfSpaces(); i++) {
            spaceRepository.save(Space.builder()
                    .isAvailable(true)
                    .link(savedLink)
                    .build());
        }
        return savedLink;
    }

    @Override
    @Transactional
    public LinkResponse updateLink(LinkRegisterRequest linkRegisterRequest) {
        Optional<Link> link = linkRepository.findByLabel(linkRegisterRequest.getLabel());
        if (link.isEmpty())
            throw new LinkNotFoundException(INVALID_ID);
        link.get().setLabel(linkRegisterRequest.getLabel());
        link.get().setLength(linkRegisterRequest.getLength());
        link.get().setNoOfSpaces(linkRegisterRequest.getNoOfSpaces());
        link.get().setSourceNode(linkRegisterRequest.getSourceNode());
        link.get().setTargetNode(linkRegisterRequest.getTargetNode());
        link.get().setSpaces(setLinkSpaces(link.get().getSpaces()));

        return mapToLinkResponse(link.get());
    }

    private List<Space> setLinkSpaces(List<Space> spaces) {
        spaces.forEach(space -> space.setIsAvailable(true));
        return spaces;
    }


    @Override
    public void deleteLinkById(int id) {
        Optional<Link> link = linkRepository.findById(id);
        if (link.isEmpty())
            throw new LinkNotFoundException(INVALID_ID);
        linkRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Link blockSpaceForLink(int linkId) {
        Optional<Link> link = linkRepository.findById(linkId);
        if (link.isEmpty()) {
            throw new LinkNotFoundException(INVALID_ID);
        }
        for (Space space : link.get().getSpaces()) {
            if (Boolean.TRUE.equals(space.getIsAvailable())) {
                space.setIsAvailable(false);
                return link.get();
            }
        }
        throw new LinkNotFoundException("No Available Space");
    }
}
