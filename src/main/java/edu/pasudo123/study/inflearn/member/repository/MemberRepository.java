package edu.pasudo123.study.inflearn.member.repository;

import edu.pasudo123.study.inflearn.member.model.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // JPQL : 엔티티 대상으로 쿼리를 진행한다.
        // SQL : 테이블을 대상으로 쿼리를 진행한다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(final String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
