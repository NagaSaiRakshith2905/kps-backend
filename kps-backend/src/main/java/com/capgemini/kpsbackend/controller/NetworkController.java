package com.capgemini.kpsbackend.controller;

import com.capgemini.kpsbackend.dto.request.NetworkRequest;
import com.capgemini.kpsbackend.entities.Network;
import com.capgemini.kpsbackend.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/network/")
public class NetworkController {
    @Autowired
    private NetworkService service;

    @PostMapping("add-network/")
    public Network addNetwork(@RequestBody NetworkRequest networkRequest){
        return service.add(networkRequest);
    }

    @GetMapping("get-all-networks/")
    public List<Network> getAllNetwork(){
        return service.getAll();
    }

    @GetMapping ("get-networks-by-id/")
    public Network getNetworkById(@RequestParam(value = "id") int id){
        return service.getById(id);
    }

   /* @PutMapping("update-network/")
    public Network updateNetwork(){
        //Todo
    }*/

    @DeleteMapping("delete-network-by-id/")
    public void deleteNetworkById(@RequestParam(value = "id") int id){
        service.deleteById(id);
    }
}
