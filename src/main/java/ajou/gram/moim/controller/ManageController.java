package ajou.gram.moim.controller;

import ajou.gram.moim.service.MoimUpperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage")
@RequiredArgsConstructor
public class ManageController {

    private final MoimUpperService moimUpperService;
    @GetMapping("")
    public String main(HttpSession httpSession) {
        if (httpSession.getAttribute("email") != null) {
            return "main";
        }
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest httpServletRequest, HttpSession httpSession) {
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        if (email.equals("admin@admin.com")) {
            httpSession.setAttribute("email", email);
            return "main";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("email");
        return "main";
    }

    @GetMapping("/upper")
    public ModelAndView getUpperMoim() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("places", moimUpperService.getAllUpperMoimPlace());
        modelAndView.setViewName("upper");
        return modelAndView;
    }
}
