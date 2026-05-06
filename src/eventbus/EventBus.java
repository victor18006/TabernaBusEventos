package src.eventbus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private final Map<Class<?>, List<Subscriber>> subscribers = new ConcurrentHashMap<>();

    public interface Subscriber {
        void onEvent(Object event);
    }

    public <T> void suscribir(Class<T> eventType, Subscriber subscriber) {
        subscribers.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>()).add(subscriber);
    }

    public <T> void desuscribir(Class<T> eventType, Subscriber subscriber) {
        List<Subscriber> list = subscribers.get(eventType);
        if (list != null) {
            list.remove(subscriber);
        }
    }

    public void publicar(Object event) {
        Class<?> eventType = event.getClass();
        List<Subscriber> list = subscribers.get(eventType);
        if (list != null) {
            for (Subscriber subscriber : list) {
                subscriber.onEvent(event);
            }
        }
    }
}