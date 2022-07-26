package com.pickpick.channel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "channel")
@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slack_id", length = 15, nullable = false, unique = true, updatable = false)
    private String slackId;

    @Column(name = "name", length = 80, nullable = false)
    private String name;

    protected Channel() {
    }

    public Channel(final String slackId, final String name) {
        this.slackId = slackId;
        this.name = name;
    }
}