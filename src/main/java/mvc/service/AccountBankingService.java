package mvc.service;

import mvc.entity.AccountBankingEntity;
import mvc.repository.AccountBankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountBankingService {
    @Autowired
    AccountBankingRepository accountBankingRepository;

    public AccountBankingEntity findById(int id) {
        return accountBankingRepository.findById(id);
    }


    public List<AccountBankingEntity> findByAccountId(int id) {
        return accountBankingRepository.findByAccountId(id);
    }

    public void save(AccountBankingEntity accountBankingEntity) {
        accountBankingRepository.save(accountBankingEntity);
    }

    public void reduceMoney(int accountID, double amount) {
        AccountBankingEntity accountBanking = accountBankingRepository.findByAccountId(accountID).get(0);
        double newBalance = accountBanking.getBalance() - amount;
        accountBanking.setBalance(newBalance);
        accountBankingRepository.save(accountBanking);
    }
}
