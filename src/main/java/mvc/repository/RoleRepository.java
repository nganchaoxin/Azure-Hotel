/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.repository;


import mvc.entity.RoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

    Set<RoleEntity> findByUsers_Email(String email);

    @Query(value = "Select * from role where role.role = 'ROLE_USER'", nativeQuery = true)
    RoleEntity getRoleUser();
}
