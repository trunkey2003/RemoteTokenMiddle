package com.RemoteTokenMiddle.server.Auth;

import lombok.Data;

@Data
public class AuthResponse {

  private Boolean authenticated;
  private String username;

  public AuthResponse(Boolean authenticated) {
    this.authenticated = authenticated;
  }

  public AuthResponse(Boolean authenticated, String username) {
    this.authenticated = authenticated;
    this.username = username;
  }
}
