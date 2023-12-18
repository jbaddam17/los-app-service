package com.loanorignx.app.entity.appreq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "application")
@NoArgsConstructor
@AllArgsConstructor
public class LOSAppRequest {
    @Id
    private String id;
    private Map<String, Object> requestData;
}
