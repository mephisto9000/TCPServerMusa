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
package org.musa.tcpserver;

import java.io.IOException;
import java.io.OutputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.musa.payload.SMLoyalty;
import org.musa.payload.SMRank;
import org.musa.payload.SpaceMarine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.serializer.Serializer;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author mephisto9000
 */
@RunWith(SpringJUnit4ClassRunner.class)


@ContextConfiguration(locations = {"classpath:META-INF/spring/integration/serverContext_test.xml" })

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MainTest {
    
    
    @Autowired 
    AbstractClientConnectionFactory client;
    
    @Autowired 
    WarpGateway gw;
    
    
    
    public MainTest() {
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
     * Test of main method, of class Main.
     */
   
    @Test
    public void  testSetup() {
        System.out.println("Main.setupContext test1");
        GenericXmlApplicationContext context = Main.setupContext();
        assertNotNull(context);
    }
    
    
     @Test
    public void  testSetup1() {
        System.out.println("Integration test2");
        
        //GenericXmlApplicationContext context = Main.setupTestContext();
        
        client.setSerializer(new Serializer<SpaceMarine>(){
            
             public void serialize(SpaceMarine spaceMarine, OutputStream out) throws IOException {
                
                       
                /*
                disassembling the space marine...
                */
                
                
                byte[] nameParticles = (spaceMarine.getName()+';').getBytes();
		out.write(nameParticles);
                
                

		byte[] chapterParticles = (spaceMarine.getChapter()+';').getBytes();
		out.write(chapterParticles);

		byte[] killsParticles = (Integer.toString(spaceMarine.getKills())+';').getBytes();                
		out.write(killsParticles);
                
                
                
                byte[] rankParticles = (spaceMarine.getRank().name()+';').getBytes();
                out.write(rankParticles);
                
                byte[] loyaltyParticles = (spaceMarine.getLoyalty().name()+';').getBytes();
                out.write(loyaltyParticles);
                
                byte[] statusParticles = (spaceMarine.getStatus().name()+';').getBytes();
                out.write(statusParticles);
                
                byte[] damageParticles = (Integer.toString(spaceMarine.getDamage())+';').getBytes();
                out.write(damageParticles);
                
                
                               		
		out.flush();
        
     
    }
        
        });
        
        SpaceMarine horus = new SpaceMarine("Horus", "Sons of Horus", 999999, SMRank.Primarch, SMLoyalty.Traitor, 999);
        Message<SpaceMarine> m = MessageBuilder.withPayload(horus)        
        .build();
        
        String res = gw.send(m);
        
        assertEquals("noone hurt", res);
        
        //
        //assertNotNull(context);
    }
    
    
    
    
}
