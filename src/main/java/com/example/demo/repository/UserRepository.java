package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;
public interface UserRepository extends JpaRepository<User, Integer> {
    // 名前とパスワードを指定してユーザー情報を取得する
    List<User> findByNameAndPassword(String name, String password);
}
