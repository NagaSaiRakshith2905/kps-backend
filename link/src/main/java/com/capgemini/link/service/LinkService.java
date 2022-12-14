package com.capgemini.link.service;


import com.capgemini.link.model.Link;
import com.capgemini.link.pojos.LinkRegisterRequest;
import com.capgemini.link.pojos.LinkResponse;

import java.util.List;

public interface LinkService {
    LinkResponse getLinkById(int id);

    Link getLink(int id);

    LinkResponse getLinkByLabel(String label);

    List<LinkResponse> getAllLinks();

    Link addLink(LinkRegisterRequest linkRegisterRequest);

    LinkResponse updateLink(LinkRegisterRequest linkRegisterRequest);

    void deleteLinkById(int id);

    Link blockSpaceForLink(int linkId);
}
