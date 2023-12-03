package chap3.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap3.assembler.Assembler;
import chap3.spring.ChangePasswordService;
import chap3.spring.DuplicateMemberException;
import chap3.spring.MemberInfoPrinter;
import chap3.spring.MemberListPrinter;
import chap3.spring.MemberNotFoundException;
import chap3.spring.MemberRegisterService;
import chap3.spring.RegisterRequest;
import chap3.spring.VersionPrinter;
import chap3.spring.WrongIdPasswordException;
import config.AppCtx;

public class MainForSpring {

    private static ApplicationContext ctx = null;
    
    public static void main(String[] args) throws IOException {

        ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Enter Command >> ");
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Quit Program");
                break;
            }

            if (command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            } else if (command.equals("list")) {
                processListCommand();
                continue;
            } else if (command.startsWith("info ")) {
                processInfoCommand(command.split(" "));
                continue;
            } else if (command.equals("version")) {
                VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
                versionPrinter.print();
            }
            printHelp();
        }
    }

    private static void processNewCommand(String[] arg) {
        if (arg.length != 5) {
            printHelp();
            return;
        }

        MemberRegisterService regSvc = ctx.getBean("memberRegSvc" , MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("Not Equal Password to Confirm_Password...\n");
            return;
        }

        try {
            regSvc.regist(req);
            System.out.println("Success Regist...! \n");
        } catch (DuplicateMemberException e) {
            System.out.println("Duplicate Email.... \n");
        }
    }

    private static void processChangeCommand(String[] arg) {
        if (arg.length != 4) {
            printHelp();
            return;
        }
        ChangePasswordService changePasswordService = 
                ctx.getBean("changePwdSvc", ChangePasswordService.class);
        try {
            changePasswordService.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("Change Password Done...! \n");
        } catch (MemberNotFoundException e) {
            System.out.println("Not Exist Member Email \n");
        } catch (WrongIdPasswordException e) {
            System.out.println("Not Equal Email and Password \n");
        }
    }

    private static void printHelp() {
        System.out.println();
        System.out.println("Wrong Command Please Check Command Manual");
        System.out.println("Command Manual");
        System.out.println("New Email name password Confirmed_Password");
        System.out.println("Change Email Current_Password New_Password");
        System.out.println();
    }
    
    private static void processListCommand() {
        MemberListPrinter listPrinter = 
                ctx.getBean("listPrinter", MemberListPrinter.class);
        listPrinter.printAll();
    }
 
    private static void processInfoCommand(String[] arg) {
        if(arg.length != 2) {
            printHelp();
            return;
        }
        MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter" , MemberInfoPrinter.class);
        
        infoPrinter.printMemberInfo(arg[1]);
    }
    
}
