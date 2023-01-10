package com.example.beecoin.DTOs;

import com.example.beecoin.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemSearchDTO {
    String id;

    String name;

    StatusEnum status;

    String token;

    String ip;

    String apiCheckUser;
}
