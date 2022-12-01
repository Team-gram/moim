package ajou.gram.moim.service;

import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.domain.MoimUpper;
import ajou.gram.moim.domain.MoimUpperHistory;
import ajou.gram.moim.dto.MoimUpperDto;
import ajou.gram.moim.dto.MoimUpperPrintDto;
import ajou.gram.moim.repository.MoimRepository;
import ajou.gram.moim.repository.MoimUpperHistoryRepository;
import ajou.gram.moim.repository.MoimUpperRepository;
import ajou.gram.moim.repository.MoimUpperRepositoryQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimUpperService {

    private final MoimRepository moimRepository;
    private final MoimUpperRepository moimUpperRepository;
    private final MoimUpperRepositoryQuery moimUpperRepositoryQuery;
    private final MoimUpperHistoryRepository moimUpperHistoryRepository;
    public void addUpperMoim(MoimUpperDto moimUpperDto) {
        MoimUpper moimUpper = MoimUpper.builder()
                .moimId(moimUpperDto.getMoimId())
                .period(moimUpperDto.getPeriod())
                .money(moimUpperDto.getMoney())
                .recStartDate(LocalDateTime.now())
                .recEndDate(LocalDateTime.now().plusDays(moimUpperDto.getPeriod()))
                .build();

        moimUpperRepository.save(moimUpper);
    }

    public void addUpperMoimHistory(MoimUpperDto moimUpperDto) {
        MoimUpperHistory moimUpperHistory = MoimUpperHistory.builder()
                .moimId(moimUpperDto.getMoimId())
                .build();

        moimUpperHistoryRepository.save(moimUpperHistory);
    }

    public List<MoimUpperPrintDto> getUpperMoims(Integer categoryId) throws SQLException {
        return moimUpperRepositoryQuery.getUpperMoims(categoryId);
    }

    public boolean checkLimitUpperMoim(MoimUpperDto moimUpperDto) throws SQLException {
        int count = moimUpperRepositoryQuery.checkLimitUpperMoim(moimUpperDto);
        return count >= 10 ? false : true;
    }

    public Optional<MoimUpper> getUpperMoimStatus(long moimId) {
        return moimUpperRepository.findByMoimId(moimId);
    }
}
