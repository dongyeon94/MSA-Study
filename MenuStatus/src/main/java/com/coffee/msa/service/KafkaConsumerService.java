package com.coffee.msa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final MenuStatusService menuStatusService;
    private static final String TOPIC = "dymenutopic1";

    @KafkaListener(topics = TOPIC, groupId = "dytest1" , containerFactory = "kafkaListenerContainerFactory")
    public void consumser(String msg) throws ParseException {
        log.info("consume message : "+ msg);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(msg);

        String orderId = (String) jsonObject.get("orderId");
        String username = (String) jsonObject.get("username");
        String orderMenu = (String) jsonObject.get("orderMenu");
        String orderCount = (String) jsonObject.get("count");

        menuStatusService.registorMenu(orderId, orderMenu, orderCount, username);
    }
}
