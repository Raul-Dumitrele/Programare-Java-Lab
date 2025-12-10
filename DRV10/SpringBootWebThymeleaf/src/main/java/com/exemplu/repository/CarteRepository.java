package com.exemplu.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplu.entity.Carte;
public interface CarteRepository extends JpaRepository<Carte, Integer>{}