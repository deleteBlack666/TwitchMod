package tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.impl;

import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.BooleanSerializer;
import tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.SerializationStrategy;

public final class BooleanSerializationStrategy implements SerializationStrategy {

    private final boolean value;
    private final BooleanSerializer booleanSerializer;

    public BooleanSerializationStrategy(boolean value, SerializerFactory serializerFactory) {
        this.value = value;
        this.booleanSerializer = serializerFactory.getBooleanSerializer();
    }

    @Override
    public byte[] serialize() {
        return booleanSerializer.serialize(value);
    }

    @Override
    public Object getValue() {
        return value;
    }
}