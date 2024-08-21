package com.Member.aiml_server_2024.shelterlocation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 유저 등록
    public Long save(Location location) {
        userRepository.save(location);
        return location.getId();
    }

    // 유저 수정
    public Long update(Long id, String newName, int newAge) {
        Location updateUser = userRepository.findById(id);
        updateUser.setName(newName);
        updateUser.setAge(newAge);

        return updateUser.getId();
    }

    // 유저 삭제
    public void remove(Long id) {
        userRepository.remove(id);
    }

    // id로 유저 검색
    public Location findById(Long id) {
        return userRepository.findById(id);
    }

    // name으로 유저 검색
    public Location findByName(String name) {
        return userRepository.findByName(name);
    }

    // 유저 전체 검색
    public List<Location> findAll() {
        return userRepository.findAll();
    }
}
