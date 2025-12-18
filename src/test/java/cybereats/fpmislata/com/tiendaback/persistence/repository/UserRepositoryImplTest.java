package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

  @Mock
  private UserJpaDao userJpaDao;

  @InjectMocks
  private UserRepositoryImpl userRepository;

  private UserDto userDto;
  private UserJpaEntity userEntity;

  @BeforeEach
  void setUp() {
    userDto = new UserDto(1L, "John", "Doe", "john@example.com", "1990-01-01", "johndoe", "password", UserRole.CLIENT);
    userEntity = new UserJpaEntity(1L, "John", "Doe", "john@example.com", "1990-01-01", "johndoe", "password",
        UserRole.CLIENT);
  }

  @Test
  @DisplayName("Debería encontrar un usuario por ID")
  void shouldFindById() {
    when(userJpaDao.findById(1L)).thenReturn(Optional.of(userEntity));

    Optional<UserDto> result = userRepository.findById(1L);

    assertTrue(result.isPresent());
    assertEquals(userDto.id(), result.get().id());
  }

  @Test
  @DisplayName("Debería devolver todos los usuarios paginados")
  void shouldFindAll() {
    when(userJpaDao.findAll(1, 10)).thenReturn(List.of(userEntity));
    when(userJpaDao.count()).thenReturn(1L);

    Page<UserDto> result = userRepository.findAll(1, 10);

    assertNotNull(result);
    assertEquals(1, result.data().size());
    assertEquals(1L, result.totalElements());
  }

  @Test
  @DisplayName("Debería guardar un usuario nuevo (insert)")
  void shouldSaveNewUser() {
    UserDto newUserDto = new UserDto(null, "New", "User", "new@example.com", "1990-01-01", "newuser", "pass",
        UserRole.CLIENT);
    when(userJpaDao.insert(any(UserJpaEntity.class))).thenReturn(userEntity);

    UserDto result = userRepository.save(newUserDto);

    assertNotNull(result);
    verify(userJpaDao).insert(any(UserJpaEntity.class));
  }

  @Test
  @DisplayName("Debería actualizar un usuario existente (update)")
  void shouldUpdateUser() {
    when(userJpaDao.update(any(UserJpaEntity.class))).thenReturn(userEntity);

    UserDto result = userRepository.save(userDto);

    assertNotNull(result);
    verify(userJpaDao).update(any(UserJpaEntity.class));
  }

  @Test
  @DisplayName("Debería eliminar un usuario por ID")
  void shouldDeleteById() {
    doNothing().when(userJpaDao).deleteById(1L);

    userRepository.deleteById(1L);

    verify(userJpaDao).deleteById(1L);
  }
}
