package ajou.gram.moim.controller;

import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.domain.MoimMember;
import ajou.gram.moim.dto.JoinMoimDto;
import ajou.gram.moim.service.MoimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moim")
@RequiredArgsConstructor
public class MoimController {

    private final MoimService moimService;

    @GetMapping({"", "/"})
    public List<Moim> getMoims(@RequestParam(value = "categoryId", required = false, defaultValue = "0") int categoryId,
                               @RequestParam(value = "sido", required = false) String sido,
                               @RequestParam(value = "sigungu", required = false) String sigungu,
                               @RequestParam(value = "dong", required = false) String dong,
                               @RequestParam(value = "title", required = false) String title) {
        return moimService.getMoims(categoryId, sido, sigungu, dong, title);
    }

    @GetMapping("/{id}")
    public Optional<Moim> getMoim(@PathVariable("id") long id) {
        return moimService.getMoim(id);
    }

    @PostMapping({" ", "/"})
    public void addMoim(@RequestBody Moim moim) {
        moimService.addMoim(moim);
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
