package ajou.gram.moim.controller;

import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.service.MoimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moim")
public class MoimController {

    private MoimService moimService;

    @Autowired
    public MoimController(MoimService moimService) {
        this.moimService = moimService;
    }

    @GetMapping("/cate/{category_id}")
    public List<Moim> getMoims(@PathVariable("category_id") int categoryId) {
        return moimService.getMoims(categoryId);
    }

    @GetMapping("/{id}")
    public Optional<Moim> getMoim(@PathVariable("id") long id) {
        return moimService.getMoim(id);
    }

    @PostMapping({" ", "/"})
    public void addMoim(@RequestBody Moim moim) {
        moimService.addMoim(moim);
    }

    @GetMapping("title/{title}")
    public List<Moim> getMoimsByTitle(@PathVariable("title") String title) {
        return moimService.getMoimsByTitle(title);
    }
}
