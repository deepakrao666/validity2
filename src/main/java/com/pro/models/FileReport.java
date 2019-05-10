package com.pro.models;

import lombok.Data;

import java.util.List;

@Data
public class FileReport {
    private float redundancyRate;
    private List<User> dupList;
    private List<User> masterList;
}
