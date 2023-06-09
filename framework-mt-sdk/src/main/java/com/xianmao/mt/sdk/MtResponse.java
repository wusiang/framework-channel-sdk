package com.xianmao.mt.sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MtResponse<T> {
  public T data;

  private Integer code;

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
