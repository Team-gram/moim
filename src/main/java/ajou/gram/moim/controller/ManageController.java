package ajou.gram.moim.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage")
@RequiredArgsConstructor
public class ManageController {

    @GetMapping("")
    public String main() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest httpServletRequest, HttpSession httpSession) {
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        if (email.equals("admin@admin.com") && password.equals("admin1234")) {
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
}
