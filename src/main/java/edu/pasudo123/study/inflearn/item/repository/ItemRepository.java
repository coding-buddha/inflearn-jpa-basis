package edu.pasudo123.study.inflearn.item.repository;

import edu.pasudo123.study.inflearn.item.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(final Item item) {
        if(item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }

        return item.getId();
    }

    public Item findById(final Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
