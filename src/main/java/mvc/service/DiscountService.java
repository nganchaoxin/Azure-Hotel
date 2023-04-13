package mvc.service;

import mvc.entity.DiscountEntity;
import mvc.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;

@Service
public class DiscountService {
    @Autowired
    DiscountRepository discountRepository;
    public List<DiscountEntity> findAllDiscount(){
        return discountRepository.findAll();
    }

    public DiscountEntity findById(int id){
        return discountRepository.findById(id);
    }
}
