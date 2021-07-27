package br.com.builders.client.api.event.listener;

import br.com.builders.client.api.config.EventConfig;
import br.com.builders.client.api.event.EventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class EventModelListener {

    private final EventConfig eventConfig;
    private final RabbitTemplate rabbitTemplate;

    @EventListener(EventModel.class)
    @TransactionalEventListener(phase = AFTER_COMMIT, fallbackExecution = true)
    public void onEventModel(final EventModel eventModel) {
        rabbitTemplate.convertAndSend(eventConfig.getClientExchange(), null, eventModel);
    }
}
