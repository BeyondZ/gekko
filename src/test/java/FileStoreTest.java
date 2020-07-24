
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
 * Created by CuttleFish on 2020/7/14.
 */

import com.github.jobop.gekko.core.config.GekkoConfig;
import com.github.jobop.gekko.core.metadata.NodeState;
import com.github.jobop.gekko.enums.StoreEnums;
import com.github.jobop.gekko.protocols.message.GekkoEntry;
import com.github.jobop.gekko.store.FileStore;
import com.github.jobop.gekko.store.Store;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FileStoreTest extends BaseTest {
    @Test
    public void testAppendAndBatchGet1() {
        String dirPath = "/Users/zhengwei/Desktop/autorollfiles";
        this.paths.add(dirPath);
        GekkoConfig conf = GekkoConfig.builder().baseFilePath(dirPath).selfId("1").leaderId("1").storeType(StoreEnums.FILE).flushInterval(1).storeFileSize(1024 * 1024).indexCountPerFile(100000).osPageSize(1024 * 4).build();

        NodeState nodeState = new NodeState(conf);
        nodeState.init();
        Store store = new FileStore(conf, nodeState);
        store.init();
        store.start();
        String appendStr = "1sdfasdfasdfasdfasdfasdfasdfadf54545fasdfasdfasdfasdfasdfasdfadfa53345dfasdfasdfasdfasdfasdfasdfad9081nvsdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfad2";
        byte[] bytes = appendStr.getBytes();
        GekkoEntry entry = GekkoEntry.builder().magic(0xCAFEDADD).term(123).data(bytes).build();
        entry.computSizeInBytes();
        long pos_66666 = 0;
        long pos_99999 = 0;
        store.append(entry);

        List<GekkoEntry> targetEntry = store.batchGetByIndex(1, 2);
        Assert.assertEquals(1, targetEntry.size());
        Assert.assertTrue(targetEntry.get(0).isIntact());


    }

    @Test
    public void testAppendAndBatchGet2() {
        String dirPath = "/Users/zhengwei/Desktop/autorollfiles";
        this.paths.add(dirPath);
        GekkoConfig conf = GekkoConfig.builder().baseFilePath(dirPath).selfId("1").leaderId("1").storeType(StoreEnums.FILE).flushInterval(1).storeFileSize(1024 * 1024).indexCountPerFile(100000).osPageSize(1024 * 4).build();

        NodeState nodeState = new NodeState(conf);
        nodeState.init();
        Store store = new FileStore(conf, nodeState);
        store.init();
        store.start();
        String appendStr = "郑伟";
        byte[] bytes = appendStr.getBytes();
        GekkoEntry entry = GekkoEntry.builder().magic(0xCAFEDADD).term(123).data(bytes).build();
        entry.computSizeInBytes();
        store.append(entry);

        String appendStr1 = "范学敏";
        byte[] bytes1 = appendStr1.getBytes();
        GekkoEntry entry1 = GekkoEntry.builder().magic(0xCAFEDADD).term(123).data(bytes1).build();
        entry1.computSizeInBytes();
        store.append(entry1);


        List<GekkoEntry> targetEntry = store.batchGetByIndex(1, 2);
        Assert.assertEquals(1, targetEntry.size());
        Assert.assertTrue(targetEntry.get(0).isIntact());

        List<GekkoEntry> targetEntry1 = store.batchGetByIndex(1, 3);
        Assert.assertEquals(2, targetEntry1.size());
        Assert.assertTrue(targetEntry1.get(1).isIntact());

    }

    @Test
    public void testAppendAndBatchGet3() {
        String dirPath = "/Users/zhengwei/Desktop/autorollfiles";
        this.paths.add(dirPath);
        GekkoConfig conf = GekkoConfig.builder().baseFilePath(dirPath).selfId("1").leaderId("1").storeType(StoreEnums.FILE).flushInterval(1).storeFileSize(1024 * 1024).indexCountPerFile(100000).osPageSize(1024 * 4).build();

        NodeState nodeState = new NodeState(conf);
        nodeState.init();
        Store store = new FileStore(conf, nodeState);
        store.init();
        store.start();
        String appendStr = "1sdfasdfasdfasdfasdfasdfasdfadf54545fasdfasdfasdfasdfasdfasdfadfa53345dfasdfasdfasdfasdfasdfasdfad9081nvsdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfad2";
        byte[] bytes = appendStr.getBytes();
        GekkoEntry entry = GekkoEntry.builder().magic(0xCAFEDADD).term(123).data(bytes).build();
        entry.computSizeInBytes();
        for (int i = 0; i < 10; i++) {
            store.append(entry);

        }

        Assert.assertEquals(10, store.getMaxIndex());
        List<GekkoEntry> entryList0 = store.batchGetByIndex(1, 2);
        Assert.assertEquals(1, entryList0.get(0).getEntryIndex());
        Assert.assertEquals(1, entryList0.size());

        List<GekkoEntry> entryList1 = store.batchGetByIndex(1, 10);
        Assert.assertEquals(9, entryList1.size());

        List<GekkoEntry> entryList2 = store.batchGetByIndex(1, 11);
        Assert.assertEquals(10, entryList2.size());
    }


    @Test
    public void testAppendAndBatchGet4() {
        String dirPath = "/Users/zhengwei/Desktop/autorollfiles";
        this.paths.add(dirPath);
        GekkoConfig conf = GekkoConfig.builder().baseFilePath(dirPath).selfId("1").leaderId("1").storeType(StoreEnums.FILE).flushInterval(1).storeFileSize(1024 * 1024).indexCountPerFile(100000).osPageSize(1024 * 4).build();

        NodeState nodeState = new NodeState(conf);
        nodeState.init();
        Store store = new FileStore(conf, nodeState);
        store.init();
        store.start();
        String appendStr = "1sdfasdfasdfasdfasdfasdfasdfadf54545fasdfasdfasdfasdfasdfasdfadfa53345dfasdfasdfasdfasdfasdfasdfad9081nvsdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfad2";
        byte[] bytes = appendStr.getBytes();
        GekkoEntry entry = GekkoEntry.builder().magic(0xCAFEDADD).term(123).data(bytes).build();
        entry.computSizeInBytes();
        for (int i = 0; i < 10000; i++) {
            store.append(entry);

        }

        Assert.assertEquals(10000, store.getMaxIndex());
        List<GekkoEntry> entryList0 = store.batchGetByIndex(1, 2);
        Assert.assertEquals(1, entryList0.get(0).getEntryIndex());
        Assert.assertEquals(1, entryList0.size());

        List<GekkoEntry> entryList1 = store.batchGetByIndex(1, 10);
        Assert.assertEquals(9, entryList1.size());

        List<GekkoEntry> entryList2 = store.batchGetByIndex(1, 10001);
        Assert.assertEquals(10000, entryList2.size());
    }


    @Test
    public void testAppendAndBatchGet5() {
        String dirPath = "/Users/zhengwei/Desktop/autorollfiles";
        this.paths.add(dirPath);
        GekkoConfig conf = GekkoConfig.builder().baseFilePath(dirPath).selfId("1").leaderId("1").storeType(StoreEnums.FILE).flushInterval(1).storeFileSize(1024 * 1024).indexCountPerFile(100000).osPageSize(1024 * 4).build();

        NodeState nodeState = new NodeState(conf);
        nodeState.init();
        Store store = new FileStore(conf, nodeState);
        store.init();
        store.start();
        String appendStr = "1sdfasdfasdfasdfasdfasdfasdfadf54545fasdfasdfasdfasdfasdfasdfadfa53345dfasdfasdfasdfasdfasdfasdfad9081nvsdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfad2";
        byte[] bytes = appendStr.getBytes();
        GekkoEntry entry = GekkoEntry.builder().magic(0xCAFEDADD).term(123).data(bytes).build();
        entry.computSizeInBytes();
        for (int i = 0; i < 1; i++) {
            store.append(entry);

        }

        Assert.assertEquals(1, store.getMaxIndex());

        List<GekkoEntry> entryList1 = store.batchGetByIndex(1, 2);
        Assert.assertEquals(1, entryList1.size());

    }


    @Test
    public void testTrim() {
        String dirPath = "/Users/zhengwei/Desktop/autorollfiles";
        this.paths.add(dirPath);
        GekkoConfig conf = GekkoConfig.builder().baseFilePath(dirPath).selfId("1").leaderId("1").storeType(StoreEnums.FILE).flushInterval(1).storeFileSize(1024 * 1024).indexCountPerFile(100000).osPageSize(1024 * 4).build();

        NodeState nodeState = new NodeState(conf);
        nodeState.init();
        nodeState.setCommitId(10);
        Store store = new FileStore(conf, nodeState);
        store.init();
        store.start();
        String appendStr = "1sdfasdfasdfasdfasdfasdfasdfadf54545fasdfasdfasdfasdfasdfasdfadfa53345dfasdfasdfasdfasdfasdfasdfad9081nvsdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfad2";
        byte[] bytes = appendStr.getBytes();
        GekkoEntry entry = GekkoEntry.builder().magic(0xCAFEDADD).term(123).data(bytes).build();
        entry.computSizeInBytes();
        for (int i = 0; i < 10; i++) {
            store.append(entry);

        }
        Assert.assertEquals(10, store.getMaxIndex());

        GekkoEntry _entry5=store.getByIndex(5);

        store.trimAfter(5);
        Assert.assertEquals(5, nodeState.getWriteId());
        Assert.assertEquals(5, nodeState.getCommitId());
        Assert.assertEquals(5, store.getMaxIndex());

        GekkoEntry entry5=store.getByIndex(5);
        Assert.assertTrue(entry5.isIntact());

        Assert.assertEquals(_entry5.getChecksum(),entry5.getChecksum());

        GekkoEntry entry6=store.getByIndex(6);

        Assert.assertNull(entry6);

    }

    @Test
    public void testAppendAndBatchGet() {
        String dirPath = "/Users/zhengwei/Desktop/autorollfiles";
        this.paths.add(dirPath);
        GekkoConfig conf = GekkoConfig.builder().baseFilePath(dirPath).selfId("1").leaderId("1").storeType(StoreEnums.FILE).flushInterval(1).storeFileSize(1024 * 1024).indexCountPerFile(100000).osPageSize(1024 * 4).build();

        NodeState nodeState = new NodeState(conf);
        nodeState.init();
        Store store = new FileStore(conf, nodeState);
        store.init();
        store.start();
        String appendStr = "1sdfasdfasdfasdfasdfasdfasdfadf54545fasdfasdfasdfasdfasdfasdfadfa53345dfasdfasdfasdfasdfasdfasdfad9081nvsdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfad2";
        byte[] bytes = appendStr.getBytes();
        GekkoEntry entry = GekkoEntry.builder().magic(0xCAFEDADD).term(123).data(bytes).build();
        entry.computSizeInBytes();
        long pos_66666 = 0;
        long pos_99999 = 0;
        for (int i = 0; i < 100001; i++) {
            store.append(entry);

            if (i == 66666) {
                System.out.println(entry.getPos());
                pos_66666 = entry.getPos();
                System.out.println("pos_66666 index=" + entry.getEntryIndex());
            }
            if (i == 99999) {
                System.out.println(entry.getPos());
                pos_99999 = entry.getPos();
            }
        }

        Assert.assertEquals(100001, store.getMaxIndex());
        List<GekkoEntry> entryList0 = store.batchGetByIndex(1, 100002);
        Assert.assertEquals(100001, entryList0.size());

        Assert.assertTrue((pos_99999 - pos_66666) > 1024 * 1024);

        //test get
        GekkoEntry ent666 = store.get(pos_66666, entry.getTotalSize());
        Assert.assertTrue(ent666.isIntact());

        //test batchget
        long startTime = System.currentTimeMillis();
        List<GekkoEntry> entryList = store.batchGet(pos_66666, pos_99999);

        GekkoEntry lastEntry1 = entryList.get(entryList.size() - 1);

        System.out.println("get 33333 entris costs " + (System.currentTimeMillis() - startTime) + "ms");
        for (GekkoEntry e : entryList) {
            Assert.assertTrue(e.isIntact());
        }
        Assert.assertEquals(pos_99999, lastEntry1.getPos() + entry.getTotalSize());
        Assert.assertEquals(99999 - 66666, entryList.size());


        //test getByIndex
        GekkoEntry _ent666 = store.getByIndex(66666 + 1);
        Assert.assertTrue(_ent666.isIntact());
        Assert.assertEquals(ent666.checksum(), _ent666.checksum());


        //test batchGetByIndex
        List<GekkoEntry> batchGetByIndexEntries = store.batchGetByIndex(66666 + 1, 99999 + 1);
        GekkoEntry lastEntry2 = batchGetByIndexEntries.get(batchGetByIndexEntries.size() - 1);
        Assert.assertEquals(pos_99999, lastEntry2.getPos() + entry.getTotalSize());
        Assert.assertEquals(99999 - 66666, batchGetByIndexEntries.size());

        for (int i = 0; i < batchGetByIndexEntries.size(); i++) {
            GekkoEntry e1 = entryList.get(i);
            GekkoEntry e2 = batchGetByIndexEntries.get(i);
            Assert.assertTrue(e2.isIntact());
            Assert.assertEquals(e1.checksum(), e2.checksum());
        }

        System.out.println("end normal ");
        //Test load

        NodeState state = new NodeState(conf);
        state.setLeaderId("1");
        state.setSelfId("1");
        Store store2 = new FileStore(conf, state);
        store2.init();
        store2.start();
        List<GekkoEntry> batchGetByIndexEntries2 = store2.batchGetByIndex(66666 + 1, 99999 + 1);
        GekkoEntry lastEntry22 = batchGetByIndexEntries2.get(batchGetByIndexEntries2.size() - 1);
        Assert.assertEquals(pos_99999, lastEntry22.getPos() + entry.getTotalSize());
        Assert.assertEquals(99999 - 66666, batchGetByIndexEntries2.size());

        for (int i = 0; i < batchGetByIndexEntries2.size(); i++) {
            GekkoEntry e1 = entryList.get(i);
            GekkoEntry e2 = batchGetByIndexEntries2.get(i);
            Assert.assertTrue(e2.isIntact());
            Assert.assertEquals(e1.checksum(), e2.checksum());
        }
        System.out.println("end batch");

        //test load the lastest one
        GekkoEntry theLastestOne = store2.getByIndex(100001);
        Assert.assertTrue(theLastestOne.isIntact());

        //test load the lastest one
        GekkoEntry theLastestOne1 = store2.getByIndex(100000);
        Assert.assertTrue(theLastestOne1.isIntact());

    }

}
