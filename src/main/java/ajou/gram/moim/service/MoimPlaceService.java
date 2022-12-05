package ajou.gram.moim.service;

import ajou.gram.moim.domain.MoimPlace;
import ajou.gram.moim.domain.MoimPlaceHistory;
import ajou.gram.moim.dto.MoimPlaceDto;
import ajou.gram.moim.dto.PlaceHistoryDto;
import ajou.gram.moim.dto.RecommendPlaceDto;
import ajou.gram.moim.repository.MoimPlaceHistoryRepository;
import ajou.gram.moim.repository.MoimPlaceHistoryRepositoryQuery;
import ajou.gram.moim.repository.MoimPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimPlaceService {

    private final MoimPlaceRepository moimPlaceRepository;
    private final MoimPlaceHistoryRepository moimPlaceHistoryRepository;
    private final MoimPlaceHistoryRepositoryQuery moimPlaceHistoryRepositoryQuery;
    public void addPlaceHistory(PlaceHistoryDto placeHistoryDto) {
        MoimPlaceHistory moimPlaceHistory = new MoimPlaceHistory();
        moimPlaceHistory.setAddressId(placeHistoryDto.getAddressId());
        moimPlaceHistory.setPlaceName(placeHistoryDto.getPlaceName());
        moimPlaceHistory.setCategoryGroupName(placeHistoryDto.getCategoryGroupName());
        moimPlaceHistory.setSido(placeHistoryDto.getSido());
        moimPlaceHistory.setSigungu(placeHistoryDto.getSigungu());
        moimPlaceHistory.setDong(placeHistoryDto.getDong());
        moimPlaceHistory.setCreatedAt(new Date());
        moimPlaceHistoryRepository.save(moimPlaceHistory);
    }

    public List<RecommendPlaceDto> getRecommendPlaces(String sido, String sigungu) throws SQLException {
        return moimPlaceHistoryRepositoryQuery.getRecommendPlaces(sido, sigungu);
    }

    public void addMoimPlace(MoimPlaceDto moimPlaceDto) {
        MoimPlace moimPlace = MoimPlace.builder()
                .moimId(moimPlaceDto.getMoimId())
                .scheduleId(moimPlaceDto.getScheduleId())
                .addressId(moimPlaceDto.getAddressId())
                .placeName(moimPlaceDto.getPlaceName())
                .sido(moimPlaceDto.getSido())
                .sigungu(moimPlaceDto.getSigungu())
                .dong(moimPlaceDto.getDong())
                .build();

        moimPlaceRepository.save(moimPlace);
    }

    public List<MoimPlace> getMoimPlaces(long moimId, long scheduleId) {
        return moimPlaceRepository.findByMoimIdAndScheduleId(moimId, scheduleId);
    }

    public void deleteMoimPlace(long placeId) {
        moimPlaceRepository.deleteById(placeId);
    }
}
