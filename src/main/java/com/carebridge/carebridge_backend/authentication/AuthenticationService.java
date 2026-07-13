package com.carebridge.carebridge_backend.authentication;

import com.carebridge.carebridge_backend.authentication.dto.LoginRequestDTO;
import com.carebridge.carebridge_backend.authentication.dto.LoginResponseDTO;

public interface AuthenticationService {
	
	LoginResponseDTO login (LoginRequestDTO request);

}
