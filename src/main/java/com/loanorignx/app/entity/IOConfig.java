package com.loanorignx.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IOConfig {

    private Integer ioConfigId;
    private Integer vendorId;
    private String name;
    private String configJson;
    private String type;
    private String dataMappingsJson;
}