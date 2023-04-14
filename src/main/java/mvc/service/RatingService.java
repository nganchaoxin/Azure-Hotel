package mvc.service;

import mvc.entity.RatingEntity;
import mvc.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;

    public void save(RatingEntity rating) { ratingRepository.save(rating);
    }

    public List<RatingEntity> findAll() {return (List<RatingEntity>) ratingRepository.findAll();
    }
}
