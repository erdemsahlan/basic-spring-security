package com.backend.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OdemeTip {
    CEK(0, "Çek"),
    NAKIT(1, "Nakit"),
    KREDI_KARTI(2, "Kredi Kartı");
    private final int code;
    private final String value;
}
