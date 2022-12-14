package com.capgemini.link.repository;

import com.capgemini.link.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LinkRepository  extends JpaRepository<Link,Integer> {

    Optional<Link> findByLabel(String label);

}
