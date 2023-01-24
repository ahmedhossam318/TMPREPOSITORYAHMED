package mts.ftth.vc4.services.apiInterface;

import mts.ftth.vc4.payload.request.LoginRequest;
import mts.ftth.vc4.payload.response.JwtResponse;

public interface AuthService {

    public JwtResponse authenticateUser(LoginRequest loginRequest);

}
