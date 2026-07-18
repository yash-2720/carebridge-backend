package com.kinthrahub.backend.authentication;

import com.kinthrahub.backend.authentication.dto.LoginRequestDTO;
import com.kinthrahub.backend.authentication.dto.LoginResponseDTO;

public interface AuthenticationService {
	
	LoginResponseDTO login (LoginRequestDTO request);

}
