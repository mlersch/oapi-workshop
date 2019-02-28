package com.example.demo.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Dog
 */

public class Dog  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("age")
  private Integer age;

  @JsonProperty("country")
  private String country;

  public Dog id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The dogs unique ID
   * @return id
  */
  @ApiModelProperty(value = "The dogs unique ID")

@Size(min=3,max=10) 
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Dog name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the dog
   * @return name
  */
  @ApiModelProperty(required = true, value = "The name of the dog")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Dog age(Integer age) {
    this.age = age;
    return this;
  }

  /**
   * The Age of the dog
   * minimum: 1
   * maximum: 21
   * @return age
  */
  @ApiModelProperty(required = true, value = "The Age of the dog")
  @NotNull

@Min(1) @Max(21) 
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Dog country(String country) {
    this.country = country;
    return this;
  }

  /**
   * The [ISO 3166](https://en.wikipedia.org/wiki/ISO_3166-1) country code where the dog lives
   * @return country
  */
  @ApiModelProperty(value = "The [ISO 3166](https://en.wikipedia.org/wiki/ISO_3166-1) country code where the dog lives")

@Pattern(regexp="^(\\d{3}|[A-Z]{2,3})$") 
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Dog dog = (Dog) o;
    return Objects.equals(this.id, dog.id) &&
        Objects.equals(this.name, dog.name) &&
        Objects.equals(this.age, dog.age) &&
        Objects.equals(this.country, dog.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, country);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Dog {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

