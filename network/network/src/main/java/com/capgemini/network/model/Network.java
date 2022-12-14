package com.capgemini.network.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Network {
    @Id
    @SequenceGenerator(name = "node_sequence",sequenceName = "node_sequence")
    @GeneratedValue(strategy = SEQUENCE,generator = "node_sequence")
    private Integer id;
    private Integer network_id;
    private String name;
    private List<Node> nodes;
    private List<Link> links;

    @OneToMany(mappedBy = "network", cascade = CascadeType.ALL)
    private List<Circuit> cirtcuits;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "link_id")
    private Link link;
}
