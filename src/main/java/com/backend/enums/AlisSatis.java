package com.backend.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AlisSatis {
    ALIS(0, "Alış"),
    SATIS(1, "Satış");
    private final int code;
    private final String value;
}


