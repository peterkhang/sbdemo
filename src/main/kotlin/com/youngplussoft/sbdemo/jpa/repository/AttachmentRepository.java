package com.youngplussoft.sbdemo.jpa.repository;

import com.youngplussoft.sbdemo.jpa.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(transactionManager = "jpaTransactionManager")
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    Attachment findBySeqNo(Long seqNo) ;
    Attachment findById(String id) ;
}