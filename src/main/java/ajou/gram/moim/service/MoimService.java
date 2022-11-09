package ajou.gram.moim.service;

import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.repository.MoimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimService {

    private final MoimRepository moimRepository;

    public List<Moim> getMoims(int categoryId) {
        return moimRepository.findByCategoryId(categoryId);
    }

    public Optional<Moim> getMoim(long id) {
        return moimRepository.findById(id);
    }

    public void addMoim(Moim moim) {
        moim.setCreateDate(new Date());
        moim.setMoimLevel((short) 0);
        moimRepository.save(moim);
    }

    public List<Moim> getMoimsByTitle(String title) {
        return moimRepository.findByTitleLike("%" + title + "%");
    }
}
