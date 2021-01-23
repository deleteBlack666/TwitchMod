package tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.impl;

import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.IntegerSerializer;
import tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.SerializationStrategy;

public final class IntegerSerializationStrategy implements SerializationStrategy {

    private final int value;
    private final IntegerSerializer integerSerializer;

    public IntegerSerializationStrategy(int value, SerializerFactory serializerFactory) {
        this.value = value;
        this.integerSerializer = serializerFactory.getIntegerSerializer();
    }

    @Override
    public byte[] serialize() {
        return integerSerializer.serialize(value);
    }

    @Override
    public Object getValue() {
        return value;
    }
}