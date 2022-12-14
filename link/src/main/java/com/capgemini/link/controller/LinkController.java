package com.capgemini.link.controller;

import com.capgemini.link.model.Link;
import com.capgemini.link.pojos.LinkRegisterRequest;
import com.capgemini.link.pojos.LinkResponse;
import com.capgemini.link.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/link/")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("get-link-by-id/")
    public ResponseEntity<LinkResponse> getLinkById(@RequestParam("id") int id) {
        return new ResponseEntity<>(linkService.getLinkById(id), HttpStatus.OK);
    }

    @GetMapping("get-link-by-label/")
    public ResponseEntity<LinkResponse> getLinkByLabel(@RequestParam("label") String label) {
        return new ResponseEntity<>(linkService.getLinkByLabel(label), HttpStatus.OK);
    }

    @GetMapping("get-all-links/")
    public ResponseEntity<List<LinkResponse>> getLinkByLabel() {
        return new ResponseEntity<>(linkService.getAllLinks(), HttpStatus.OK);
    }

    @PostMapping("add-link/")
    public ResponseEntity<Link> addLink(@RequestBody LinkRegisterRequest linkRegisterRequest){
        return new ResponseEntity<>(linkService.addLink(linkRegisterRequest),HttpStatus.CREATED);
    }

    @PutMapping("block-space-for-link/")
    public ResponseEntity<Link> blockSpaceForLink(@RequestParam("link-id") int linkId){
        return new ResponseEntity<>(linkService.blockSpaceForLink(linkId),HttpStatus.OK);
    }

    @PutMapping("update-link/")
    public ResponseEntity<LinkResponse> updateLink(@RequestBody LinkRegisterRequest linkRegisterRequest){
        return new ResponseEntity<>(linkService.updateLink(linkRegisterRequest),HttpStatus.OK);
    }

    @DeleteMapping("delete-by-id/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLinkById(@RequestParam("id") int id){
        linkService.deleteLinkById(id);
    }
}
