package com.logoscti.micael.assembleia.batch;

import java.util.ArrayList;
import java.util.List;

import com.logoscti.micael.assembleia.model.Voto;
import com.logoscti.micael.assembleia.repositories.VotoRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class VotoConsumer {

    private VotoRepository votoRepository;

    private List<Voto> buffer = new ArrayList<>();
    private static final int BATCH_SIZE = 100;

    @RabbitListener(queues = "queue.votos")
    public void receberVoto(Voto voto) {
        buffer.add(voto);
        if (buffer.size() >= BATCH_SIZE) {
            votoRepository.saveAll(buffer);
            buffer.clear();
        }
    }
}
