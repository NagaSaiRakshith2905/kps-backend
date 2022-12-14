package com.capgemini.network.controllers;

import com.capgemini.network.model.Network;
import com.capgemini.network.pojo.NetworkResponse;
import com.capgemini.network.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/network")
public class NetworkController {

    @Autowired
    private NetworkService networkService;

    @GetMapping("get-network-by-id")
    public ResponseEntity<NetworkResponse> getNetworkById(@RequestParam("id") int id) {
        return new ResponseEntity<>(networkService.getNetworkById(id), HttpStatus.OK);
    }

    @GetMapping("get-network-by-name")
    public ResponseEntity<NetworkResponse> getNetworkByName(@RequestParam("name") String name){

    }

    @GetMapping("get-all-network")
    public ResponseEntity<Network<NetworkResponse>> getAllNetworks() {
        return new ResponseEntity<>(networkService.getAllNetworks(), HttpStatus.OK);
    }

    @PostMapping("add-network")
    public ResponseEntity<Network> addNetwork(@ResponseBody NetworkRequest networkRequest){

    }

    @PutMapping("update-network")
    public ResponseEntity<NetworkResponse> updateNetwork(@ResponseBody ){

    }

    @DeleteMapping("delete-network-by-id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNetworkById(@RequestParam("id") int id){

    }




}
