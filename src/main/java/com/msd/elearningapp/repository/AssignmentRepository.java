package com.msd.elearningapp.repository;

import com.msd.elearningapp.domain.Assignment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

//@Transactional(SUPPORTS)
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

	/*assigState
    @PersistenceContext
    private EntityManager em;

    public Assignment find(@NotNull Long id) {
        return em.find(Assignment.class, id);
    }

    @Transactional(REQUIRED)
    public Assignment create(@NotNull @Min(10) Assignment assignment) {
        em.persist(assignment);
        return assignment;
    }

    @Transactional(REQUIRED)
    public void delete(@NotNull @Min(1) @Max(1000) Long id){
        em.remove(em.getReference(Assignment.class, id));
    }

    public List<Assignment> findAll() {
        TypedQuery<Assignment> query = em.createQuery("select a from Assignment a order by a.assigDatestart desc", Assignment.class);
        return query.getResultList();
    }
    */
}
