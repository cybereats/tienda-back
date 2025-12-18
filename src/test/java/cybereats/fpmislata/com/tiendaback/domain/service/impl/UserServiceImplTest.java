package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.repository.UserRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  private UserDto userDto;

  @BeforeEach
  void setUp() {
    userDto = new UserDto(1L, "John", "Doe", "john@example.com", "1990-01-01", "johndoe", "password", UserRole.CLIENT);
  }

  @Nested
  @DisplayName("Tests para el método findAll")
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver una página de usuarios")
    void shouldReturnPage() {
      Page<UserDto> page = new Page<>(List.of(userDto), 1, 10, 1L);
      when(userRepository.findAll(1, 10)).thenReturn(page);

      Page<UserDto> result = userService.findAll(1, 10);

      assertNotNull(result);
      assertEquals(1, result.data().size());
      verify(userRepository).findAll(1, 10);
    }
  }

  @Nested
  @DisplayName("Tests para el método getById/findById")
  class GetByIdTests {
    @Test
    @DisplayName("Debería devolver el usuario cuando existe")
    void shouldReturnUserWhenExists() {
      when(userRepository.findById(1L)).thenReturn(Optional.of(userDto));

      UserDto result = userService.getById(1L);

      assertNotNull(result);
      assertEquals(userDto.id(), result.id());
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(userRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> userService.getById(1L));
    }
  }

  @Nested
  @DisplayName("Tests para el método insert")
  class InsertTests {
    @Test
    @DisplayName("Debería insertar un usuario cuando no existe")
    void shouldInsertWhenDoesNotExist() {
      when(userRepository.findById(1L)).thenReturn(Optional.empty());
      when(userRepository.save(userDto)).thenReturn(userDto);

      UserDto result = userService.insert(userDto);

      assertNotNull(result);
      verify(userRepository).save(userDto);
    }

    @Test
    @DisplayName("Debería lanzar BusinessException cuando ya existe")
    void shouldThrowExceptionWhenAlreadyExists() {
      when(userRepository.findById(1L)).thenReturn(Optional.of(userDto));

      assertThrows(BusinessException.class, () -> userService.insert(userDto));
    }
  }

  @Nested
  @DisplayName("Tests para el método update")
  class UpdateTests {
    @Test
    @DisplayName("Debería actualizar un usuario existente")
    void shouldUpdateWhenExists() {
      when(userRepository.findById(1L)).thenReturn(Optional.of(userDto));
      when(userRepository.save(userDto)).thenReturn(userDto);

      UserDto result = userService.update(userDto);

      assertNotNull(result);
      verify(userRepository).save(userDto);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(userRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> userService.update(userDto));
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteById")
  class DeleteTests {
    @Test
    @DisplayName("Debería eliminar un usuario existente")
    void shouldDeleteWhenExists() {
      when(userRepository.findById(1L)).thenReturn(Optional.of(userDto));
      doNothing().when(userRepository).deleteById(1L);

      userService.deleteById(1L);

      verify(userRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(userRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> userService.deleteById(1L));
    }
  }
}
