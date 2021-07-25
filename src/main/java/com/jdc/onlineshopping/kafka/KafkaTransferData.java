package com.jdc.onlineshopping.kafka;

import io.jsonwebtoken.Header;
import lombok.Data;

/**
 * @author tiendao on 22/07/2021
 */
@Data
public class KafkaTransferData {

    private HeaderData header;

    private String data;
}
