package ajou.gram.moim.controller;

import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.domain.MoimMember;
import ajou.gram.moim.dto.JoinMoimDto;
import ajou.gram.moim.service.MoimService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moim")
@RequiredArgsConstructor
public class MoimController {

    private final MoimService moimService;

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

    @PostMapping("free")
    public void moimJoin(@RequestBody MoimMember moimMember) {
        moimService.moimJoin(moimMember);
    }

    @PostMapping("pass")
    public void moimJoinMessage(@RequestBody JoinMoimDto joinMoimDto) {
        moimService.moimJoinMessage(joinMoimDto);
    }
}
