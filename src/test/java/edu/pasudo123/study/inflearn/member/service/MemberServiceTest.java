package edu.pasudo123.study.inflearn.member.service;

import edu.pasudo123.study.inflearn.member.model.Member;
import edu.pasudo123.study.inflearn.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Profile("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@DisplayName("회원서비스는")
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입을 수행한다.")
    public void 회원가입_테스트() throws Exception {
        // given
        Member member = Member.builder()
                .name("PARK SUNG DONG")
                .build();

        // when
        Long savedId = memberService.join(member);
        Member foundMember = memberRepository.findById(savedId);

        // then
        assertThat(savedId).isSameAs(foundMember.getId());
    }
}