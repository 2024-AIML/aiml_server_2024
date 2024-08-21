package com.Member.aiml_server_2024.shelterlocation;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    // Create, Update
    public void save(Location user) {
        em.persist(user);
    }

    // Delete
    public void remove(Long id) {
        em.remove(findById(id));
    }

    // Read
    public Location findById(Long id) {
        return em.find(Location.class, id);
    }

    public Location findByName(String findName) {
        return em.createQuery("SELECT u FROM Location u WHERE u.name = :findName", Location.class)
                .setParameter("findName", findName)
                .getSingleResult();
    }

    public List<Location> findAll() {
        return em.createQuery("SELECT u FROM Location u", Location.class)
                .getResultList();
    }
}
