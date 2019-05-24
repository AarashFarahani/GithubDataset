package com.hackerrank.github.repository;

import com.hackerrank.github.model.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RepoRepository extends JpaRepository<Repo, Long> {
}
