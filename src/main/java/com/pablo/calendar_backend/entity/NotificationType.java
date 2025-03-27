package com.pablo.calendar_backend.entity;

import java.util.ArrayList;

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

    public static ArrayList<String> getValues() {
        ArrayList<String> values = new ArrayList<>();
        for (NotificationType type : NotificationType.values()) {
            values.add(type.getName());
        }
        return values;
    }

    public static NotificationType fromString(String name) {
        for (NotificationType type : NotificationType.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

}
