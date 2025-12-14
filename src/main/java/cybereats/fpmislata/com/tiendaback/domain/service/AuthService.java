package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.model.Token;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.AuthResponseDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LoginRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.RegisterRequestDto;

public interface AuthService {
    User getUsuarioFromToken(Token token);
    Token createTokenFromUser(User user);
    AuthResponseDto login(LoginRequestDto request);
    AuthResponseDto register(RegisterRequestDto request);
}
