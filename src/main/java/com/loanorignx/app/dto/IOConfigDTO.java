package com.loanorignx.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "config")
@NoArgsConstructor
public class IOConfigDTO {

    private Integer ioConfigId;
    private Integer vendorId;
    private String name;
    private String configJson;
    private String type;
}
