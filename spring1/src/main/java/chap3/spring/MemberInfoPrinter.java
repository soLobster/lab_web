package chap3.spring;

public class MemberInfoPrinter {

    private MemberDao memDao;
    private MemberPrinter printer;
    
    public void printMemberInfo(String email) {
        Member member = memDao.selectByEmail(email);
        if(member == null) {
            System.out.println("No Data Exist...\n");
            return;
        }
        printer.print(member);
        System.out.println();
    }
    
    public void setMemberDao(MemberDao memberDao) {
        this.memDao = memberDao;
    }
    
    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }
    
}
