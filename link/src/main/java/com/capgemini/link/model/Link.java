package com.capgemini.link.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @Id
    @SequenceGenerator(name = "link_sequence",sequenceName = "link_sequence")
    @GeneratedValue(strategy = SEQUENCE,generator = "link_sequence")
    private Integer id;
    private String label;
    private  String sourceNode;
    private String targetNode;
    private Integer length;
    private Integer noOfSpaces;
    @OneToMany(mappedBy = "link",cascade = CascadeType.ALL)
    private List<Space> spaces;

    public Link(String label, String sourceNode, String targetNode, Integer length, Integer noOfSpaces) {
        this.label = label;
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.length = length;
        this.noOfSpaces = noOfSpaces;
    }
}
