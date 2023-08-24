package com.yeachan.exp.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "authority")
public class Authority {
    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;
}