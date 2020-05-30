package com.youngplussoft.sbdemo.jpa.repository;

import com.youngplussoft.sbdemo.jpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "jpaTransactionManager")
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Modifying
    @Query(value="update member set bol = bol + :rewardBol where seq_no = :memberSeqNo")
    void updateBol(@Param("memberSeqNo") Long memberSeqNo, @Param("rewardBol") Long rewardBol) ;

}