package com.example.test.service;

import com.example.test.beans.UserValueBean;
import com.example.test.model.UserEntity;
import com.example.test.repository.UserRepository;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserValueBean create (UserValueBean userValueBean){

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userValueBean.getUsername());
        userEntity.setLastName(userValueBean.getLastname());

        UserEntity save = userRepository.save(userEntity);
        UserValueBean returnBean = new UserValueBean(save.getId(), save.getFirstName(),save.getLastName());
        return returnBean;
    }

    public List<UserValueBean> findAll (){
        List<UserEntity> all = userRepository.findAll();
        List<UserValueBean> retlist = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            UserEntity userEntity = all.get(i);
            UserValueBean bean = new UserValueBean(userEntity.getId(),userEntity.getFirstName(), userEntity.getLastName());
            retlist.add(bean);
        }
        return retlist;
    }

    public UserValueBean find (int id){
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            UserValueBean bean = new UserValueBean(userEntity.get().getId(), userEntity.get().getFirstName(), userEntity.get().getLastName());
            return bean;
        }
        else return null;
    }
}
