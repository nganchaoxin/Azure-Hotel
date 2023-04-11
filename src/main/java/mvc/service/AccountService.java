package mvc.service;

import mvc.entity.AccountEntity;
import mvc.entity.RoleEntity;
import mvc.enums.UserStatus;
import mvc.repository.AccountRepository;
import mvc.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    public AccountEntity findById(int id) {
        return accountRepository.findById(id);
    }

    public AccountEntity getAccountByEmail(String email) {
        return accountRepository.findByEmailLikeAndStatusLike(email, UserStatus.ACTIVE);
    }

    public void save(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
    }

    public RoleEntity getRoleUser() {
        return roleRepository.getRoleUser();
    }

    public AccountEntity findByEmail(String userMail) {
        return accountRepository.findByEmail(userMail);
    }

    public AccountEntity findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public AccountEntity findByToken(String token) {
        return accountRepository.findByToken(token);
    }

    public List<AccountEntity> findAll(){
        return (List<AccountEntity>) accountRepository.findAll();
    }
}
