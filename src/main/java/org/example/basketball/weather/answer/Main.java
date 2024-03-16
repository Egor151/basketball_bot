package org.example.basketball.weather.answer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class Main {
    private Integer temp;

    @JsonProperty("feels_like")
    private Integer feelsLike;

}
