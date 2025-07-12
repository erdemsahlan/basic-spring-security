package com.backend.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AlisSatis {
    ALIS(1, "Alış"),
    SATIS(2, "Satış");
    private final int code;
    private final String value;
}


