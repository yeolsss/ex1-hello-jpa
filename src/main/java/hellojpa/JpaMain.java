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

            //비영속 상태
            Member member = new Member();
            member.setId(101L);
            member.setName("helloJPA");
            //비영속 상태 끝

            //영속 상태
            // em.persist를 한다고 바로 db에 query를 날리지 않는다.
            System.out.println("=== BEFORE === ");
            //1차 캐시에 저장
            em.persist(member);
            System.out.println("=== AFTER ===");

            //1차 캐시에서 조회
            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
