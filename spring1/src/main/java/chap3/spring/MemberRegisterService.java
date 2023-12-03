package chap3.spring;

import java.time.LocalDateTime;

public class MemberRegisterService {

    private MemberDao memberDao;

    // MemberRegisterService 클래스에 MemberDao를 주입한다.
    // 생성자를 통해서 의존 객체를 주입 받아 필드에 할당. (this.memberDao)
    public MemberRegisterService(MemberDao memberDao) {
        // 주입받은 객체를 필드에 할당
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail());
        // 주입 받은 의존 객체의 메서드를 사용
        if (member != null) { // getEmail을 통해 회원을 비교한다. 
            // null이 아니라면 duplication Email Exception을 던진다.
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }
        // Member Model Class와 RegisterRequest DTO의 Email을 비교해서 
        // duplicate Email이 아니라면 Member에 새로운 회원을 추가한다.
        Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
        memberDao.insert(newMember);
        return newMember.getId();
    }

}
