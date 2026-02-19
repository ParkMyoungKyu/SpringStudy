package com.hello;

import com.hello.member.Grade;
import com.hello.member.Member;
import com.hello.member.MemberService;
import com.hello.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        // CASE 01.
//        MemberService memberService = new MemberServiceImpl();

        // CASE 02.
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // CASE 03.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean(MemberService.class);


        Member member = new Member("member_name",1L, Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(member.getId());
        System.out.println("new member  : " + member.getName());
        System.out.println("find member : " + findMember.getName());
    }
}
