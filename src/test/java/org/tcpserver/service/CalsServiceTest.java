/*
 * Copyright 2015 SpringIO.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tcpserver.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mephisto9000
 */
public class CalsServiceTest {
    
    public CalsServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * simple equality test
     */
    @Test
    public void testSayHello() {
        System.out.println("CalcService.sayHello test1");
        String name = "sayHello";
        CalcService.clearSum();
        CalcService instance = new CalcService();
        String expResult = "you typed 'sayHello', current sum = 0";
        String result = instance.saySum(name);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testSayHello1() {
        System.out.println("CalcService.sayHello test2");
        String name = "1";
        CalcService.clearSum();
        CalcService instance = new CalcService();
        String expResult = "you typed '1', current sum = 1";
        String result = instance.saySum(name);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testSayHello2() {
        System.out.println("CalcService.sayHello test3");
        CalcService.clearSum();
        CalcService instance = new CalcService();
        
        String name = "1";
        instance.saySum(name);
        name = "2";
        String expResult = "you typed '2', current sum = 3";
        String result = instance.saySum(name);
        assertEquals(expResult, result);

    }
    
}
