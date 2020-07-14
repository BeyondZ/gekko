
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
 * Created by CuttleFish on 2020/7/13.
 */

import com.github.jobop.gekko.utils.NotifyableThread;
import org.junit.Test;

public class NotifyableThreadTest {

    @Test
    public void testRunInterval() throws InterruptedException {
        NotifyableThread t = new NotifyableThread(10,"testRunInterval") {
            @Override
            public void doWork() {
                System.out.println(System.currentTimeMillis());
            }
        };
        t.start();
        t.join();
    }

    @Test
    public void testRunByTrigger() throws InterruptedException {
        NotifyableThread t = new NotifyableThread(10,"testRunByTrigger") {
            @Override
            public void doWork() {
                System.out.println(System.currentTimeMillis());
            }
        };
        t.start();

        Thread.sleep(1000);
        t.trigger();
        Thread.sleep(1000);
        t.trigger();
        Thread.sleep(1000);
        t.trigger();
        Thread.sleep(1000);
        t.trigger();
        Thread.sleep(1000);
        t.trigger();
        t.join();
    }

    @Test
    public void testWaitforShutdown() throws InterruptedException {
        NotifyableThread t = new NotifyableThread(10,"testWaitforShutdown") {
            @Override
            public void doWork() {
                System.out.println(System.currentTimeMillis());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        Thread.sleep(1000);
        t.shutdown();

        t.join();
    }


}
