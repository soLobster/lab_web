package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap3.spring.ChangePasswordService;
import chap3.spring.MemberDao;
import chap3.spring.MemberInfoPrinter;
import chap3.spring.MemberListPrinter;
import chap3.spring.MemberPrinter;
import chap3.spring.MemberRegisterService;
import chap3.spring.VersionPrinter;

@Configuration
public class AppConf2 {

    @Autowired
    private MemberDao memberdao;
    @Autowired
    private MemberPrinter memberPrinter;
    
    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService(memberdao);
    }
    
    @Bean
    public ChangePasswordService changePwdSvc() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberdao);
        return pwdSvc;
    }
    
    @Bean
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter(memberdao, memberPrinter);
    }
    
    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberdao);
        return infoPrinter;
    }
    
    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}
