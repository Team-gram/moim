package ajou.gram.moim.controller;

import ajou.gram.moim.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/guide-mvc")
    public String guideMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "guide/mvc";
    }

    @GetMapping("/guide-api")
    @ResponseBody
    public Member guideApi(@RequestParam("name") String name) {
        Member empty = new Member();
        empty.setName(name);
        return empty;
    }

}
