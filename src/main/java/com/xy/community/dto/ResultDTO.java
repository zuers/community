package com.xy.community.dto;

import lombok.Data;

/**
 * @Author:Kori
 * @Description:
 * @Date: Create in 13:26 2019/8/30
 * @Modified by:
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }
}
