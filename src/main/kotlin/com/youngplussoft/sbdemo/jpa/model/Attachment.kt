package com.youngplussoft.sbdemo.jpa.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import javax.persistence.*

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "attachment")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_no")
    var seqNo // '첨부 순번',
            : Long? = null

    @Column(name = "id")
    var id // uuid
            : String? = null

    @JsonIgnore
    @Column(name = "ref_count")
    var refCount = 1

    @JsonIgnore
    @Column(name = "origin_name")
    var originName: String? = null

    @JsonIgnore
    @Column(name = "file_path")
    var filePath: String? = null

    @JsonIgnore
    @Column(name = "file_name")
    var fileName: String? = null

    @JsonIgnore
    @Column(name = "extension")
    var extension: String? = null

    @JsonIgnore
    @Column(name = "file_size")
    var fileSize: Long? = null

    @JsonIgnore
    @Column(name = "url")
    var url: String? = null

    @JsonIgnore
    @Column(name = "attachment_prop", columnDefinition = "TEXT")
    var attachmentProp: Map<String, Any>? = null

    @JsonIgnore
    @Column(name = "deleted")
    var deleted = false //`블라인드 여부',

    @Column(name = "target_type")
    var targetType: String? = null
}