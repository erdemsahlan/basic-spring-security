package com.backend.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ParaTipi {
    TURKLIRASI(0, "TürkLirası"),
    DOLAR(1, "DOLAR"),
    EURO(2, "EURO");
    private final int code;
    private final String value;
}
