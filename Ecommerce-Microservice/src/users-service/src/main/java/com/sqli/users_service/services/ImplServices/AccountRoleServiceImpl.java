package com.sqli.users_service.services.ImplServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sqli.users_service.core.exceptions.DataAlreadyUsedException;
import com.sqli.users_service.core.exceptions.NoDataFoundException;
import com.sqli.users_service.data.entities.AccountRole;
import com.sqli.users_service.data.repositories.AccountRoleRepository;
import com.sqli.users_service.services.IServices.IAccountRoleService;

@Service
@Transactional
@CrossOrigin("*")
public class AccountRoleServiceImpl implements IAccountRoleService {
    private final AccountRoleRepository accountRoleRepository;

    public AccountRoleServiceImpl(AccountRoleRepository accountRoleRepository) {
        this.accountRoleRepository = accountRoleRepository;
    }

    @Override
    public AccountRole getRoleByName(String name) {
        return accountRoleRepository.findAccountRoleByName(name).orElseThrow(()->
            new NoDataFoundException("AccountRole named "+name+" does not exist")
        );
    }

    @Override
    public AccountRole getRoleById(Long idRole) throws NoDataFoundException {
        return accountRoleRepository.findById(idRole).orElseThrow(()->
            new NoDataFoundException("AccountRole identified "+idRole+" does not exist")
        );
    }

    @Override
    public List<AccountRole> getAllRoles() throws NoDataFoundException {
        return new ArrayList<>(accountRoleRepository.findAll());
    }

    @Override
    public AccountRole addAccountRole(AccountRole accountRole) throws DataAlreadyUsedException{
        if(accountRoleRepository.findAccountRoleByName(accountRole.getName()).isPresent())
            throw new DataAlreadyUsedException("AccountRole Name already exist");
        return accountRoleRepository.save(accountRole);
    }

    @Override
    public AccountRole updateRole(long idRole, AccountRole role) throws NoDataFoundException {
        if(!accountRoleRepository.findById(idRole).isPresent())
            throw new NoDataFoundException("No AccountRole was identified by "+idRole);
        role.setId(idRole);
        return accountRoleRepository.save(role);
    }

    @Override
    public void deleteRole(Long idRole) throws NoDataFoundException {
        Optional<AccountRole> accountRole=accountRoleRepository.findById(idRole);
        accountRoleRepository.delete(accountRole
            .orElseThrow(() -> new NoDataFoundException("No Role was identified by "+idRole)));
    }

}
