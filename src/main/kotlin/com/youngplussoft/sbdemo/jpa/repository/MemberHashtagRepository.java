package com.youngplussoft.sbdemo.jpa.repository;

import com.youngplussoft.sbdemo.jpa.model.MemberHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "jpaTransactionManager")
public interface MemberHashtagRepository extends JpaRepository<MemberHashtag, Long> {

    @Modifying
    public void deleteByMemberSeqNo(Long memberSeqNo) ;

    public MemberHashtag findByMemberSeqNo(Long memberSeqNo) ;
}