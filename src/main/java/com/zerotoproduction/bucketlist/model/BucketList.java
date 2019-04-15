package com.zerotoproduction.bucketlist.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@ApiModel(description = "All details about Bucket. ")
public class BucketList {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated bucket ID")
    private long id;

    @Column(name = "name", length = 60, nullable = false)
    @ApiModelProperty(notes = "The bucket name")
    private String name;

    @Column
    @ApiModelProperty(notes = "The bucket description")
    private  String description;

    BucketList(){}

    public BucketList(String name, String description){
        this.name = name;
        this.description = description;
    }

    public BucketList(long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BucketList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
