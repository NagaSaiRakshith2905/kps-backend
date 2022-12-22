package com.capgemini.kpsbackend.controller;

import com.capgemini.kpsbackend.dto.request.NetworkRequest;
import com.capgemini.kpsbackend.dto.request.NetworkUpdateRequest;
import com.capgemini.kpsbackend.entities.Network;
import com.capgemini.kpsbackend.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/network/")
public class NetworkController {
    @Autowired
    private NetworkService service;

    @PostMapping("add-network/")
    public ResponseEntity<Network> addNetwork(@RequestBody NetworkRequest networkRequest){
        return new ResponseEntity<>(service.add(networkRequest), HttpStatus.CREATED);
    }

    @GetMapping("get-all-networks/")
    public ResponseEntity<List<Network>> getAllNetwork(){
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @GetMapping ("get-networks-by-id/")
    public ResponseEntity<Network> getNetworkById(@RequestParam(value = "id") int id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.FOUND);
    }

    @PutMapping("update-network/")
    public ResponseEntity<Network> updateNetwork(@RequestBody NetworkUpdateRequest networkUpdateRequest){
        return new ResponseEntity<>(service.update(networkUpdateRequest),HttpStatus.OK);
    }

    @DeleteMapping("delete-network-by-id/")
    public void deleteNetworkById(@RequestParam(value = "id") int id){
        service.deleteById(id);
    }
}
