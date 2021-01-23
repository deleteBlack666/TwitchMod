package tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.impl;

import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.ByteSerializer;
import tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.SerializationStrategy;

public final class ByteSerializationStrategy implements SerializationStrategy {

    private final byte value;
    private final ByteSerializer byteSerializer;

    public ByteSerializationStrategy(byte value, SerializerFactory serializerFactory) {
        this.value = value;
        this.byteSerializer = serializerFactory.getByteSerializer();
    }

    @Override
    public byte[] serialize() {
        return byteSerializer.serialize(value);
    }

    @Override
    public Object getValue() {
        return value;
    }
}