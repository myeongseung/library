package com.korit.library.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RoleDtlDto {
    private int roleDtlId;

    private int userId;
    private int roleId;

    private RoleMstDto roleDto;

    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
