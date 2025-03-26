package com.pablo.calendar_backend.entity;

import java.util.ArrayList;
import java.util.List;

public enum NotificationType {

    EVENT("Evento"),
    MEETING("Reunión"),
    REMINDER("Recordatorio"),
    APPOINTMENT("Cita");

    private final String name;

    NotificationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<String> getValues() {
        List<String> values = new ArrayList<>();
        for (NotificationType type : NotificationType.values()) {
            values.add(type.getName());
        }
        return values;
    }

}
