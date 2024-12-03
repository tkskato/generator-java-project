package dev.aulait.svqk.domain.hello;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public class HelloRepository extends JpaRepository<HelloEntity, Integer> {}
