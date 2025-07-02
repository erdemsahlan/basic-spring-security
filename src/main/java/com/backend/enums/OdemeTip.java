package com.backend.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OdemeTip {
    CEK(1, "Çek"),
    NAKIT(2, "Nakit"),
    KREDI_KARTI(3, "Kredi Kartı");
    private final int code;
    private final String value;
}
