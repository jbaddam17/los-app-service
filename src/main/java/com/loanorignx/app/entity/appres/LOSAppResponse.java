package com.loanorignx.app.entity.appres;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "los_offer_response")
@NoArgsConstructor
public class LOSAppResponse {

    //TODO: define offer structure
    private String res;
}
