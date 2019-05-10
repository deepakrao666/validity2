package com.validity.models;

import lombok.Data;

import java.util.List;

@Data
public class FileReport {
    private float redundancyRate;
    private List<User> masterList;
    private List<User> dupList;
}
