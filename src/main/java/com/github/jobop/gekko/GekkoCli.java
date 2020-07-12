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
 * Created by CuttleFish on 2020/7/4.
 */
package com.github.jobop.gekko;


import com.github.jobop.gekko.core.GekkoConfig;
import com.github.jobop.gekko.core.GekkoNode;

import java.util.concurrent.atomic.AtomicInteger;


public class GekkoCli {
    AtomicInteger hashStarted = new AtomicInteger(0);
    GekkoConfig conf;
    GekkoNode node;

    public GekkoCli(GekkoConfig conf) {
        this.conf = conf;

    }

    public void start() {
        if (hashStarted.compareAndSet(0, 1)) {
            this.node = new GekkoNode(conf);
            this.node.init();
            this.node.start();
        }
    }

    public void shutdown() {
        if (hashStarted.compareAndSet(1, 0)) {
            this.node.shutdown();
        }

    }
}
