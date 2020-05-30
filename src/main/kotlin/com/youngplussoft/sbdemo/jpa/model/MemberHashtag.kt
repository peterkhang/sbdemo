package com.youngplussoft.sbdemo.jpa.model

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "memberHashtag") // This tells Hibernate to make a table out of this class
@Table(name = "member_hashtag")
@JsonInclude(JsonInclude.Include.NON_NULL)
class MemberHashtag {
    @Id
    @Column(name = "member_seq_no")
    var memberSeqNo: Long? = null

    @Column(name = "hashtag")
    var hashtag: String? = null
}