package com.cinestream.repository;

import com.cinestream.entity.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorJpaRepository extends JpaRepository<DirectorEntity, Long> {}
