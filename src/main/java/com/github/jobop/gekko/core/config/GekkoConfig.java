/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by CuttleFish on 2020/7/3.
 */
package com.github.jobop.gekko.core.config;

import com.github.jobop.gekko.core.statemachine.NoopStateMachine;
import com.github.jobop.gekko.core.statemachine.StateMachine;
import com.github.jobop.gekko.enums.StoreEnums;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Data
@Builder(toBuilder = true)
public class GekkoConfig {
    String group;
    StoreEnums storeType;
    @Singular
    Set<String> peers;
    @Singular
    Set<String> peerIds;
    @Singular
    Set<Integer> peerApiPorts;

    String selfId;
    String leaderId;
    @Builder.Default
    StateMachine stateMachine = new NoopStateMachine();

    String baseFilePath;
    @Builder.Default
    int storeFileSize = 1024 * 1024 * 40;
    @Builder.Default
    int osPageSize = 1024 * 4;
    @Builder.Default
    int flushInterval = 1;
    @Builder.Default
    int indexCountPerFile = 100000;
    @Builder.Default
    int saveCheckPointInterval = 5;
    @Builder.Default
    int maxElectionTimeOut = 5000;
    @Builder.Default
    int minElectionTimeOut = 2000;
    @Builder.Default
    int heartBeatInterval = 1000;

}
