package com.backend.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_FOUND("1001","Kayıt Bulunamadı"),
    GENARAL_EXCEPTİON("9999","Genel Bir Hata Oluştu");
    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
    private String code;
    private String message;
}
