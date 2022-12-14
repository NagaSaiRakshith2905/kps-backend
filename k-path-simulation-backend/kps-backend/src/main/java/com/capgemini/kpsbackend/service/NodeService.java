package com.capgemini.kpsbackend.service;

import com.capgemini.kpsbackend.dto.request.node.NodeRequest;
import com.capgemini.kpsbackend.entities.node.Edge;
import com.capgemini.kpsbackend.entities.node.Node;
import com.capgemini.kpsbackend.entities.node.NodeType;
import com.capgemini.kpsbackend.exception.EntityNotFoundException;
import com.capgemini.kpsbackend.repository.node.EdgeRepository;
import com.capgemini.kpsbackend.repository.node.NodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@Slf4j
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private EdgeRepository edgeRepository;

    public Node add(NodeRequest nodeRequest) {
        log.info(nodeRequest.toString());
        Node node = Node.builder()
                .nodeName(nodeRequest.getNodeName())
                .ipAddress(nodeRequest.getIpAddress())
                .password(nodeRequest.getPassword())
                .nodeType(mapToNodeType(nodeRequest.getNodeType()))
                .x(nodeRequest.getX())
                .y(nodeRequest.getY())
                .network(nodeRequest.getNetwork())
                .build();
        Node savedNode = nodeRepository.save(node);
        createEdges(node.getNodeType().getNoOfEdges(),savedNode);
        return savedNode;
    }

    private void createEdges(int noOfEdges,Node node) {
        for (int i=0;i<noOfEdges;i++){
            edgeRepository.save(Edge.builder()
                    .isAvailable(true)
                    .edgeName((char)(65+i))
                    .node(node)
                    .build());
        }
    }

    private NodeType mapToNodeType(String nodeType) {
        switch (nodeType) {
            case "ola":
                return NodeType.OLA;
            case "pass-through":
                return NodeType.PASS_THROUGH;
            default:
                return NodeType.ADD_DROP;
        }
    }

    public List<Node> getAll() {
        return nodeRepository.findAll();
    }

    public Node getById(int id) {
        Optional<Node> node = nodeRepository.findById(id);
        if(node.isEmpty()){
            throw new EntityNotFoundException("Node doesn't exists");
        }
        return node.get();
    }

    @Transactional
    public Node update(Node object) {
        Optional<Node> node = nodeRepository.findById(object.getId());
        if(node.isEmpty()){
            throw new EntityNotFoundException("Node doesn't exists");
        }
        String nodeName = node.get().getNodeName();
        String ipAddress = node.get().getIpAddress();
        String password = node.get().getPassword();
        int xPosition = node.get().getX();
        int yPosition = node.get().getY();
        NodeType nodeType = node.get().getNodeType();
        List<Edge> edgeList = node.get().getEdges();

        String objectNodeName = object.getNodeName();
        String objectIpAddress = object.getIpAddress();
        String objectPassword = object.getPassword();
        int objectXPosition = object.getX();
        int objectYPosition = object.getY();
        NodeType objectNodeType = object.getNodeType();
        List<Edge> objectEdgeList = object.getEdges();

        if(!nodeName.equals(objectNodeName)){
            node.get().setNodeName(objectNodeName);
        }
        else if(!ipAddress.equals(objectIpAddress)){
            node.get().setIpAddress(objectIpAddress);
        }
        else if(!password.equals(objectPassword)){
            node.get().setPassword(objectPassword);
        }
        else if(xPosition!=objectXPosition){
            node.get().setX(objectXPosition);
        }
        else if(yPosition!=objectYPosition){
            node.get().setY(objectYPosition);
        }
        else if(!nodeType.equals(objectNodeType)){
            node.get().setNodeType(objectNodeType);
        }
        else if(!edgeList.equals(objectEdgeList)){
           //Todo
        }
        return node.get();
    }

    public void deleteById(int id) {
        Optional<Node> node = nodeRepository.findById(id);
        if(node.isEmpty()){
            throw new EntityNotFoundException("Node doesn't exists");
        }
        nodeRepository.deleteById(id);
    }
}
