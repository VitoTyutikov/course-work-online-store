package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.CartEntity;
import com.vitaly.onlineStore.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartService) {
        this.cartRepository = cartService;
    }
    public List<CartEntity> findAll(){
        return cartRepository.findAll();
    }
    public CartEntity findById(Integer id){
        return cartRepository.findById(id).orElseThrow();
    }
    public CartEntity save(CartEntity cartEntity){
        return cartRepository.save(cartEntity);
    }
    public List<CartEntity> findByClientId(Integer clientId){
        return cartRepository.findByClientId(clientId);
    }
    public void deleteById(Integer id){
        cartRepository.deleteById(id);
    }
    public Optional<CartEntity> findByClientIdAndProductId(Integer clientId, Integer productId){
        return cartRepository.findByClientIdAndProductId(clientId,productId);
    }
    public void delete(CartEntity cartEntity){
        cartRepository.delete(cartEntity);
    }
    public void deleteAll(List<CartEntity> cartEntities){
        cartRepository.deleteAll(cartEntities);
    }

}
