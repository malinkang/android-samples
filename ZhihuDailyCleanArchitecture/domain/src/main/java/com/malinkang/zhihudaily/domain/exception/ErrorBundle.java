package com.malinkang.zhihudaily.domain.exception;

public interface ErrorBundle {
  Exception getException();

  String getErrorMessage();
}