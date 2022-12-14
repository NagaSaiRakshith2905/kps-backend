package com.capgemini.network.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    @Id
    @SequenceGenerator(name = "node_sequence",sequenceName = "node_sequence")
    @GeneratedValue(strategy = SEQUENCE,generator = "node_sequence")
    private Integer id;
    private Integer node_id;
    private String node_name;
}
