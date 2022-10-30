package ajou.gram.moim.controller;

import ajou.gram.moim.Service.MemberService;
import ajou.gram.moim.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    @ResponseBody
    public List<Member> getAllMember() {
        List<Member> members = memberService.findMembers();
        System.out.println(members.size());
        for (Member m : members) {
            System.out.println(m.getId());
        }
        return members;
    }
}
