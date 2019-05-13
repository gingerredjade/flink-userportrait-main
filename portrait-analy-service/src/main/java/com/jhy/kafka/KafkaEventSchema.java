/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jhy.kafka;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

/**
 * 该KafkaEventSchema类实现了Kafka事件的序列化和反序列化
 *
 * The serialization schema for the {@link KafkaEvent} type. This class defines how to transform a
 * Kafka record's bytes to a {@link KafkaEvent}, and vice-versa（反之亦然）.
 */
public class KafkaEventSchema implements DeserializationSchema<KafkaEvent>, SerializationSchema<KafkaEvent> {

    private static final long serialVersionUID = 6154188370181669758L;

    @Override
    public KafkaEvent deserialize(byte[] message) throws IOException {
        return KafkaEvent.fromString(new String(message));
    }

    @Override
    public boolean isEndOfStream(KafkaEvent nextElement) {
        return false;
    }

    @Override
    public byte[] serialize(KafkaEvent element) {
        return element.toString().getBytes();
    }

    @Override
    public TypeInformation<KafkaEvent> getProducedType() {
        return TypeInformation.of(KafkaEvent.class);
    }
}
