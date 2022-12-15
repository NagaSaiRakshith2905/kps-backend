package com.capgemini.kpsbackend.service;

import com.capgemini.kpsbackend.dto.request.node.EdgeRequest;
import com.capgemini.kpsbackend.dto.request.node.NodeRequest;
import com.capgemini.kpsbackend.entities.node.Edge;
import com.capgemini.kpsbackend.entities.node.Node;
import com.capgemini.kpsbackend.entities.node.NodeType;
import com.capgemini.kpsbackend.repository.node.EdgeRepository;
import com.capgemini.kpsbackend.repository.node.NodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NodeService implements ServiceInterface<NodeRequest, Node> {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private EdgeRepository edgeRepository;

    @Override
    public Node add(NodeRequest nodeRequest) {
        log.info("In node service");

        Node node = Node.builder()
                .nodeName(nodeRequest.getNodeName())
                .ipAddress(nodeRequest.getIpAddress())
                .password(nodeRequest.getPassword())
                .nodeType(mapToNodeType(nodeRequest.getNodeType()))
                .network(nodeRequest.getNetwork())
                .build();
        Node savedNode = nodeRepository.save(node);
        log.info(savedNode.toString());
        createEdges(node.getNodeType().getNoOfEdges(),savedNode);
        log.info("");
        log.info(savedNode.toString());
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

    @Override
    public List<Node> getAll() {
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public Object update(Node object) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
