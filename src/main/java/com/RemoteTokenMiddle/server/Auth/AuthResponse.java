package com.RemoteTokenMiddle.server.Auth;

import lombok.Data;

@Data
public class AuthResponse {

  private Boolean authenticated;
  private String username;
  private String accessToken;

  public AuthResponse(Boolean authenticated) {
    this.authenticated = authenticated;
  }

  public AuthResponse(Boolean authenticated, String username) {
    this.authenticated = authenticated;
    this.username = username;
  }

  public AuthResponse(Boolean authenticated, String username, String accessToken) {
    this.authenticated = authenticated;
    this.username = username;
    this.accessToken = accessToken;
  }
}
