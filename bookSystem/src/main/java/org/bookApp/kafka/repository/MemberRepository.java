package org.bookApp.kafka.repository;

import org.bookApp.kafka.payload.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Repository-annotationen används för att markera detta gränssnitt som en Spring Data JPA-repositoriekomponent.
@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
}
