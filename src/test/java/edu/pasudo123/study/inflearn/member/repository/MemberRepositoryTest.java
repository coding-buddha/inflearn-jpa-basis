package edu.pasudo123.study.inflearn.member.repository;

import edu.pasudo123.study.inflearn.member.model.Member;
import edu.pasudo123.study.inflearn.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Profile("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("멤버 레파지토리는")
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @DisplayName("테스트를 수행한다.")
    @Commit
    public void memberRepoTest() {
        // given
        Member member = new Member();
        member.setUsername("PARK SUNG DONG");

        // when
        long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findById(saveId);

        // then
        assertThat(findMember.getId()).isSameAs(saveId);
    }
}