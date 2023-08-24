package com.yeachan.exp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.yeachan.exp.domain.Member} entity
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto implements Serializable {
    @Email
    @NotNull
    @Size(min = 3,max = 50)
    private String email;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3,max = 100)
    private String password;
    
    @NotNull
    @Size(min = 3,max = 50)
    private String memberName;
}