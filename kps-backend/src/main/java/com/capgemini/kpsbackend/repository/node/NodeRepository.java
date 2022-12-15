package com.capgemini.kpsbackend.repository.node;

import com.capgemini.kpsbackend.entities.node.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node,Integer> {
}
