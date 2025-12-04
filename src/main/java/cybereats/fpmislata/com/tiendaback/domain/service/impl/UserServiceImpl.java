package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.repository.UserRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.UserService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto insert(UserDto user) {
        Optional<UserDto> userDto = userRepository.getUserById(user.id());
        if (userDto.isPresent()) {
            throw new BusinessException("User already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public UserDto update(UserDto user) {
        Optional<UserDto> userDto = userRepository.getUserById(user.id());
        if (userDto.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        Optional<UserDto> userDto = userRepository.getUserById(id);
        if (userDto.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        return userDto;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
        Optional<UserDto> userDto = userRepository.getUserById(id);
        if (userDto.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}