package chap3.spring;

public class MemberPrinter {

    public void print(Member member) {
        System.out.printf("Member Info >> Id = %d, Email = %s, Name = %s, Register Date = %tF\n", 
                member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
    }
    
}
