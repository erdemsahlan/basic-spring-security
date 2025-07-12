package com.backend.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ParaTipi {
    TURKLIRASI(1, "TürkLirası"),
    DOLAR(2, "DOLAR"),
    EURO(3, "EURO");
    private final int code;
    private final String value;
}
