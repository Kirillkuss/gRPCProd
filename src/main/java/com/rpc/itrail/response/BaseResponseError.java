package com.rpc.itrail.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BaseResponseError {

    private int code;
    private String message;
    
}
