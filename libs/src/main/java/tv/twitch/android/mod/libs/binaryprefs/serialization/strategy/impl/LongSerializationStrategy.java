package tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.impl;

import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.LongSerializer;
import tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.SerializationStrategy;

public final class LongSerializationStrategy implements SerializationStrategy {

    private final long value;
    private final LongSerializer longSerializer;

    public LongSerializationStrategy(long value, SerializerFactory serializerFactory) {
        this.value = value;
        this.longSerializer = serializerFactory.getLongSerializer();
    }

    @Override
    public byte[] serialize() {
        return longSerializer.serialize(value);
    }

    @Override
    public Object getValue() {
        return value;
    }
}