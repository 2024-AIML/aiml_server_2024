package com.Member.aiml_server_2024;

import com.Member.aiml_server_2024.shelterlocation.Location;
import com.Member.aiml_server_2024.shelterlocation.UserService;
import com.google.firebase.database.annotations.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entitymanager-example/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public String create(@RequestParam String name, int age) {
        Location user = new Location();
        user.setName(name);
        user.setAge(age);

        Long userId = userService.save(user);
        return userId + "번 유저 등록 완료";
    }

    @PutMapping("")
    public String update(@RequestParam Long id, String name, int age) {
        Long userId = userService.update(id, name, age);
        return userId + "번 유저 수정 완료";
    }

    @DeleteMapping("")
    public String delete(@RequestParam Long id) {
        userService.remove(id);
        return id + "번 유저 삭제 완료";
    }

    @GetMapping("")
    public String read(@RequestParam @Nullable Long id, String name) {
        if(id != null) {
            return userService.findById(id).toString();
        } else {
            return userService.findByName(name).toString();
        }
    }

    @GetMapping("/all")
    public String readAll() {
        return userService.findAll().toString();
    }
}
