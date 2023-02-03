package com.RemoteTokenMiddle.server.Auth;

import lombok.Data;

@Data
public class AuthResponse {

  private Boolean authenticated;
  private String username;
  private String accessToken;
  private String message;

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

  public AuthResponse(Boolean authenticated, String username, String accessToken, String message) {
    this.authenticated = authenticated;
    this.username = username;
    this.accessToken = accessToken;
    this.message = message;
  }
}
