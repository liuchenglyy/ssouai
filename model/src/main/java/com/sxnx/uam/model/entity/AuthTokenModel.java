package com.sxnx.uam.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenModel implements Serializable{

    private String accessToken;

    private Long accessExpire;

}