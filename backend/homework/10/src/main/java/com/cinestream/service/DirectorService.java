package com.cinestream.service;

import com.cinestream.entity.DirectorEntity;
import com.cinestream.repository.DirectorJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {

    private final DirectorJpaRepository repository;

    public DirectorService(DirectorJpaRepository repository) {
        this.repository = repository;
    }

    public DirectorEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
