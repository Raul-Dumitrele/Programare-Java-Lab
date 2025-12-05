package com.exemplu.repository;

import com.exemplu.entity.Masini;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

@Repository
@Transactional
public class MasiniJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Masini insert(Masini masini) {
        return entityManager.merge(masini);
    }

    public void deleteById(String nr_inmatriculare) {
        Masini m = findById(nr_inmatriculare);
        if (m != null) {
            entityManager.remove(m);
        }
    }

    public Masini findById(String nr_inmatriculare) {
        return entityManager.find(Masini.class, nr_inmatriculare);
    }

    public List<Masini> findAll() {
        return entityManager.createQuery("FROM Masini", Masini.class)
                .getResultList();
    }

    public long countByMarca(String marca) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(m) FROM Masini m WHERE m.marca = :marca", Long.class);
        query.setParameter("marca", marca);
        return query.getSingleResult();
    }

    public long countByKmUnder100k() {
        return entityManager.createQuery(
                "SELECT COUNT(m) FROM Masini m WHERE m.nr_kilometri < 100000", Long.class)
                .getSingleResult();
    }

    public List<Masini> findMasiniNoi5Ani() {
        int anulLimita = Year.now().getValue() - 5;
        TypedQuery<Masini> query = entityManager.createQuery(
                "FROM Masini m WHERE m.anul_fabricatiei > :an",
                Masini.class);
        query.setParameter("an", anulLimita);
        return query.getResultList();
    }
}
