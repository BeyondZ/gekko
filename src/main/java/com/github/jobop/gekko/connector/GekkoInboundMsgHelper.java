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
 * Created by CuttleFish on 2020/7/2.
 */

package com.github.jobop.gekko.connector;


import com.github.jobop.gekko.core.statemachine.StateMachine;
import com.github.jobop.gekko.protocols.GekkoInboundProtocol;
import com.github.jobop.gekko.protocols.message.api.*;
import com.github.jobop.gekko.protocols.message.node.VoteReq;
import com.github.jobop.gekko.protocols.message.node.VoteResp;
import com.github.jobop.gekko.store.Store;


public class GekkoInboundMsgHelper implements GekkoInboundProtocol {
    Store store;
    StateMachine stateMachine;

    public GekkoInboundMsgHelper(Store store, StateMachine stateMachine) {
        this.store = store;
        this.stateMachine = stateMachine;
    }

    public GetEntryResp getEntries(GetEntryReq req) {

        return null;
    }

    public AppendEntryResp appendEntry(AppendEntryReq req) {
        return null;
    }

    public GetMetadataResp getMetadata(GetMetadataReq req) {
        return null;
    }

    public VoteResp vote(VoteReq req) {
        return null;
    }
}