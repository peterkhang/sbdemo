package com.youngplussoft.sbdemo.jpa.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*
import javax.persistence.*

@Entity(name = "member") // This tells Hibernate to make a table out of this class
@Table(name = "member")
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_no")
    var seqNo //'회원 순번',
            : Long? = null

    @Column(name = "country_seq_no")
    var countrySeqNo: Long? = null //'국가 순번',

    @Column(name = "member_name")
    var memberName: String? = null //'이름',

    @Column(name = "account_type")
    var accountType //'계정 타입',
            : String? = null

    @Column(name = "login_id")
    var loginId //'로그인 ID',
            : String? = null

    @JsonIgnore
    @Column(name = "password")
    var password //'로그인 암호',
            : String? = null

    @Column(name = "member_type")
    var memberType //'회원 타입',
            : String? = null

    @Column(name = "use_status")
    var useStatus //'사용 상태',
            : String? = null

    @Column(name = "restriction_status")
    var restrictionStatus //'제제 상태',
            : String? = null

    @Column(name = "restriction_clear_datetime")
    var restrictionClearDatetime //
            : String? = null

    @Column(name = "nickname")
    var nickname: String? = null // COMMENT '별명',

    @Column(name = "mobile_number")
    var mobile_number: String? = null //'휴대폰 번호',

    @Column(name = "email")
    var email: String? = null //'이메일',

    @JsonIgnore
    @Column(name = "zip_code")
    var zipCode: String? = null //'우편번호',

    @JsonIgnore
    @Column(name = "base_address")
    var baseAddress: String? = null //'기본 주소',

    @JsonIgnore
    @Column(name = "join_datetime")
    var joinDatetime // '가입 일시',
            : Date? = null

    @JsonIgnore
    @Column(name = "join_platform")
    var joinPlatform //'가입 플랫폼',
            : String? = null

    @JsonIgnore
    @Column(name = "verification_media")
    var verificationMedia // '인증 수단',
            : String? = null

    @JsonIgnore
    @Column(name = "recommendation_code")
    var recommendationCode // '추천인 코드',
            : String? = null

    @JsonIgnore
    @Column(name = "gender")
    var gender: String? = null //  '성별',

    @JsonIgnore
    @Column(name = "married")
    var married: Boolean? = null //'기혼 여부',

    @JsonIgnore
    @Column(name = "child")
    var child: Boolean? = null //'자녀 있음 여부',

    @JsonIgnore
    @Column(name = "birthday")
    var birthday: String? = null //'생년월일',

    @JsonIgnore
    @Column(name = "job")
    var job: String? = null //'직업',

    @JsonIgnore
    @Column(name = "talk_receive_bounds")
    var talkReceiveBounds // '채팅 수신 범위',
            : String? = null

    @JsonIgnore
    @Column(name = "talk_deny_day")
    var talkDenyDay: String? = null //'채팅 거부 요일',

    @JsonIgnore
    @Column(name = "talk_deny_start_time")
    var talkDenyStartTime: String? = null // '채팅 거부 시작 시간',

    @JsonIgnore
    @Column(name = "talk_deny_end_time")
    var talkDenyEndTime: String? = null //'채팅 거부 종료 시간',

    @JsonIgnore
    @Column(name = "talk_receive")
    var talkReceive //'채팅 수신 여부',
            : Boolean? = null

    @JsonIgnore
    @Column(name = "talk_push_receive")
    var talkPushReceive // '채팅 푸쉬 수신 여부',
            : Boolean? = null

    @JsonIgnore
    @Column(name = "last_login_datetime")
    var lastLoginDatetime: String? = null // '최종 로그인 일시',

    @JsonIgnore
    @Column(name = "login_fail_count")
    var loginFailCount: Int? = null //'로그인 실패 횟수',

    @JsonIgnore
    @Column(name = "last_login_fail_datetime")
    var lastLoginFailDatetime: String? = null //'마지막 로그인 실패 일시',

    @JsonIgnore
    @Column(name = "contact_list_version")
    var contactListVersion: Long? = null //'연락처 버전',

    @JsonIgnore
    @Column(name = "leave_request_datetime")
    var leaveRequestDatetime: String? = null // '탈퇴 신청 시간',

    @JsonIgnore
    @Column(name = "leave_finish_datetime")
    var leaveFinishDatetime: String? = null // '탈퇴 완료 시간',

    @JsonIgnore
    @Column(name = "calculated")
    var calculated: Boolean? = null // '정산 마감 여부',

    @JsonIgnore
    @Column(name = "calculated_month")
    var calculatedMonth: String? = null //'정산 월',

    @Column(name = "reg_type")
    var regType //'등록 타입',
            : String? = null

    @Column(name = "sendbird_user")
    var sendbirdUser: Boolean? = null // '샌드버드 사용자 여부',

    @Column(name = "cash")
    var cash: Long? = null // '보유 캐쉬',

    @Column(name = "bol")
    var bol: Long? = null // '보유 BOL',

    @JsonIgnore
    @Column(name = "mod_datetime")
    var modDatetime: String? = null // '수정 일시',

    @JsonIgnore
    @Column(name = "modifier_seq_no")
    var modifierSeqNo: Long? = null //'수정자 순번',

    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "profile_seq_no")
    var profileAttachment: Attachment? = null // '프로필 이미지',

    @JsonIgnore
    @Column(name = "member_prop", columnDefinition = "TEXT")
    var memberProp: String? = null //'추가 속성',

    @JsonIgnore
    @Column(name = "recommend_unique_key")
    var recommendUniqueKey: String? = null //'추천 식별 코드',

    @Column(name = "certification_level")
    var certificationLevel: Short? = null // '인증 레벨',

    @Column(name = "lotto_ticket_count")
    var lottoTicketCount: Int? = null

    @Column(name = "lotto_default_ticket_count")
    var lottoDefaultTicketCount // 매주 참여가능한 기본 로또 응모권 사용 유무
            : Int? = null
}