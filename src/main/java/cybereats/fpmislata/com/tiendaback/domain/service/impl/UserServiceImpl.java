package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.UserRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.UserService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

import java.util.List;
import jakarta.transaction.Transactional;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDto insert(UserDto user) {
        Optional<UserDto> userDto = userRepository.findById(user.id());
        if (userDto.isPresent()) {
            throw new BusinessException("User already exists");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDto update(UserDto user) {
        Optional<UserDto> userDto = userRepository.findById(user.id());
        if (userDto.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        return userRepository.save(user);
    }

    @Override
    public UserDto getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<UserDto> findAll(int page, int size) {
        return userRepository.findAll(page, size);
    }

    @Override
    public Page<UserDto> search(String text, String role, int page, int size) {
        return userRepository.search(text, role, page, size);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<UserDto> userDto = userRepository.findById(id);
        if (userDto.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}