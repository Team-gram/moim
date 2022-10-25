package ajou.gram.moim.controller;

import ajou.gram.moim.domain.EmptyClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/guide-mvc")
    public String guideMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "guide/mvc";
    }

    @GetMapping("/guide-api")
    @ResponseBody
    public EmptyClass guideApi(@RequestParam("name") String name) {
        EmptyClass empty = new EmptyClass();
        empty.setName(name);
        return empty;
    }

}
