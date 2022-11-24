package ajou.gram.moim.service;

import ajou.gram.moim.domain.MoimPlaceHistory;
import ajou.gram.moim.dto.PlaceHistoryDto;
import ajou.gram.moim.repository.MoimPlaceHistoryRepository;
import ajou.gram.moim.repository.MoimPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimPlaceService {

    //private final MoimPlaceRepository moimPlaceRepository;
    private final MoimPlaceHistoryRepository moimPlaceHistoryRepository;
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
}
