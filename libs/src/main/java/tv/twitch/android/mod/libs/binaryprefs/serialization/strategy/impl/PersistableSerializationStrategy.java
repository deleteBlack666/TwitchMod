package tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.impl;

import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.PersistableSerializer;
import tv.twitch.android.mod.libs.binaryprefs.serialization.serializer.persistable.Persistable;
import tv.twitch.android.mod.libs.binaryprefs.serialization.strategy.SerializationStrategy;

public final class PersistableSerializationStrategy implements SerializationStrategy {

    private final Persistable value;
    private final PersistableSerializer persistableSerializer;

    public PersistableSerializationStrategy(Persistable value, SerializerFactory serializerFactory) {
        this.value = value;
        this.persistableSerializer = serializerFactory.getPersistableSerializer();
    }

    @Override
    public byte[] serialize() {
        return persistableSerializer.serialize(value);
    }

    @Override
    public Object getValue() {
        return value.deepClone();
    }
}