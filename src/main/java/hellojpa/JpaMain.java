package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //영속 엔티티 조회
            Member memberA = em.find(Member.class, 150L);
            memberA.setName("AAA");

            //준영속 엔티티 전환
            //detach: 단일 객체만 준영속으로 전환
            em.detach(memberA);
            //EntityManager를 통째로 준영속으로 전환
            //즉, 1차캐시를 다 지운다.
            em.clear();
            //영속성 컨텍스트를 닫는다.
            em.close();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
