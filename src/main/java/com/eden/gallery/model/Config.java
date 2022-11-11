package com.eden.gallery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Config {

    @Id
    private String key;
    private int value;
}
