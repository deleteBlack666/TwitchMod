package tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.impl;

import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.ShortSerializer;
import tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.SerializationStrategy;

public final class ShortSerializationStrategy implements SerializationStrategy {

    private final short value;
    private final ShortSerializer shortSerializer;

    public ShortSerializationStrategy(short value, SerializerFactory serializerFactory) {
        this.value = value;
        this.shortSerializer = serializerFactory.getShortSerializer();
    }

    @Override
    public byte[] serialize() {
        return shortSerializer.serialize(value);
    }

    @Override
    public Object getValue() {
        return value;
    }
}