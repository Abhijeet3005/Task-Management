package com.TaskManagement.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "projects")
public class Project {

    @Id
    private String id;
    private String name;
    private String description;
    private String createdby;
    private List< User > members;

    public String getDescription() {
        return description;
    }
    public void setDescription(String desc){
        this.description = desc;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
