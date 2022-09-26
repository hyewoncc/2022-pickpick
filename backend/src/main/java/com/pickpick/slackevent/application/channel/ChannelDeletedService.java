package com.pickpick.slackevent.application.channel;

import com.pickpick.channel.domain.ChannelRepository;
import com.pickpick.message.domain.MessageRepository;
import com.pickpick.slackevent.application.SlackEvent;
import com.pickpick.slackevent.application.SlackEventService;
import com.pickpick.slackevent.application.channel.dto.ChannelDeletedRequest;
import com.pickpick.utils.JsonUtils;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ChannelDeletedService implements SlackEventService {

    private final ChannelRepository channels;
    private final MessageRepository messages;

    public ChannelDeletedService(final ChannelRepository channels, final MessageRepository messages) {
        this.channels = channels;
        this.messages = messages;
    }

    @Override
    public void execute(final String requestBody) {
        String channelSlackId = extractChannelSlackId(requestBody);

        messages.deleteAllByChannelSlackId(channelSlackId);
        channels.deleteBySlackId(channelSlackId);
    }

    private String extractChannelSlackId(final Map<String, Object> requestBody) {
        return (String) requestBody.get(CHANNEL_SLACK_ID);
    }

    @Override
    public boolean isSameSlackEvent(final SlackEvent slackEvent) {
        return SlackEvent.CHANNEL_DELETED == slackEvent;
    }
}
