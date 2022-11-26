package ajou.gram.moim.service;

import ajou.gram.moim.domain.MoimPlaceHistory;
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

    //private final MoimPlaceRepository moimPlaceRepository;
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

    public List<RecommendPlaceDto> getRecommendPlaces(String sido, String sigungu, String categoryGroupName) throws SQLException {
        return moimPlaceHistoryRepositoryQuery.getRecommendPlaces(sido, sigungu, categoryGroupName);
    }
}
