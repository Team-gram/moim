package ajou.gram.moim.service;

import ajou.gram.moim.domain.*;
import ajou.gram.moim.dto.MoimPlaceUpperDto;
import ajou.gram.moim.dto.MoimUpperDto;
import ajou.gram.moim.dto.MoimUpperPrintDto;
import ajou.gram.moim.dto.PlaceUpperHistoryDto;
import ajou.gram.moim.repository.*;
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

    private final AwsS3Service awsS3Service;
    private final MoimRepository moimRepository;
    private final MoimUpperRepository moimUpperRepository;
    private final MoimUpperRepositoryQuery moimUpperRepositoryQuery;
    private final MoimUpperHistoryRepository moimUpperHistoryRepository;
    private final MoimPlaceUpperRepository moimPlaceUpperRepository;
    private final MoimPlaceUpperRepositoryQuery moimPlaceUpperRepositoryQuery;
    private final MoimPlaceUpperHistoryRepository moimPlaceUpperHistoryRepository;
    public void addUpperMoim(MoimUpperDto moimUpperDto) {
        MoimUpper moimUpper = MoimUpper.builder()
                .moimId(moimUpperDto.getMoimId())
                .period(moimUpperDto.getPeriod())
                .money(moimUpperDto.getMoney())
                .recStartDate(LocalDateTime.now())
                .recEndDate(LocalDateTime.now().plusDays(moimUpperDto.getPeriod()))
                .build();

        moimUpperRepository.save(moimUpper);

        MoimUpperHistory moimUpperHistory = MoimUpperHistory.builder()
                .moimId(moimUpper.getMoimId())
                .build();

        moimUpperHistoryRepository.save(moimUpperHistory);
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
        return count >= 20 ? false : true;
    }

    public Optional<MoimUpper> getUpperMoimStatus(long moimId) {
        return moimUpperRepository.findByMoimId(moimId);
    }

    public void addUpperPlace(MoimPlaceUpperDto moimPlaceUpperDto) {
        String url = awsS3Service.uploadFileV1(moimPlaceUpperDto.getCrn());

        MoimPlaceUpper moimPlaceUpper = MoimPlaceUpper.builder()
                .userId(moimPlaceUpperDto.getUserId())
                .placeId(moimPlaceUpperDto.getPlaceId())
                .placeName(moimPlaceUpperDto.getPlaceName())
                .categoryId(moimPlaceUpperDto.getCategoryId())
                .page(moimPlaceUpperDto.getPage())
                .crn(url)
                .sido(moimPlaceUpperDto.getSido())
                .sigungu(moimPlaceUpperDto.getSigungu())
                .dong(moimPlaceUpperDto.getDong())
                .period(moimPlaceUpperDto.getPeriod())
                .money(moimPlaceUpperDto.getMoney())
                .createDate(LocalDateTime.now())
                .status("-")
                .build();

        moimPlaceUpperRepository.save(moimPlaceUpper);
    }

    public List<MoimPlaceUpper> getUpperPlaceStatus(long userId) {
        return moimPlaceUpperRepository.findByUserId(userId);
    }

    public List<MoimPlaceUpper> getAllUpperMoimPlace() {
        return moimPlaceUpperRepository.findAll();
    }

    public void addUpperPlaceHistory(PlaceUpperHistoryDto placeUpperHistoryDto) {
        MoimPlaceUpperHistory moimPlaceUpperHistory = MoimPlaceUpperHistory.builder()
                .placeUpperId(placeUpperHistoryDto.getPlaceUpperId())
                .build();

        moimPlaceUpperHistoryRepository.save(moimPlaceUpperHistory);
    }

    public void upperAccept(long id) {
        moimPlaceUpperRepository.findById(id).ifPresent(m -> {
            m.setRecStartDate(LocalDateTime.now());
            m.setRecEndDate(LocalDateTime.now().plusDays(m.getPeriod()));
            m.setStatus("Y");
            moimPlaceUpperRepository.save(m);
            MoimPlaceUpperHistory moimPlaceUpperHistory = MoimPlaceUpperHistory.builder()
                    .placeUpperId(id)
                    .build();
            moimPlaceUpperHistoryRepository.save(moimPlaceUpperHistory);
        });
    }

    public void upperReject(long id) {
        moimPlaceUpperRepository.findById(id).ifPresent(m -> {
            m.setStatus("N");
            moimPlaceUpperRepository.save(m);
        });
    }

    public List<MoimPlaceUpper> getUpperPlaces(long categoryId) throws SQLException {
        return moimPlaceUpperRepositoryQuery.findByCategoryId(categoryId);
    }
}
