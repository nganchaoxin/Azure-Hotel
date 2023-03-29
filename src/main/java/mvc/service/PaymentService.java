package mvc.service;

import mvc.entity.PaymentEntity;
import mvc.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;


    public PaymentEntity findById(int id) {
        return paymentRepository.findById(id);
    }

    public void save(PaymentEntity newPayment) {
        paymentRepository.save(newPayment);
    }

    public List<PaymentEntity> findByAccountBankingId(int id) {
        return paymentRepository.findByAccountBankingId(id);
    }
}
