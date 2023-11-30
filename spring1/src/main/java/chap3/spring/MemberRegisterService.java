package chap3.spring;

public class MemberRegisterService {

    private MemberDao memberDao;
    
    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
//    public Long regist(RegisterRequest req) {
//        
//    }
    
}
