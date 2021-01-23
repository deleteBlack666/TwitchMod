package tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.impl;

import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.ByteArraySerializer;
import tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.SerializationStrategy;

import java.util.Arrays;

public final class ByteArraySerializationStrategy implements SerializationStrategy {

    private final byte[] value;
    private final ByteArraySerializer byteArraySerializer;

    public ByteArraySerializationStrategy(byte[] value, SerializerFactory serializerFactory) {
        this.value = Arrays.copyOf(value, value.length);
        this.byteArraySerializer = serializerFactory.getByteArraySerializer();
    }

    @Override
    public byte[] serialize() {
        return byteArraySerializer.serialize(value);
    }

    @Override
    public Object getValue() {
        return value;
    }
}