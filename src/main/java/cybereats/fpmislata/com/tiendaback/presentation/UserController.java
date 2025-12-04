package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.presentation.mapper.UserMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.UserRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserResponse;
import cybereats.fpmislata.com.tiendaback.domain.service.UserService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        DtoValidator.validate(userRequest);
        UserDto userDto = UserMapper.getInstance().fromUserRequestToDto(userRequest);
        UserDto createdUserDto = userService.insert(userDto);
        return new ResponseEntity<>(UserMapper.getInstance().fromDtoToUserResponse(createdUserDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id).get();
        return new ResponseEntity<>(UserMapper.getInstance().fromDtoToUserResponse(userDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserDto> userDtoList = userService.getAll();
        return new ResponseEntity<>(userDtoList.stream().map(UserMapper.getInstance()::fromDtoToUserResponse).toList(),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        if (!id.equals(userRequest.id())) {
            throw new IllegalArgumentException("El id del usuario no coincide con el id proporcionado");
        }
        DtoValidator.validate(userRequest);
        UserDto userDto = UserMapper.getInstance().fromUserRequestToDto(userRequest);
        UserDto updatedUserDto = userService.update(userDto);
        return new ResponseEntity<>(UserMapper.getInstance().fromDtoToUserResponse(updatedUserDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
