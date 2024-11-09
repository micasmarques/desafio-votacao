package com.logoscti.micael.assembleia.batch;

import com.logoscti.micael.assembleia.model.Voto;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class VotoProducer {

    private static final String QUEUE_NAME = "queue.votos";

    private RabbitTemplate rabbitTemplate;

    public void enviarVotoParaFila(Voto voto) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, voto);
    }
}
