package com.capgemini.kpsbackend.entities.node;

import java.util.HashMap;
import java.util.Map;

public enum NodeType {
    ADD_DROP(16),OLA(2),PASS_THROUGH(2);

    private final int noOfEdges;

    NodeType(int noOfEdges) {
        this.noOfEdges=noOfEdges;
    }

    public int getNoOfEdges(){
       return noOfEdges;
    }
}
