package tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.impl;

import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.StringSerializer;
import tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.SerializationStrategy;

public final class StringSerializationStrategy implements SerializationStrategy {

    private final String value;
    private final StringSerializer stringSerializer;

    public StringSerializationStrategy(String value, SerializerFactory serializerFactory) {
        this.value = value;
        this.stringSerializer = serializerFactory.getStringSerializer();
    }

    @Override
    public byte[] serialize() {
        return stringSerializer.serialize(value);
    }

    @Override
    public Object getValue() {
        return value;
    }
}