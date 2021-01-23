package tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.impl;

import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.CharSerializer;
import tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.SerializationStrategy;

public final class CharSerializationStrategy implements SerializationStrategy {

    private final char value;
    private final CharSerializer charSerializer;

    public CharSerializationStrategy(char value, SerializerFactory serializerFactory) {
        this.value = value;
        this.charSerializer = serializerFactory.getCharSerializer();
    }

    @Override
    public byte[] serialize() {
        return charSerializer.serialize(value);
    }

    @Override
    public Object getValue() {
        return value;
    }
}