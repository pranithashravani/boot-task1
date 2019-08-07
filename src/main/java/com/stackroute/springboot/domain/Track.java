package com.stackroute.springboot.domain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Track")
@PropertySource("application.properties")
public class Track {
  @Id
  @Column(name="id")
  @Value("id")
  private int id;
  @Column(name="name")

  @Value("${value.name}")
  private String name;
  @Column(name="comment")
  @Value("${value.comment}")
  private String comment;

  public Track() {
  }

  public Track(int id, String name, String comment) {
    this.id = id;
    this.name = name;
    this.comment = comment;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "Track{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", comment='" + comment + '\'' +
      '}';
  }
}
