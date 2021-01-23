package tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.impl;

import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.StringSetSerializer;
import tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.SerializationStrategy;

import java.util.HashSet;
import java.util.Set;

public final class StringSetSerializationStrategy implements SerializationStrategy {

    private final Set<String> value;
    private final StringSetSerializer stringSetSerializer;

    public StringSetSerializationStrategy(Set<String> value, SerializerFactory serializerFactory) {
        this.value = value;
        this.stringSetSerializer = serializerFactory.getStringSetSerializer();
    }

    @Override
    public byte[] serialize() {
        return stringSetSerializer.serialize(value);
    }

    @Override
    public Object getValue() {
        return new HashSet<>(value);
    }
}