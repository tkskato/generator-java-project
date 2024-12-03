package dev.aulait.svqk.domain.issue;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public class IssueStatusRepository extends JpaRepository<IssueStatusEntity, Integer> {}
