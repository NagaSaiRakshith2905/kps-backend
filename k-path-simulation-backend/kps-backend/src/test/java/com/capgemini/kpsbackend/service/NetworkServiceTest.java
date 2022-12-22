package com.capgemini.kpsbackend.service;

import com.capgemini.kpsbackend.dto.request.NetworkRequest;
import com.capgemini.kpsbackend.dto.request.link.LinkRequest;
import com.capgemini.kpsbackend.dto.request.node.NodeRequest;
import com.capgemini.kpsbackend.entities.link.Link;
import com.capgemini.kpsbackend.entities.node.Node;
import com.capgemini.kpsbackend.entities.node.NodeType;
import com.capgemini.kpsbackend.repository.NetworkRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
public class NetworkServiceTest {

    @InjectMocks
    private NetworkService networkService = new NetworkService();
    @InjectMocks
    private NodeService nodeService = new NodeService();
    @InjectMocks
    private LinkService linkService = new LinkService();

    @Mock
    private NetworkRepository networkRepository;

    private String networkName = "Network1";
    private String username = "User1";

    private String nodeName = "A";
    private String ipAddress = "192.168.1.1";
    private String password = "Password123";
    private Integer xPosition = 1;
    private Integer yPosition = 1;

    private String label = "Link1";
    private String fromNode = "A";
    private String toNode = "B";
    private Integer length = 10;
    private Integer weight = 8;
    private Integer noOfSpaces = 3;

    private NodeType nodeType = NodeType.ADD_DROP;


    List<NodeRequest> nodeRequest = List.of(NodeRequest.builder()
            .nodeName(nodeName)
            .ipAddress(ipAddress)
            .password(password)
            .xPosition(xPosition)
            .yPosition(yPosition)
            .nodeType("ADD_DROP")
            .build());
    List<Node> node = List.of(Node.builder()
                    .id(1)
            .nodeName(nodeName)
            .ipAddress(ipAddress)
            .password(password)
            .xPosition(xPosition)
            .yPosition(yPosition)
            .nodeType(nodeType)
            .build());

    List<LinkRequest> linkRequest =  List.of(LinkRequest.builder()
            .label(label)
            .fromNode(fromNode)
            .toNode(toNode)
            .length(length)
            .weight(weight)
            .noOfSpaces(noOfSpaces)
            .build());
    List<Link> link =  List.of(Link.builder()
                    .id(1)
            .label(label)
            .fromNode(fromNode)
            .toNode(toNode)
            .length(length)
            .weight(weight)
            .noOfSpaces(noOfSpaces)
            .build());

    NetworkRequest networkRequest = NetworkRequest.builder()
            .networkName(networkName)
            .username(username)
            .nodes(nodeRequest)
            .links(linkRequest)
            .build();

    @Test
    void add() {

    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}
