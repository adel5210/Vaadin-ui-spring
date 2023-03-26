package com.adel.vaadin.model;

import java.time.Instant;

public record Message(
        String username,
        String text,
        Instant time
) {
}
