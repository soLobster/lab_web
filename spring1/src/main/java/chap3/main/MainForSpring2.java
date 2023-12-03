package chap3.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap3.spring.DuplicateMemberException;
import chap3.spring.MemberRegisterService;
import chap3.spring.RegisterRequest;
import config.AppConf1;
import config.AppConf2;

public class MainForSpring2 {

    private static ApplicationContext ctx;

    public static void main(String[] args) throws IOException {

        ctx = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Enter the Command >> ");
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("exit the program...");
                break;
            }
            if (command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            }
        }

    }

    private static void processNewCommand(String[] arg) {
        if (arg.length != 5) {
            printHelp();
            return;
        }
        MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다.\n");
            return;
        }
        try {
            regSvc.regist(req);
            System.out.println("Regist Success...!!! \n");
        } catch (DuplicateMemberException e) {
            System.out.println("Exist Email...try Another Email...\n");
        }
    }

    private static void printHelp() {
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법:");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println();
    }

}
