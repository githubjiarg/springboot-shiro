package com.spring.login.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileBean implements Serializable {

    private String fileName;

    private String dateTime;

}
