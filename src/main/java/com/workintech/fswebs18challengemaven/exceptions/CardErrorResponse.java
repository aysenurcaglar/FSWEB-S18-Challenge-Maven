package com.workintech.fswebs18challengemaven.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardErrorResponse {
    private String message;
    private Integer status;

    public CardErrorResponse(String message) {
        this.message = message;
    }
}
